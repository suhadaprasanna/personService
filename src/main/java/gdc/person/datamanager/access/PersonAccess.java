package gdc.person.datamanager.access;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.person.datamanager.dao.PersonRepository;
import gdc.person.datamanager.pojo.Person;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

@Repository("personAccess")
@Transactional
public class PersonAccess{

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(PersonAccess.class);

	@Autowired
	private PersonRepository personRepository;

	public DataTransfer save(DataTransfer dataTrans) {
		logger.debug("------>>start save<<------");
		try {
			Person person = (Person)dataTrans.getInput("pojo_person");
			person = this.personRepository.save(person);
			if (person.getId() > 0) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput("pojo_person", person);
			} else {
				dataTrans.setStatus(Status.FAIL);
			}
		} catch (Exception e) {
			logger.error("------>>Error in person saving: "+e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, e.getLocalizedMessage());
			dataTrans.addOutput(Key.ERROR_LIST, e.getLocalizedMessage());
		}
		logger.debug("------>>end save<<------");
		return dataTrans;
	}
	
	public DataTransfer update(DataTransfer dataTrans) {
		logger.debug("------>>start update<<------");
		try {
			Person person = (Person)dataTrans.getInput(Key.POJO_PERSON);
			person = this.personRepository.save(person);
			dataTrans.setStatus(Status.SUCCESS);
		} catch (Exception e) {
			logger.error("Error : "+e);
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>>end update<<------");
		return dataTrans;
	}
	
	public Person getById(long id) {
		logger.debug("------>>start getById<<------");
		logger.debug("------>> id:"+id);
		Person person = null;
		try {
			Optional<Person> list =  this.personRepository.findById(id);
			if(list.isPresent()) {
				person = list.get();
			}
		} catch (Exception e) {
			logger.error("------>> Error : ",e);
		}
		logger.debug("------>> person: "+person);
		logger.debug("------>>end getById<<------");
		return person;
	}

	public Person getByNIC(String nic) {
		logger.debug("------>>start getByNIC<<------");
		logger.debug("------>> nic:"+nic);
		Person person = null;
		try {
			person = this.personRepository.findByNIC(nic);
		} catch (Exception e) {
			logger.error("------>> Error : ",e);
		}
		logger.debug("------>> person: "+person);
		logger.debug("------>>end getByNIC<<------");
		return person;
	}
	
	public List<Person> getByNICLike(String nic) {
		logger.debug("------>>start getByNICLike<<------");
		logger.debug("------>> nic:"+nic);
		List<Person> list = null;
		try {
			list = this.personRepository.findByNICLike(nic);
		} catch (Exception e) {
			logger.error("------>> Error : ",e);
		}
		logger.debug("------>> person list: "+list);
		logger.debug("------>>end getByNICLike<<------");
		return list;
	}

	public Person getByEmail(String email) {
		logger.debug("------>>start getByEmail<<------");
		Person person = null;
		try {
			List<Person> list = this.personRepository.findByEmail(email);
			logger.debug("------>> list size:"+list.size());
			if(list.size()>0) {
				person = list.get(0);
			}
		} catch (Exception e) {
			logger.error("------>> Error : ",e);
		}
		logger.debug("------>> person : "+person);
		logger.debug("------>>end getByEmail<<------");
		return person;
	}
	
	public List<Person> getByEmailLike(String email) {
		logger.debug("------>>start getByEmailLike<<------");
		List<Person> list = null;
		try {
			list = this.personRepository.findByEmailLike(email);
			
		} catch (Exception e) {
			logger.error("------>> Error : ",e);
		}
		logger.debug("------>>end getByEmailLike<<------");
		return list;
	}
	
	public Person deletePerson(long id) {
		logger.debug("------>>start deletePerson<<------");
		Person person = null;
		try {
			Optional<Person> op= this.personRepository.findById(id);
			if(op.isPresent()) {
				person = op.get();
				if(person != null) {
					person.setStatus("N");
					person = this.personRepository.save(person);
				}
			}
		} catch (Exception e) {
			logger.error("------>> Error : ",e);
		}
		logger.debug("------>>end deletePerson<<------");
		return person;
	}
	
	public List<Person> getPersonList(DataTransfer dataTrans){
		logger.debug("------>>start getPersonList<<------");
		List<Person> list = null;
		try {
			HashMap<String, Object> param = new HashMap();
			if(dataTrans.getInput("count")!=null) {
				param.put("count", (int)dataTrans.getInput("count"));
			}
			if(dataTrans.getInput("start")!=null) {
				param.put("start", (int)dataTrans.getInput("start"));
			}
			if(dataTrans.getInput("order")!=null) {
				param.put("order", (String)dataTrans.getInput("order"));
			}
			if(dataTrans.getInput("other_details")!=null) {
				param.put("other_details", (String)dataTrans.getInput("other_details"));
			}
			list = this.personRepository.getPersonList(param);
			logger.debug("------>> list: "+list);
		} catch (Exception e) {
			logger.error("------>> Error : ",e);
		}
		logger.debug("------>>end getPersonList<<------");
		return list;
	}

	/**
	 * @param id_list
	 * @return
	 */
	public List<Person> getByIds(Long[] id_list) {
		logger.debug("------>>start getByIds<<------");
		List<Person> list = null;
		try {
			list = this.personRepository.findByIdIn(id_list);
		} catch (Exception e) {
			logger.error("------>> Error : ",e);
		}
		logger.debug("------>>end getByIds<<------");
		return list;
	}
}
