/**
 * 
 */
package gdc.person.controllers.response;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gdc.person.common.form.PersonForm;
import gdc.person.datamanager.pojo.Person;
import gdc.person.datamanager.pojo.PersonAddress;
import gdc.person.datamanager.pojo.PersonContact;
import gdc.person.datamanager.pojo.PersonEmail;
import gdc.person.dto.PersonAddressDTO;
import gdc.person.dto.PersonContactDTO;
import gdc.person.dto.PersonDTO;
import gdc.person.dto.PersonEmailDTO;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForPersonAdd extends ResponseGeneratorImpl {

	private static final Logger logger = LoggerFactory.getLogger(ResponseForPersonAdd.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see gdc.person.controllers.response.ResponseGenerator#generate(gdc.utility.
	 * dataservice.DataTransfer, java.util.HashMap)
	 */
	@Override
	public HashMap<String, Object> generate(DataTransfer dataTrans, HashMap<String, Object> res) {
		// create inputs
		HashMap<String, Object> inputs = new HashMap<String, Object>();
		this.processInputs(dataTrans, inputs);
		
		// create outputs
		HashMap<String, Object> outputs = new HashMap<String, Object>();
		this.processOutputs(dataTrans, outputs);
		
		res.put(Key.INPUTS, inputs);
		res.put(Key.OUTPUTS, outputs);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see gdc.person.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public Object generate(DataTransfer dataTrans) {
		logger.debug("------>> start generate<<------");
		Person person = (Person)dataTrans.getOutput(Key.PERSON);
		PersonDTO personDTO = new PersonDTO();
		logger.debug("------>> person:"+person);
		if(person != null) {
			personDTO.setId(person.getId());
			personDTO.setFirst_name(person.getFirst_name());
			personDTO.setMiddle_name(person.getMiddle_name());
			personDTO.setLast_name(person.getLast_name());
			personDTO.setSur_name(person.getSur_name());
			personDTO.setGender(person.getGender());
			personDTO.setNic(person.getNic());
			personDTO.setStatus(person.getStatus());
			personDTO.setBirth_day(person.getBirth_day());
			personDTO.setSys_add_date(person.getSys_add_date());
			logger.debug("------>>person.getPersonContacts(): "+person.getPersonContacts());
			if(person.getPersonContacts()!=null && person.getPersonContacts().size()>0) {
				for (PersonContact contact : person.getPersonContacts()) {
					PersonContactDTO contactDTO = new PersonContactDTO();
					contactDTO.setId(contact.getId());
					contactDTO.setNumber(contact.getNumber());
					contactDTO.setStatus(contact.getStatus());
					contactDTO.setSysAddDate(contact.getSysAddDate());
					contactDTO.setPerson_id(person.getId());
					personDTO.getPersonContacts().add(contactDTO);
				}
			}
			logger.debug("------>>person.getPersonAddress(): "+person.getPersonAddress());
			if(person.getPersonAddress() != null && person.getPersonAddress().size()>0) {
				for (PersonAddress address : person.getPersonAddress()) {
					PersonAddressDTO addressDTO = new PersonAddressDTO();
					addressDTO.setId(address.getId());
					addressDTO.setAddressl1(address.getAddressl1());
					addressDTO.setAddressl2(address.getAddressl2());
					addressDTO.setAddressl3(address.getAddressl3());
					addressDTO.setAddressl4(address.getAddressl4());
					addressDTO.setStatus(address.getStatus());
					addressDTO.setSys_add_date(address.getSys_add_date());
					addressDTO.setPerson_id(person.getId());
					personDTO.getPersonAddress().add(addressDTO);
				}
			}
			logger.debug("------>>person.getPersonEmails(): "+person.getPersonEmails());
			if(person.getPersonEmails() != null && person.getPersonEmails().size()>0) {
				for (PersonEmail email : person.getPersonEmails()) {
					PersonEmailDTO emailDTO = new PersonEmailDTO();
					emailDTO.setId(email.getId());
					emailDTO.setEmail(email.getEmail());
					emailDTO.setStatus(email.getStatus());
					emailDTO.setSysAddDate(email.getSysAddDate());
					emailDTO.setPerson_id(person.getId());
					personDTO.getPersonEmails().add(emailDTO);
				}
			}
			
		}
		logger.debug("------>> end generate<<------");
		return personDTO;
	}

	private void processInputs(DataTransfer dataTrans, HashMap<String, Object> inputs) {
		logger.debug("------>>start processInputs<<------");
		try {
			inputs.put("form", dataTrans.getInput("reqParam"));
		}catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>end processInputs<<------");
	}
	
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		logger.debug("------>>start processOutputs<<------");
		try {
			Person person = (Person)dataTrans.getOutput(Key.POJO_PERSON);
			logger.debug("------>> person:"+person);
			HashMap _person = null;
			if(person != null) {
				_person = new HashMap();
				_person.put("id", person.getId());
				_person.put("first_name", person.getFirst_name());
				_person.put("middle_name", person.getMiddle_name());
				_person.put("last_name", person.getLast_name());
				_person.put("sur_name", person.getSur_name());
				_person.put("gender", person.getNic());
				_person.put("birth_day", person.getBirth_day());
				_person.put("status", person.getStatus());
				
				ArrayList _addresses = new ArrayList();
				if(person.getPersonAddress()!=null&&person.getPersonAddress().size()>0) {
					for(PersonAddress address:person.getPersonAddress()) {
						HashMap _address = new HashMap();
						_address.put("id", address.getId());
						_address.put("addressl1", address.getAddressl1());
						_address.put("addressl2", address.getAddressl2());
						_address.put("addressl3", address.getAddressl3());
						_address.put("addressl3", address.getAddressl3());
						_address.put("status", address.getStatus());
						_address.put("person_id", person.getId());
						_addresses.add(_address);
					}
				}
				_person.put("addresses", _addresses);
				
				ArrayList _contacts = new ArrayList();
				if(person.getPersonContacts()!=null&&person.getPersonContacts().size()>0) {
					for(PersonContact contact:person.getPersonContacts()) {
						HashMap _contact = new HashMap();
						_contact.put("id", contact.getId());
						_contact.put("number", contact.getNumber());
						_contact.put("status", contact.getStatus());
						_contact.put("person_id", person.getId());
						_contacts.add(_contact);
					}
				}
				_person.put("contacts", _contacts);
				
				ArrayList _emails = new ArrayList();
				if(person.getPersonEmails()!=null&&person.getPersonEmails().size()>0) {
					for(PersonEmail email:person.getPersonEmails()) {
						HashMap _email = new HashMap();
						_email.put("id", email.getId());
						_email.put("email", email.getEmail());
						_email.put("status", email.getStatus());
						_email.put("person_id", person.getId());
						_emails.add(_email);
					}
				}
				_person.put("emails", _emails);
			}
			outputs.put("person", _person);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>end processOutputs<<------");
	}

	
	
}
