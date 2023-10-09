package utils.common.util;

import utils.common.constant.KeyPrefixCd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdFormationUtil {

    public static String generator(KeyPrefixCd keyPrefixCd, Long seq) {
        String id = keyPrefixCd + String.format("%05d", seq);

        return id;
    }
}
