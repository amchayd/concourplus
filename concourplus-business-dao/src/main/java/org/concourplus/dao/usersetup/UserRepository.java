package org.concourplus.dao.usersetup;

import java.util.Date;
import java.util.List;

import org.concourplus.model.usersetup.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{

    @Query("select u from User u  where u.username = :username")
	public User findByUsername(@Param("username") String username);
	
    //public List<String> findUserDetailService(String username);
    
	@Modifying
	@Query("UPDATE User u SET u.token = :token, u.tokenDate = :tokenDate WHERE u.id = :userId")
	public void updateUserToken(@Param("token") String token, @Param("tokenDate") Date tokenDate, @Param("userId") Long userId);

}
