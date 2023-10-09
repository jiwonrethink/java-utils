package utils.common.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import utils.common.domain.Attachment;
import utils.common.dto.file.FileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUtil {
    private final AmazonS3Client amazonS3Client;
    private final StandardPBEStringEncryptor pbeEnc;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    final int INPUT_STREAM_BUFFER_SIZE = 2048;

    public <T> ArrayList<T> difference(ArrayList<T> list1, ArrayList<T> list2)
    {
        ArrayList<T> result = new ArrayList<>();
        result.addAll(list1);
        result.removeAll(list2);

        return result;
    }

    public void deleteFile(Attachment attachment) {
        delete(attachment.getFilePath());
    }

    public void deleteFiles(List<Attachment> delFileList) {
        for (Attachment attachment : delFileList) {
            delete(attachment.getFilePath());
        }
    }

    public List<FileDto> uploadFiles(MultipartFile[] files, String dirName, Long fileSeq) throws RuntimeException, IOException {
        List<FileDto> fileList = new ArrayList<>();

        for (MultipartFile file : files) {
            FileDto fileDto = uploadFileToS3(file, dirName, false, fileSeq);
            fileList.add(fileDto);

            fileSeq++;
        }

        return fileList;
    }

    public FileDto uploadFile(MultipartFile file, String dirName, Boolean isImg, Long fileSeq) throws RuntimeException, IOException {

        return uploadFileToS3(file, dirName, isImg, fileSeq);
    }

    public FileDto uploadLogoFile(MultipartFile file, String dirName) throws RuntimeException, IOException {

        File uploadFile = convert(file).orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 전환에 실패했습니다."));

        UUID atchmUuid = UUID.randomUUID();
        String originFileName = file.getOriginalFilename();
        String[] originFileNameList = originFileName.split("\\.");
        String fileExt = originFileNameList[originFileNameList.length - 1];
        String filePath = dirName + "/" + "logo." + fileExt; // key
        // s3로 파일 업로드
        String fileUrl = putS3(filePath, uploadFile);
        // 로컬 파일 삭제
        removeFile(uploadFile);

        return new FileDto(atchmUuid.toString(), 1L, "logo." + fileExt, pbeEnc.encrypt(filePath),
                fileUrl, fileExt, file.getSize(), true);
    }

    public void download(OutputStream fileOut, String filePath) throws IOException {
        String realFilePath = pbeEnc.decrypt(filePath);
        S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, realFilePath));

        InputStream inputStream = s3Object.getObjectContent();
        byte[] bytes = new byte[INPUT_STREAM_BUFFER_SIZE];
        int length;

        while ((length = inputStream.read(bytes)) >= 0) {
            fileOut.write(bytes, 0, length);
        }
    }

    public void downloadZip(ZipOutputStream zipOut, String zipDirName, Attachment file) throws IOException {
        String realFilePath = pbeEnc.decrypt(file.getFilePath());
        S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, realFilePath));

        // response에 해당 객체 write
        InputStream inputStream = s3Object.getObjectContent();
        ZipEntry zipEntry = new ZipEntry(zipDirName + "/" + file.getOriginFileName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[INPUT_STREAM_BUFFER_SIZE];
        int length;

        while ((length = inputStream.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.closeEntry();
        inputStream.close();

        log.info("다운로드 성공: {}", realFilePath);
    }

    public String contentType(String fileExt) {
        return switch (fileExt) {
            case "txt" -> MediaType.TEXT_PLAIN.toString();
            case "png" -> MediaType.IMAGE_PNG.toString();
            case "jpg" -> MediaType.IMAGE_JPEG.toString();
            case "svg" -> "image/svg+xml";
            default -> MediaType.APPLICATION_OCTET_STREAM.toString();
        };
    }

    private FileDto uploadFileToS3(MultipartFile file, String dirName, Boolean isImg, Long fileSeq) throws IOException {
        File uploadFile = convert(file).orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 전환에 실패했습니다."));

        UUID atchmUuid = UUID.randomUUID();
        String originFileName = file.getOriginalFilename();
        String[] originFileNameList = originFileName.split("\\.");
        String fileExt = originFileNameList[originFileNameList.length - 1];
        String filePath = dirName + "/" + atchmUuid + "." + fileExt; // key
        // s3로 파일 업로드
        String fileUrl = putS3(filePath, uploadFile);
        // 로컬 파일 삭제
        removeFile(uploadFile);

        return isImg
                ? new FileDto(atchmUuid.toString(), fileSeq, file.getOriginalFilename(), pbeEnc.encrypt(filePath), fileUrl, fileExt, file.getSize(), false)
                : new FileDto(atchmUuid.toString(), fileSeq, file.getOriginalFilename(), pbeEnc.encrypt(filePath), fileExt, file.getSize(), false);
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            file.transferTo(convertFile);

            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    private String putS3(String key, File uploadFile) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, key, uploadFile));

        return amazonS3Client.getUrl(bucket, key).toString();
    }

    private void removeFile(File targetFile) {
        if (targetFile.exists()) {
            if (targetFile.delete()) {
                log.info(targetFile.getName() + ": 로컬 파일 삭제 완료.");
            } else {
                log.info(targetFile.getName() + ": 로컬 파일 삭제 실패.");
            }
        }
    }

    private void delete(String filePath) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, pbeEnc.decrypt(filePath)));
    }
}
