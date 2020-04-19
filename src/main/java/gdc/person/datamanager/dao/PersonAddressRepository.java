/**
 * 
 */
package gdc.person.datamanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gdc.person.datamanager.pojo.PersonAddress;
import gdc.person.datamanager.pojo.PersonContact;

/**
 * @author suhada
 *
 */
@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, Long> {

	@Query(value = "SELECT pc FROM PersonAddress pc WHERE pc.person.id = :personId")
	List<PersonAddress> findByPersonId(@Param("personId") long personId);

	@Query(value = "SELECT pc FROM PersonAddress pc WHERE pc.person.nic = :nic")
	PersonAddress findByPersonNIC(@Param("nic")String nic);
	
}
