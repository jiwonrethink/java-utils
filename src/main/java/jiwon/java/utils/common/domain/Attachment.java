package jiwon.java.utils.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID fileUuid;

    @Column(length = 20, nullable = false)
    private String fileKey;

    @Column(nullable = false)
    private Long fileSeq;
    @Column(name = "file_nm", length = 50)
    private String originFileName;

    @Column(length = 300)
    private String filePath;

    @Column(length = 300)
    private String fileUrl;

    @Column(length = 10)
    private String fileExt;

    private Long fileSize;

    @Column(name = "logo_file_fl", nullable = false)
    @ColumnDefault("false")
    private Boolean logoFileFlag;

    @Column(length = 30, nullable = false)
    private String crtUserId;

    @CreatedDate
    @Column(name = "crt_dtm", updatable = false)
    private LocalDateTime crtDateTime;

    @Builder
    public Attachment(UUID fileUuid, String fileKey, Long fileSeq,
                      String originFileName, String filePath, String fileUrl,
                      String fileExt, Long fileSize, Boolean logoFileFlag, String crtUserId) {
        this.fileUuid = fileUuid;
        this.fileKey = fileKey;
        this.fileSeq = fileSeq;
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.fileUrl = fileUrl;
        this.fileExt = fileExt;
        this.fileSize = fileSize;
        this.logoFileFlag = logoFileFlag;
        this.crtUserId = crtUserId;
    }
}
