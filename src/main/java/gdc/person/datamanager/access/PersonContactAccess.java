/**
 * 
 */
package gdc.person.datamanager.access;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.person.datamanager.dao.PersonContactRepository;
import gdc.person.datamanager.pojo.PersonContact;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Repository("personContactAccess")
@Transactional
public class PersonContactAccess {

	private static final Logger logger = LoggerFactory.getLogger(PersonContactAccess.class);
	
	@Autowired
	private PersonContactRepository personContactRepository;

	public List<PersonContact> getByPersonId(long personId)throws Exception {
		logger.debug("------>> start getByPersonId <<------");
		List<PersonContact> list = null;
		try {
			list = this.personContactRepository.findByPersonId(personId);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> end getByPersonId <<------");
		return list;
	}

	public PersonContact getById(long id)throws Exception {
		logger.debug("------>> start getById <<------");
		PersonContact personContact = null;
		try {
			Optional<PersonContact> op = this.personContactRepository.findById(id);
			if(op.isPresent()) {
				personContact = op.get();
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> end getById <<------");
		return personContact;
	}

	public DataTransfer save(DataTransfer dataTrans) {
		logger.debug("------>> start save <<------");
		try {
			PersonContact personContact = (PersonContact) dataTrans.getInput("pojo_person_contact");
			personContact = this.personContactRepository.save(personContact);
			if(personContact.getId()>0) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput("person_contact", personContact);
			}else {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.MESSAGE, "save failed");
			}
		} catch (Exception e) {
			logger.error("Error : "+e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.MESSAGE, "something went wrong");
		}
		logger.debug("------>> end save <<------");
		return dataTrans;
	}


	public PersonContact getByPersonNIC(String nic) {
		logger.debug("------>> start getByPersonNIC <<------");
		PersonContact personContact = null;
		try {
			if(nic != null && !nic.equals("")) {
				personContact = this.personContactRepository.findByPersonNIC(nic);
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> personContact: "+personContact);
		logger.debug("------>> end getByPersonNIC <<------");
		return personContact;
	}

	/**
	 * @param contact1
	 * @param id
	 */
	public PersonContact getByContactAndPersonId(String contact, Long id) {
		logger.debug("------>> start getByContactAndPersonId <<------");
		PersonContact personContact = null;
		try {
			if(contact != null && !contact.equals("") && id > 0) {
				personContact = this.personContactRepository.findByContactAndPersonId(contact,id);
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> personContact: "+personContact);
		logger.debug("------>>  getByContactAndPersonId <<------");
		return personContact;
	}
}
