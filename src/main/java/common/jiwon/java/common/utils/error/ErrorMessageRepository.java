package common.jiwon.java.common.utils.error;

import common.jiwon.java.common.utils.domain.ErrorMessage;
import common.jiwon.java.common.utils.domain.ErrorMessagePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ErrorMessageRepository extends JpaRepository<ErrorMessage, ErrorMessagePK> {

    List<ErrorMessage> findAllByName(String name);
}
