package org.concourplus.dao.usersetup;

import org.concourplus.model.usersetup.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends JpaRepository<Profile, Long>, JpaSpecificationExecutor<Profile>{

	@Query("select p from Profile p  where p.code = :code")
	public Profile getProfileByCode(@Param("code") String code);
}
