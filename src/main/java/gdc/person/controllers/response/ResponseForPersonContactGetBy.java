/**
 * 
 */
package gdc.person.controllers.response;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.person.datamanager.pojo.PersonContact;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForPersonContactGetBy extends ResponseGeneratorImpl {

	private static final Logger logger = LoggerFactory.getLogger(ResponseForPersonContactGetBy.class);
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

		res.put(Key.INPUTS, inputs);
		res.put(Key.OUTPUTS, outputs);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see gdc.person.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public Object generate(DataTransfer dataTrans) {
		PersonContact personCOntact = (PersonContact)dataTrans.getOutput(Key.PERSON_CONTACT);
		
		return null;
	}

	private void processInputs(DataTransfer dataTrans, HashMap<String, Object> inputs) {
		try {
			inputs.put("form", dataTrans.getInput("reqParam"));
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
	}
	
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		
	}
}
