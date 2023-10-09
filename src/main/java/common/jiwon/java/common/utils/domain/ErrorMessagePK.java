package common.jiwon.java.common.utils.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ErrorMessagePK implements Serializable {
    @Serial
    private static final long serialVersionUID = -3504979493250850908L;

    private String code;
    private String lang;
}
