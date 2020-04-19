/**
 * 
 */
package gdc.person.datamanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gdc.person.datamanager.pojo.Person;

/**
 * @author suhada
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>,PersonCustomRepository {

	
	@Query(value = "SELECT p FROM Person p WHERE p.nic =:nic")
	Person findByNIC(@Param("nic") String nic);
	
	@Query(value = "SELECT p FROM Person p WHERE p.nic like %:nic%")
	List<Person> findByNICLike(@Param("nic") String nic);
	
	@Query(value = "SELECT p FROM Person p JOIN FETCH p.personEmails pe WHERE pe.email = :email")
	List<Person> findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT p FROM Person p JOIN FETCH p.personEmails pe WHERE pe.email LIKE %:email%")
	List<Person> findByEmailLike(@Param("email") String email);

	@Query(value = "SELECT p FROM Person p WHERE p.id in (:id_list)")
	List<Person> findByIdIn(@Param("id_list")Long[] id_list);
}
