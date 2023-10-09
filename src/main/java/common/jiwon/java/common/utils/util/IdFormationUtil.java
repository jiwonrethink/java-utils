package common.jiwon.java.common.utils.util;

import common.jiwon.java.common.utils.constant.KeyPrefixCd;
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
