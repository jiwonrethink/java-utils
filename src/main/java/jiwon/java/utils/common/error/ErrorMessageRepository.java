package jiwon.java.utils.common.error;

import jiwon.java.utils.common.domain.ErrorMessagePK;
import jiwon.java.utils.common.domain.ErrorMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ErrorMessageRepository extends JpaRepository<ErrorMessage, ErrorMessagePK> {

    List<ErrorMessage> findAllByName(String name);
}
