/**
 * 
 */
package gdc.person.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdc.person.common.form.PersonContactForm;
import gdc.person.common.form.validation.FormValidationUtil.FormKey;
import gdc.person.datamanager.access.PersonContactAccess;
import gdc.person.datamanager.pojo.Person;
import gdc.person.datamanager.pojo.PersonContact;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Service
public class PersonContactService {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonContactService.class);

	@Autowired
	private PersonContactAccess personContactAccess;
	
	public DataTransfer addContact(DataTransfer dataTrans) {
		logger.debug("------>>Start addContact<<------");
		try {
			PersonContactForm form = (PersonContactForm)dataTrans.getInput("personContactForm");
			if(!form.validate(FormKey.PERSONCONTACTFORM, dataTrans)) {
				return dataTrans;
			}
			PersonContact personContact =new PersonContact();
			this.setContactData(form, personContact);
			dataTrans.addInput("pojo_person_contact", personContact);
			dataTrans = this.personContactAccess.save(dataTrans);
			
		} catch (Exception e) {
			logger.error("Error : "+e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addInput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>>End addContact<<------");
		return dataTrans;
	}
	
	public DataTransfer getByPersonID(DataTransfer dataTrans) {
		logger.debug("------>>Start getByPersonID<<------");
		try {
			long personId = (long)dataTrans.getInput("personId");
			if(personId > 0) {
				List<PersonContact> list = this.personContactAccess.getByPersonId(personId);
				if(list != null) {
					dataTrans.addOutput(Key.PERSON_CONTACT_LIST, list);
				}else {
					dataTrans.setStatus(Status.WARNING);
					dataTrans.addOutput(Key.WARNING, "not found");
				}
			}else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "enter valid id");
			}
		} catch (Exception e) {
			logger.error("Error : "+e);
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>>End getByPersonID<<------");
		return dataTrans;
	}

	public DataTransfer getByPersonNIC(DataTransfer dataTrans) {
		logger.debug("------>>Start getByPersonNIC<<------");
		try {
			String nic = (String)dataTrans.getInput("nic");
			if(nic !=null && !nic.equals("")) {
				PersonContact personContact = this.personContactAccess.getByPersonNIC(nic);
			}else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "enter nic");
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>End getByPersonNIC<<------");
		return dataTrans;
	}
	/**
	 * @param dataTrans
	 * @return
	 */
	public DataTransfer getById(DataTransfer dataTrans) {
		logger.debug("------>>Start getById<<------");
		try {
			long id = (long)dataTrans.getInput("id");
			if(id > 0) {				
				PersonContact personContact= this.personContactAccess.getById(id);
				dataTrans.setStatus(Status.SUCCESS);
				if(personContact != null) {
					dataTrans.addOutput(Key.PERSON_CONTACT, personContact);
				}else {
					dataTrans.addOutput(Key.MESSAGE, "not found person contact");
				}
			}else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING_LIST, "enter valid id");
			}
		} catch (Exception e) {
			logger.error("------>> Error : ",e);
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>>End getById<<------");
		return dataTrans;
	}

	public void setContactData(PersonContactForm form,PersonContact personContact) {
		logger.debug("------>>Start setContactData<<------");
		personContact.setId(form.getId());
		if(personContact.getNumber()==null || personContact.getNumber().equals("")|| !personContact.getNumber().equals(form.getNumber())) {
			personContact.setNumber(form.getNumber());
		}
		if(personContact.getStatus()==null|| personContact.getStatus().equals("") || !personContact.getStatus().equals(form.getStatus())) {
			personContact.setStatus(form.getStatus());
		}
		if(personContact.getSysAddDate()==null) {
			if (form.getSys_add_date() !=null)
				personContact.setSysAddDate(form.getSys_add_date());
			else 
				personContact.setSysAddDate(new Date());
		}
		
		if(personContact.getPerson()==null || personContact.getPerson().getId()!=form.getPerson_id()) {
			personContact.setPerson(new Person(form.getPerson_id()));
		}
		logger.debug("------>>End setContactData<<------");
		
	}

}
