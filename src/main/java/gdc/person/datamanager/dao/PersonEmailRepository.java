/**
 * 
 */
package gdc.person.datamanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gdc.person.datamanager.pojo.PersonEmail;

/**
 * @author suhada
 *
 */
public interface PersonEmailRepository extends JpaRepository<PersonEmail, Long> {

	@Query(value = "SELECT p FROM PersonEmail p WHERE p.email = :email")
	List<PersonEmail> findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT p FROM PersonEmail p WHERE p.status = :status")
	List<PersonEmail> findByStatus(@Param("status") String status);
	
	@Query(value = "SELECT p FROM PersonEmail p WHERE p.email LIKE %:email%")
	List<PersonEmail> findByEmailLike(@Param("email") String email);
	
	@Query(value = "SELECT p FROM PersonEmail p WHERE p.email = :email AND p.person.id = :person_id")
	PersonEmail findbyEmailAndPersonId(@Param("email") String email,@Param("person_id")Long id);
}
