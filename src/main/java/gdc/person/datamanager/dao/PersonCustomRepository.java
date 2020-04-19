/**
 * 
 */
package gdc.person.datamanager.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import gdc.person.datamanager.pojo.Person;

/**
 * @author suhada
 *
 */
@Repository
public interface PersonCustomRepository extends CustomRepository<Person>{

	public List<Person> getPersonList(HashMap<String, Object> param);
}
