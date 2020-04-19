/**
 * 
 */
package gdc.person.controllers.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
public class ResponseForPersonEmailGetLike extends ResponseGeneratorImpl {

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
		inputs.put("form", dataTrans.getInput("reqParam"));

		// create outputs
		HashMap<String, Object> outputs = new HashMap<String, Object>();
		List<PersonEmail> list = (List)dataTrans.getOutput("person_email_list");
		List _list = new ArrayList<>();
		if(list !=null && list.size()>0) {
			for (PersonEmail personEmail : list) {
				HashMap<String,Object> _personEmail = new HashMap();
				_personEmail.put("id", personEmail.getId());
				_personEmail.put("email", personEmail.getEmail());
				_personEmail.put("status", personEmail.getStatus());
				_personEmail.put("person_id", personEmail.getPerson().getId());
				_list.add(_personEmail);
			}
		}
		outputs.put("person_email_list", _list);
		
		res.put(Key.INPUTS, inputs);
		res.put(Key.OUTPUTS, outputs);
		return res;
	}

	/* (non-Javadoc)
	 * @see gdc.person.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public Object generate(DataTransfer dataTrans) {
		List<PersonEmail> list = (List)dataTrans.getOutput(Key.PERSON_EMAIL_LIST);
		List<PersonEmailDTO> _list = new ArrayList<PersonEmailDTO>();
		if(list !=null && list.size()>0) {
			for (PersonEmail personEmail : list) {
				PersonEmailDTO emailDTO = new PersonEmailDTO();
				emailDTO.setId(personEmail.getId());
				emailDTO.setEmail(personEmail.getEmail());
				emailDTO.setStatus(personEmail.getStatus());
				emailDTO.setSysAddDate(personEmail.getSysAddDate());
				if(personEmail.getPerson()!=null)
					emailDTO.setPerson_id(personEmail.getId());
				_list.add(emailDTO);
			}
		}
		return _list;
	}

}
