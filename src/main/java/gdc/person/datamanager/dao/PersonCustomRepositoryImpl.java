/**
 * 
 */
package gdc.person.datamanager.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;

import gdc.person.datamanager.pojo.Person;

/**
 * @author suhada
 *
 */
public class PersonCustomRepositoryImpl implements PersonCustomRepository{

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(PersonCustomRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see gdc.person.datamanager.dao.PersonCustomRepository#getShopList(java.util.HashMap)
	 */
	@Override
	public List<Person> getPersonList(HashMap<String, Object> param) {
		logger.debug("------>> start getPersonList<<------");
		List<Person> list = null;
		try {
			String stmt = "SELECT s FROM Person s ";
			if(param.get("order") != null) {
				stmt+="ORDER BY s.id "+((String)param.get("order"));
			}
			Query query = this.entityManager.createQuery(stmt,Person.class);
			if(param.get("start") != null) {
				query.setFirstResult((int)param.get("start"));
			}
			if(param.get("count") != null && ((int)param.get("count"))>0) {
				query.setMaxResults((int)param.get("count"));
			}
			list = query.getResultList();
		} catch (Exception e) {
			logger.error("------>> Error while getting person list: ",e);
		}
		logger.debug("------>> list: "+list);
		logger.debug("------>> end getPersonList<<------");
		return list;
	}

}
