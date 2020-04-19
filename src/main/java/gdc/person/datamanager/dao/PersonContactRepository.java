/**
 * 
 */
package gdc.person.datamanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gdc.person.datamanager.pojo.PersonContact;

/**
 * @author suhada
 *
 */
@Repository
public interface PersonContactRepository extends JpaRepository<PersonContact, Long> {

	@Query(value = "SELECT pc FROM PersonContact pc WHERE pc.person.id = :personId")
	List<PersonContact> findByPersonId(@Param("personId") long personId);

	@Query(value = "SELECT pc FROM PersonContact pc WHERE pc.person.nic = :nic")
	PersonContact findByPersonNIC(@Param("nic")String nic);
	
	@Query(value = "SELECT pc FROM PersonContact pc WHERE pc.number = :contact AND pc.person.id = :person_id")
	PersonContact findByContactAndPersonId(@Param("contact")String nic,@Param("person_id")Long person_id);
}
