package utils.common.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "err_msg_mng")
@IdClass(ErrorMessagePK.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorMessage {

    @Id
    @Column(name = "err_code")
    private String code;

    @Id
    private String lang;

    @Column(name = "err_nm")
    private String name;

    @Column(name = "err_msg")
    private String message;

}
