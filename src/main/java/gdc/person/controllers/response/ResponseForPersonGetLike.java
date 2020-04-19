/**
 * 
 */
package gdc.person.controllers.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
public class ResponseForPersonGetLike extends ResponseGeneratorImpl {

	private static final Logger logger = LoggerFactory.getLogger(ResponseForPersonGetLike.class);

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
		
		
		// create outputs
		HashMap<String, Object> outputs = new HashMap<String, Object>();
		this.processOutputs(dataTrans, outputs);
		
		res.put(Key.INPUTS, inputs);
		res.put(Key.OUTPUTS, outputs);
		return null;
	}

	/* (non-Javadoc)
	 * @see gdc.person.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public Object generate(DataTransfer dataTrans) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<Person> list = (List) dataTrans.getOutput(Key.PERSON_LIST);
		
		ArrayList<PersonDTO> _list = new ArrayList<PersonDTO>();
		
		if(list != null && list.size()> 0) {
			for (Person person : list) {
				PersonDTO personDTO = new PersonDTO();
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
				_list.add(personDTO);
			}
		}
		return _list;
	}

	private void processInputs(DataTransfer dataTrans, HashMap<String, Object> inputs) {
		try {
			inputs.put("form", dataTrans.getInput("reqParam"));
		} catch (Exception e) {
			logger.debug("------>> Error",e);
		}
	}
	
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		try {
			List<Person> list = (List) dataTrans.getOutput(Key.PERSON_LIST);
			ArrayList person_list = new ArrayList();
			if (list.size() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for (Person person : list) {
					HashMap<String, Object> _person = new HashMap<String, Object>();
					_person.put("id", person.getId());
					_person.put("first_name", person.getFirst_name());
					_person.put("last_name", person.getLast_name());
					_person.put("middle_name", person.getMiddle_name());
					_person.put("sur_name", person.getSur_name());
					_person.put("nic", person.getNic());
					_person.put("gender", person.getGender());
					_person.put("status", person.getStatus());
					_person.put("birth_day", person.getBirth_day() != null ? sdf.format(person.getBirth_day()) : "-");
					if (person.getPersonEmails().size() > 0) {
						ArrayList person_email_list = new ArrayList();
						for (PersonEmail email : person.getPersonEmails()) {
							HashMap<String, Object> _email = new HashMap<String, Object>();
							_email.put("email", email.getEmail());
							_email.put("status", email.getStatus());
							_email.put("id", email.getId());
							person_email_list.add(_email);
						}
						_person.put("emails", person_email_list);
					}
					if (person.getPersonContacts().size() > 0) {
						ArrayList person_contact_list = new ArrayList();
						for (PersonContact contact : person.getPersonContacts()) {
							HashMap<String, Object> _contact = new HashMap<String, Object>();
							_contact.put("number", contact.getNumber());
							_contact.put("status", contact.getStatus());
							_contact.put("id", contact.getId());
							person_contact_list.add(_contact);
						}
						_person.put("contacts", person_contact_list);
					}
					if (person.getPersonAddress().size() > 0) {
						ArrayList person_address_list = new ArrayList();
						for (PersonAddress address : person.getPersonAddress()) {
							HashMap<String, Object> _address = new HashMap<String, Object>();
							_address.put("addressl1", address.getAddressl1());
							_address.put("addressl2", address.getAddressl2());
							_address.put("addressl3", address.getAddressl3());
							_address.put("addressl4", address.getAddressl4());
							_address.put("status", address.getStatus());
							_address.put("id", address.getId());
							person_address_list.add(_address);
						}
						_person.put("addresses", person_address_list);
					}
					person_list.add(_person);
				}
			}
			outputs.put("person_list", person_list);

		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
	}
}
