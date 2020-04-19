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

import gdc.person.common.form.PersonEmailForm;
import gdc.person.common.form.PersonForm;
import gdc.person.common.form.validation.FormValidationUtil.FormKey;
import gdc.person.datamanager.access.PersonEmailAccess;
import gdc.person.datamanager.pojo.Person;
import gdc.person.datamanager.pojo.PersonEmail;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;
import gdc.utility.validations.Validation;

/**
 * @author suhada
 *
 */
@Service
public class PersonEmailService {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonEmailService.class);

	@Autowired
	private PersonEmailAccess personEmailAccess;
	
	@Autowired
	private Validation validation;
	
	public DataTransfer getByEmail(DataTransfer dataTrans) {
		String email = (String)dataTrans.getInput("email");
		if(email!=null&&!email.equals("")) {
			if(this.validation.isEmail(email)) {
				List<PersonEmail> list = this.personEmailAccess.getByEmail(email);
				if(list.size()>0) {
					dataTrans.setStatus(Status.SUCCESS);
					dataTrans.addOutput(Key.PERSON_LIST, list);
				}
			}else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not a valid email");
				dataTrans.addOutput(Key.WARNING_LIST, "not a valid email");
			}
		}else {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING, "enter email");
			dataTrans.addOutput(Key.WARNING_LIST, "enter email");
		}
		return dataTrans;
	}
	
	public DataTransfer getByEmailLike(DataTransfer dataTrans) {
		String email = (String)dataTrans.getInput("email");
		if(email!=null&&!email.equals("")) {
			List<PersonEmail> list = this.personEmailAccess.getByEmailLike(email);
			if(list.size()>0) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.PERSON_LIST, list);
			}
		}else {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING, "enter email");
			dataTrans.addOutput(Key.WARNING_LIST, "enter email");
		}
		return dataTrans;
	}

	/**
	 * @param dataTrans
	 */
	public DataTransfer addEmail(DataTransfer dataTrans) {
		logger.debug("------>>start addPerson<<------");
		PersonEmailForm form = (PersonEmailForm)dataTrans.getInput("personEmailForm");
		if(!form.validate(FormKey.PERSONEMAILFORM, dataTrans)) {
			return dataTrans;
		}
		PersonEmail email = new PersonEmail();
		this.setEmailData(form, email);
		dataTrans.addInput("pojo_person_email", email);
		this.personEmailAccess.save(dataTrans);
		dataTrans.addInput("personEmail", email);
		logger.debug("------>>end addPerson<<------");
		return dataTrans;
	}
	
	public DataTransfer getById(DataTransfer dataTrans) {
		logger.debug("------>>start getById<<------");
		long id = (long)dataTrans.getInput("id");
		if(id>0) {
			PersonEmail personEmail = this.personEmailAccess.getById(id);
			if(personEmail != null) {
				dataTrans.addOutput("person_email", personEmail);
			}
		}else {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING, "enter id");
			dataTrans.addOutput(Key.WARNING_LIST, "enter id");
		}
		logger.debug("------>>end getById<<------");
		return dataTrans;
	}
	
	public DataTransfer getByStatus(DataTransfer dataTrans) {
		logger.debug("------>>start getByStatus<<------");
		String status = (String)dataTrans.getInput("status");
		if(status!=null && !status.equals("")) {
			List<PersonEmail> list = this.personEmailAccess.getByStatus(status);
			if(list!=null) {
				dataTrans.setStatus(Status.SUCCESS);
				if(list.size()>0) {
					dataTrans.addOutput(Key.PERSON_LIST, list);
				}
			}
		}else {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING, "enter status");
			dataTrans.addOutput(Key.WARNING_LIST, "enter status");
		}
		logger.debug("------>>end getByStatus<<------");
		return dataTrans;
	}
	
	private void setEmailData(PersonEmailForm form,PersonEmail email) {
		logger.debug("------>>start setEmailData<<------");
		email.setId(form.getId());
		if(email.getEmail()==null|| email.getEmail().equals("") || !email.getEmail().equals(form.getEmail())) {
			email.setEmail(form.getEmail());
		}
		if(email.getStatus()==null|| email.getStatus().equals("") || !email.getStatus().equals(form.getStatus())) {
			email.setStatus(form.getStatus());
		}
		if(email.getSysAddDate()==null) {
			if (form.getSys_add_date() !=null)
				email.setSysAddDate(form.getSys_add_date());
			else 
				email.setSysAddDate(new Date());
		}
		
		if(email.getPerson()==null || email.getPerson().getId()!=form.getPerson_id()) {
			email.setPerson(new Person(form.getPerson_id()));
		}
		logger.debug("------>>end setEmailData<<------");
	}
	
}
