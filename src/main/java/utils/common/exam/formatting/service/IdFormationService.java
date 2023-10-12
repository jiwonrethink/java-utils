package utils.common.exam.formatting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.common.constant.KeyPrefixCd;
import utils.common.util.IdFormationUtil;

@Service
@Transactional(readOnly = true)
public class IdFormationService {

    @Transactional
    public void addId() {
        String id = IdFormationUtil.generator(KeyPrefixCd.KEY, 1L);
    }
}
