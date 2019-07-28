package org.concourplus.dao.referential;

import org.concourplus.model.referential.SecretQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SecretQuestionRepository extends JpaRepository<SecretQuestion, Long>, JpaSpecificationExecutor<SecretQuestion>{

	@Query("select sq from SecretQuestion sq  where sq.code = :code")
	public SecretQuestion getSecretQuestionByCode(@Param("code") String code);
}
