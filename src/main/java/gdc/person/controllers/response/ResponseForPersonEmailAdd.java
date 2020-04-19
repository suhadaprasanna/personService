/**
 * 
 */
package gdc.person.controllers.response;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForPersonEmailAdd extends ResponseGeneratorImpl {

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
		// TODO Auto-generated method stub
		return null;
	}

}
