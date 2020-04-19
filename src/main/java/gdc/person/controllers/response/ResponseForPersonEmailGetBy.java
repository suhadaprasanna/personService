/**
 * 
 */
package gdc.person.controllers.response;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.person.datamanager.pojo.PersonEmail;
import gdc.person.dto.PersonEmailDTO;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForPersonEmailGetBy extends ResponseGeneratorImpl {

	private static final Logger logger = LoggerFactory.getLogger(ResponseForPersonEmailGetBy.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see gdc.person.controllers.response.ResponseGenerator#generate(gdc.utility.
	 * dataservice.DataTransfer, java.util.HashMap)
	 */
	@Override
	public HashMap<String, Object> generate(DataTransfer dataTrans, HashMap<String, Object> res) {
		logger.debug("------>>Start generate<<------");
		// create inputs
		HashMap<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("form", dataTrans.getInput("reqParam"));

		// create outputs
		HashMap<String, Object> outputs = new HashMap<String, Object>();
		PersonEmail personEmail = (PersonEmail)dataTrans.getOutput("person_email");
		HashMap<String, Object> _personEmail = null;
		if(personEmail!=null) {
			_personEmail = new HashMap();
			_personEmail.put("id", personEmail.getId());
			_personEmail.put("email", personEmail.getEmail());
			_personEmail.put("status", personEmail.getStatus());
			_personEmail.put("person_id", personEmail.getPerson().getId());
		}
		outputs.put("person_email", _personEmail);
		
		res.put(Key.INPUTS, inputs);
		res.put(Key.OUTPUTS, outputs);
		logger.debug("------>>End generate<<------");
		return res;
	}
	/* (non-Javadoc)
	 * @see gdc.person.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public Object generate(DataTransfer dataTrans) {
		PersonEmail personEmail = (PersonEmail)dataTrans.getOutput(Key.PERSON_EMAIL);
		PersonEmailDTO emailDTO = new PersonEmailDTO();
		emailDTO.setId(personEmail.getId());
		emailDTO.setEmail(personEmail.getEmail());
		emailDTO.setStatus(personEmail.getStatus());
		emailDTO.setSysAddDate(personEmail.getSysAddDate());
		if(personEmail.getPerson()!=null)
			emailDTO.setPerson_id(personEmail.getId());
		
		return emailDTO;
	}

}
