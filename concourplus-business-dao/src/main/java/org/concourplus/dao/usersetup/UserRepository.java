package org.concourplus.dao.usersetup;

import java.util.Date;

import org.concourplus.model.usersetup.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{

	@Modifying
	@Query("UPDATE User u SET u.token = :token, u.tokenDate = :tokenDate WHERE u.id = :userId")
	public void updateUserToken(@Param("token") String token, @Param("tokenDate") Date tokenDate, @Param("userId") Long userId);

}
