package gdc.person.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdc.person.common.form.PersonAddressForm;
import gdc.person.common.form.PersonContactForm;
import gdc.person.common.form.PersonEmailForm;
import gdc.person.common.form.PersonForm;
import gdc.person.common.form.validation.FormValidationUtil.FormKey;
import gdc.person.datamanager.access.PersonAccess;
import gdc.person.datamanager.access.PersonAddressAccess;
import gdc.person.datamanager.access.PersonContactAccess;
import gdc.person.datamanager.access.PersonEmailAccess;
import gdc.person.datamanager.pojo.Person;
import gdc.person.datamanager.pojo.PersonAddress;
import gdc.person.datamanager.pojo.PersonContact;
import gdc.person.datamanager.pojo.PersonEmail;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;
import gdc.utility.validations.Validation;

@Service(value = "personService")
public class PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

	@Autowired
	private PersonAccess personAccess;

	@Autowired
	private PersonEmailAccess personEmailAccess;

	@Autowired
	private PersonContactAccess personContactAccess;
	
	@Autowired
	private PersonAddressAccess personAddressAccess;

	@Autowired
	private Validation validation;

	public DataTransfer addPerson(DataTransfer dataTrans) {
		logger.debug("------>>start addPerson<<------");
		PersonForm form = (PersonForm) dataTrans.getInput("personForm");
		// start validation
		if (!form.validate(FormKey.PERSONFORM, dataTrans)) {
			return dataTrans;
		}
		if (!this.checkUniqueValuesStatus(dataTrans)) {
			return dataTrans;
		}
		// end validation

		// fill other data in form
		this.reFillForm(form);

		// fill data into person pojo from form
		Person person = this.setPersonData(form,null);
		person.setSys_add_date(new Date());
		person.setStatus("Y");

		// fill data into person email pojo from form
		this.setEmailData(form, person);

		// fill data into person contact pojo from form
		this.setContactData(form, person);

		// fill data into person address pojo from form
		this.setAddressData(form, person);

		dataTrans.addInput(Key.POJO_PERSON, person);
		try {
			dataTrans = this.personAccess.save(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error : ", e);
		}

		logger.debug("------>>end addPerson<<------");
		return dataTrans;
	}

	public DataTransfer updatePerson(DataTransfer dataTrans) {
		logger.debug("------>>start updatePerson<<------");
		PersonForm form = (PersonForm) dataTrans.getInput("personForm");

		if (form == null) {
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "not found form");
		}

		// start validation
		if (!form.validate(FormKey.PERSONFORM, dataTrans)) {
			return dataTrans;
		}

		// check if person id exist
		if (form.getId() <= 0) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.MESSAGE, "not found person id");
			return dataTrans;
		}

		Person person = this.personAccess.getById(form.getId());

		// check if person data exist
		if (person == null) {
			dataTrans.setStatus(Status.FAIL);
			dataTrans.addOutput(Key.MESSAGE, "not found person details");
			return dataTrans;
		}

		this.setPersonData(form, person);

		dataTrans.addInput(Key.POJO_PERSON, person);
		dataTrans = this.personAccess.save(dataTrans);
		logger.debug("------>>end updatePerson<<------");
		return dataTrans;
	}

	public DataTransfer deletePerson(DataTransfer dataTrans) {
		logger.debug("------>>start deletePerson<<------");
		long id = (long) dataTrans.getInput("id");
		if (id > 0) {
			Person person = this.personAccess.deletePerson(id);
			if (person != null) {
				dataTrans.addOutput("person", person);
			}
		}
		logger.debug("------>>end deletePerson<<------");
		return dataTrans;
	}

	public DataTransfer getPersonList(DataTransfer dataTrans) {
		logger.debug("------>>start getAllPerson<<------");
		try {
			List<Person> list = this.personAccess.getPersonList(dataTrans);
			if (list != null) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.PERSON_LIST, list);
			} else {
				dataTrans.addOutput(Key.MESSAGE, "not found any person");
			}
		} catch (Exception e) {
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>>end getAllPerson<<------");
		return dataTrans;
	}

	public DataTransfer getById(DataTransfer dataTrans) {
		logger.debug("------>>start getById<<------");
		try {
			long id = (long) dataTrans.getInput("id");
			if (id > 0) {
				Person person = this.personAccess.getById(id);
				if (person != null) {
					dataTrans.setStatus(Status.SUCCESS);
					dataTrans.addOutput(Key.PERSON, person);
				}
			} else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "enter id");
			}
		} catch (Exception e) {
			dataTrans.setStatus(Status.ERROR);
			logger.error("------>> Error ", e);
		}
		logger.debug("------>>end getById<<------");
		return dataTrans;
	}

	public DataTransfer getByIds(DataTransfer dataTrans) {
		logger.debug("------>>start getByIds<<------");
		try {
			Long[] id_list = (Long[]) dataTrans.getInput("id_list");
			if(id_list.length>0) {
				List<Person> list = this.personAccess.getByIds(id_list);
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.PERSON_LIST, list);
			}else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "enter id list");
			}
		} catch (Exception e) {
			dataTrans.setStatus(Status.ERROR);
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>end getByIds<<------");
		return dataTrans;
	}
	
	public DataTransfer getByNIC(DataTransfer dataTrans) {
		logger.debug("------>>start getByNIC<<------");
		String nic = (String) dataTrans.getInput("nic");
		if (nic != null && !nic.equals("")) {
			Person person = this.personAccess.getByNIC(nic);
			if (person != null) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.PERSON, person);
			}
		} else {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING, "enter nic");
		}
		logger.debug("------>>end getByNIC<<------");
		return dataTrans;
	}

	public DataTransfer getByNICLike(DataTransfer dataTrans) {
		logger.debug("------>>start getByNICLike<<------");
		try {
			String nic = (String) dataTrans.getInput("nic");
			if (nic != null && !nic.equals("")) {
				List<Person> list = this.personAccess.getByNICLike(nic);
				if (list != null) {
					dataTrans.setStatus(Status.SUCCESS);
					dataTrans.addOutput(Key.PERSON_LIST, list);
				}
			} else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.MESSAGE, "enter valid nic");
			}
		} catch (Exception e) {
			logger.error("---->> error ingetByNICLike : " + e);
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>>end getByNICLike<<------");
		return dataTrans;
	}

	public DataTransfer getByEmail(DataTransfer dataTrans) {
		logger.debug("------>>start getByEmail<<------");
		try {
			String email = (String) dataTrans.getInput("email");
			if (email != null && !email.equals("")) {
				Person person = this.personAccess.getByEmail(email);
				if (person != null) {
					dataTrans.setStatus(Status.SUCCESS);
					dataTrans.addOutput(Key.PERSON, person);
				}
			}
		} catch (Exception e) {
			logger.error("---->> error getByEmail : " + e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR_LIST, "Something went wrong");
		}
		logger.debug("------>>end getByEmail<<------");
		return dataTrans;
	}

	public DataTransfer getByEmailLike(DataTransfer dataTrans) {
		logger.debug("------>>start getByEmailLike<<------");
		try {
			String email = (String) dataTrans.getInput("email");
			if (email != null && !email.equals("")) {
				List<Person> list = this.personAccess.getByEmailLike(email);
				if (list != null) {
					dataTrans.setStatus(Status.SUCCESS);
					dataTrans.addOutput(Key.PERSON_LIST, list);
				}
			}
		} catch (Exception e) {
			logger.error("---->> error getByEmailLike : " + e);
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>>end getByEmailLike<<------");
		return dataTrans;
	}

	private Person setPersonData(PersonForm form,Person person) {
		logger.debug("------>>start setPersonData<<------");
		
		if(person == null || form.getId()>0) {
			person = this.personAccess.getById(form.getId());
		}
		if(person == null ){
			person = new Person();
		}
		if(person.getId()==0&&form.getId()>0)
			person.setId(form.getId());
		if (person.getFirst_name() == null || person.getFirst_name().equals("")
				|| !person.getFirst_name().equals(form.getFirst_name()))
			person.setFirst_name(form.getFirst_name());
		if (person.getMiddle_name() == null || person.getMiddle_name().equals("")
				|| !person.getMiddle_name().equals(form.getMiddle_name()))
			person.setMiddle_name(form.getMiddle_name());
		if (person.getLast_name() == null || person.getLast_name().equals("")
				|| !person.getLast_name().equals(form.getLast_name()))
			person.setLast_name(form.getLast_name());
		if (person.getSur_name() == null || person.getSur_name().equals("")
				|| !person.getSur_name().equals(form.getSur_name()))
			person.setSur_name(form.getSur_name());
		if (person.getGender() == null || person.getGender().equals("") || !person.getGender().equals(form.getGender()))
			person.setGender(form.getGender());
		if (person.getNic() == null || person.getNic().equals("") || !person.getNic().equals(form.getNic()))
			person.setNic(form.getNic());
		if (person.getNationality() == null || person.getNationality().equals("")
				|| !person.getNationality().equals(form.getNationality()))
			person.setNationality(form.getNationality());
		if (person.getBirth_day() == null || person.getBirth_day().getTime() != form.getBirth_day().getTime())
			person.setBirth_day(form.getBirth_day());
		if (person.getLiving_status() == null || person.getLiving_status() != form.getLiving_status())
			person.setLiving_status(form.getLiving_status());
		if (person.getStatus() == null || person.getStatus().equals("") || person.getStatus() != form.getStatus())
			person.setStatus(form.getStatus());
		if (person.getSys_add_date() == null && form.getSys_add_date() != null)
			person.setSys_add_date(form.getSys_add_date());
		logger.debug("------>>end setPersonData<<------");
		return person;
	}

	private List<PersonEmail> setEmailData(PersonForm form, Person person) {
		logger.debug("------>>start setEmailData<<------");
		List<PersonEmail> list = new ArrayList<>();
		logger.debug("------>> email : " + form.getEmail());
		if (form.getEmail() != null && !form.getEmail().equals("")) {
			PersonEmail personEmail = null;
			if (person != null) {
				personEmail = this.personEmailAccess.getByEmailAndPersonId(form.getEmail(), person.getId());
			}
			if (personEmail == null) {
				personEmail = new PersonEmail();
			}
			if(personEmail.getId()<=0&&form.getEmail_id()>0)
				personEmail.setId(form.getEmail_id());
			if (personEmail.getEmail() == null || personEmail.getEmail().equals("")
					|| !personEmail.getEmail().equals(form.getEmail()))
				personEmail.setEmail(form.getEmail());
			if (personEmail.getStatus() == null || personEmail.getStatus().equals("")
					|| !personEmail.getStatus().equals(form.getStatus()))
				personEmail.setStatus(form.getStatus());
			if (personEmail.getSysAddDate() == null)
				personEmail.setSysAddDate(new Date());
			if (person != null) {
				personEmail.setPerson(person);
				person.getPersonEmails().add(personEmail);
			}
			list.add(personEmail);
		}
		logger.debug("------>> emails : " + form.getEmails());
		if (form.getEmails() != null && form.getEmails().length > 0) {
			for (PersonEmailForm email_form : form.getEmails()) {
				PersonEmail personEmail = null;
				if (person != null) {
					personEmail = this.personEmailAccess.getByEmailAndPersonId(email_form.getEmail(), person.getId());
				}
				if (personEmail == null) {
					personEmail = new PersonEmail();
				}
				if(personEmail.getId()<=0&&email_form.getId()>0)
					personEmail.setId(email_form.getId());
				if (personEmail.getEmail() == null || personEmail.getEmail().equals("")
						|| !personEmail.getEmail().equals(email_form.getEmail()))
					personEmail.setEmail(email_form.getEmail());
				if (personEmail.getStatus() == null || personEmail.getStatus().equals("")
						|| !personEmail.getStatus().equals(email_form.getStatus()))
					personEmail.setStatus(email_form.getStatus());
				if (personEmail.getSysAddDate() == null)
					personEmail.setSysAddDate(new Date());
				if (person != null) {
					personEmail.setPerson(person);
					person.getPersonEmails().add(personEmail);
				}
				list.add(personEmail);
			}
		}
		logger.debug("------>>end setEmailData<<------");
		return list;
	}

	private List<PersonAddress> setAddressData(PersonForm form, Person person) {
		logger.debug("------>>start setAddressData<<------");
		List<PersonAddress> list = new ArrayList();
		logger.debug("------>> address: "+form.getAddressl1()+" "+form.getAddressl2()+" "+form.getAddressl3());
		if (form.getAddressl1() != null && form.getAddressl2() != null && !form.getAddressl1().equals("")
				&& !form.getAddressl2().equals("")) {
			PersonAddress personAddress = new PersonAddress();
			if(personAddress.getId()<=0)
				personAddress.setId(form.getAddress_id());
			if (personAddress.getAddressl1() == null || personAddress.getAddressl1().equals("")
					|| !personAddress.getAddressl1().equals(form.getAddressl1()))
				personAddress.setAddressl1(form.getAddressl1());
			if (personAddress.getAddressl2() == null || personAddress.getAddressl2().equals("")
					|| !personAddress.getAddressl2().equals(form.getAddressl2()))
				personAddress.setAddressl2(form.getAddressl2());
			if (personAddress.getAddressl3() == null || personAddress.getAddressl3().equals("")
					|| !personAddress.getAddressl3().equals(form.getAddressl3()))
				personAddress.setAddressl3(form.getAddressl3());
			if (personAddress.getAddressl4() == null || personAddress.getAddressl4().equals("")
					|| !personAddress.getAddressl4().equals(form.getAddressl4()))
				personAddress.setAddressl4(form.getAddressl4());
			if(personAddress.getSys_add_date()==null)
				personAddress.setSys_add_date(new Date());
			personAddress.setStatus("Y");
			if (person != null) {
				personAddress.setPerson(person);
				person.getPersonAddress().add(personAddress);
			}
			list.add(personAddress);
		}
		logger.debug("------>> addresses: "+form.getAddresses());
		if(form.getAddresses() != null && form.getAddresses().length>0) {
			for (PersonAddressForm personAddress_form : form.getAddresses()) {
				PersonAddress personAddress = null;
				if(personAddress_form.getId()>0)
					personAddress = this.personAddressAccess.getById(personAddress_form.getId());
				if(personAddress == null ) {
					personAddress = new PersonAddress();
				}
				if(personAddress.getId()<=0)
					personAddress.setId(personAddress_form.getId());
				if (personAddress.getAddressl1() == null || personAddress.getAddressl1().equals("")
						|| !personAddress.getAddressl1().equals(personAddress_form.getAddressl1()))
					personAddress.setAddressl1(personAddress_form.getAddressl1());
				if (personAddress.getAddressl2() == null || personAddress.getAddressl2().equals("")
						|| !personAddress.getAddressl2().equals(personAddress_form.getAddressl2()))
					personAddress.setAddressl2(personAddress_form.getAddressl2());
				if (personAddress.getAddressl3() == null || personAddress.getAddressl3().equals("")
						|| !personAddress.getAddressl3().equals(personAddress_form.getAddressl3()))
					personAddress.setAddressl3(personAddress_form.getAddressl3());
				if (personAddress.getAddressl4() == null || personAddress.getAddressl4().equals("")
						|| !personAddress.getAddressl4().equals(personAddress_form.getAddressl4()))
					personAddress.setAddressl4(personAddress_form.getAddressl4());
				if(personAddress.getSys_add_date()==null)
					personAddress.setSys_add_date(new Date());
				personAddress.setStatus(personAddress_form.getStatus());
				if (person != null) {
					personAddress.setPerson(person);
					person.getPersonAddress().add(personAddress);
				}
				list.add(personAddress);
			}
		}
		logger.debug("------>>end setAddressData<<------");
		return list;
	}

	private List<PersonContact> setContactData(PersonForm form, Person person) {
		logger.debug("------>>start setContactData<<------");
		List<PersonContact> list = new ArrayList();
		logger.debug("------>> contact1:"+form.getContact1());
		if (form.getContact1() != null && !form.getContact1().equals("")) {
			PersonContact personContact = null;
			if (person != null) {
				personContact = this.personContactAccess.getByContactAndPersonId(form.getContact1(), person.getId());
			}
			if (personContact == null) {
				personContact = new PersonContact();
			}
			if (personContact.getNumber() == null || personContact.getNumber().equals("")|| !form.getContact1().equals(personContact.getNumber())) {
				personContact.setNumber(form.getContact1());
			}
			if(personContact.getStatus()== null || personContact.getStatus().equals(""))
				personContact.setStatus("Y");
			if(personContact.getSysAddDate() == null)
				personContact.setSysAddDate(new Date());
			if (person != null) {
				personContact.setPerson(person);
				person.getPersonContacts().add(personContact);
			}
			list.add(personContact);
		}
		
		logger.debug("------>> contact2:"+form.getContact2());
		if (form.getContact2() != null && !form.getContact2().equals("")) {
			PersonContact personContact = null;
			if (person != null) {
				personContact = this.personContactAccess.getByContactAndPersonId(form.getContact2(), person.getId());
			}
			if (personContact == null) {
				personContact = new PersonContact();
			}
			if (personContact.getNumber() == null || personContact.getNumber().equals("")
					|| !form.getContact2().equals(personContact.getNumber())) {
				personContact.setNumber(form.getContact2());
			}
			if(personContact.getStatus()== null || personContact.getStatus().equals(""))
				personContact.setStatus("Y");
			if(personContact.getSysAddDate() == null)
				personContact.setSysAddDate(new Date());
			if (person != null) {
				personContact.setPerson(person);
				person.getPersonContacts().add(personContact);
			}
			list.add(personContact);
		}
		
		logger.debug("------>> contacts:"+form.getContacts());
		if(form.getContacts() != null && form.getContacts().length>0) {
			for (PersonContactForm personContact_form : form.getContacts()) {
				PersonContact personContact = null;
				if (person != null) {
					personContact = this.personContactAccess.getByContactAndPersonId(personContact_form.getNumber(), person.getId());
				}
				if (personContact == null) {
					personContact = new PersonContact();
				}
				if (personContact.getNumber() == null || personContact.getNumber().equals("")|| !personContact_form.getNumber().equals(personContact.getNumber())) {
					personContact.setNumber(personContact_form.getNumber());
				}
				if(personContact.getStatus()== null || personContact.getStatus().equals("") || !personContact.getStatus().equals(personContact_form.getStatus()))
					personContact.setStatus(personContact_form.getStatus());
				if(personContact.getSysAddDate() == null)
					personContact.setSysAddDate(new Date());
				if (person != null) {
					personContact.setPerson(person);
					person.getPersonContacts().add(personContact);
				}
				list.add(personContact);
			}
		}
		
		logger.debug("------>>end setContactData<<------");
		return list;
	}

	private void reFillForm(PersonForm form) {
		form.setSys_add_date(new Date());
	}

	private boolean checkUniqueValuesStatus(DataTransfer dataTrans) {
		logger.debug("------>>start checkUniqueValuesStatus<<------");
		boolean status = true;
		PersonForm form = (PersonForm) dataTrans.getInput("personForm");

		if (form.getEmail() != null && !form.getEmail().equals("")) {
			List<PersonEmail> list = this.personEmailAccess.getByEmail(form.getEmail());
			if (list != null) {
				if (list.size() > 0) {
					status = false;
					dataTrans.setStatus(Status.WARNING);
					dataTrans.addOutput(Key.WARNING, "email already exist");
				}
			}
		}

		if (form.getNic() != null && !form.getNic().equals("")) {
			Person person = this.personAccess.getByNIC(form.getNic());
			if (person != null) {
				status = false;
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "nic already exist");
			}
		}

		logger.debug("------>>end checkUniqueValuesStatus<<------");
		return status;
	}

}
