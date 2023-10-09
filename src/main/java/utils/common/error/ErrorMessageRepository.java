package utils.common.error;

import utils.common.domain.ErrorMessagePK;
import utils.common.domain.ErrorMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ErrorMessageRepository extends JpaRepository<ErrorMessage, ErrorMessagePK> {

    List<ErrorMessage> findAllByName(String name);
}
