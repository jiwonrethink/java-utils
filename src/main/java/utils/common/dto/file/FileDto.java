package utils.common.dto.file;

import utils.common.domain.Attachment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileDto {
    private String fileUuid;
    private Long fileSeq;
    private String originFileName;
    private String filePath;
    private String fileUrl;
    private String fileExt;
    private Long fileSize;
    private Boolean logoFileFlag;

    public FileDto(Attachment attachment) {
        this.fileUuid = attachment.getFileUuid().toString();
        this.fileSeq = attachment.getFileSeq();
        this.originFileName = attachment.getOriginFileName();
        this.filePath = attachment.getFilePath();
        this.fileUrl = attachment.getFileUrl();
        this.fileExt = attachment.getFileExt();
        this.fileSize = attachment.getFileSize();
        this.logoFileFlag = attachment.getLogoFileFlag();
    }

    public FileDto(String fileUuid, Long fileSeq, String originFileName, String filePath,
                   String fileUrl, String fileExt, Long fileSize, Boolean logoFileFlag) {
        this.fileUuid = fileUuid;
        this.fileSeq = fileSeq;
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.fileUrl = fileUrl;
        this.fileExt = fileExt;
        this.fileSize = fileSize;
        this.logoFileFlag = logoFileFlag;
    }

    public FileDto(String fileUuid, Long fileSeq, String originFileName, String filePath,
                   String fileExt, Long fileSize, Boolean logoFileFlag) {
        this.fileUuid = fileUuid;
        this.fileSeq = fileSeq;
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.fileExt = fileExt;
        this.fileSize = fileSize;
        this.logoFileFlag = logoFileFlag;
    }
}