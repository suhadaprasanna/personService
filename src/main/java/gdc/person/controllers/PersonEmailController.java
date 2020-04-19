/**
 * 
 */
package gdc.person.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdc.person.common.form.PersonEmailForm;
import gdc.person.controllers.response.ResponseGeneratorFactory;
import gdc.person.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.person.service.PersonEmailService;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping(value= {"/personemail"})
public class PersonEmailController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonEmailController.class);
	
	@Autowired
	private PersonEmailService personEmailService;
	
	@Autowired
	ResponseGeneratorFactory resFactory;

	@RequestMapping(value= {"","/"})
	public Object personEnaul() {
		return "Welcome to person service (person email)";
	}
	
	@PostMapping(value= {"/add"})
	public Object addEmail(@ModelAttribute("personEmailForm") PersonEmailForm form, HttpServletRequest req) {
		logger.debug("------>>start addEmail()<------");
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans.addInput("personEmailForm", form);
		this.personEmailService.addEmail(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_EMAIL_ADD,req);
		logger.debug("------>>start addEmail()<<------");
		return res;
	}
	
	@PostMapping(value= {"/update"})
	public Object updateEmail(@ModelAttribute("personEmailForm") PersonEmailForm form, HttpServletRequest req) {
		logger.debug("----start updateEmail()----");
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans.addInput("personEmailForm", form);
		
		logger.debug("------>>start updateEmail()<<------");
		return dataTrans;
	}
	
	@PostMapping(value= {"/get"})
	public Object getEmail() {
		logger.debug("------>>start getEmail()<<------");
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addOutput(Key.MESSAGE, "N/A");
		logger.debug("---->>end getEmail()----");
		return dataTrans;
	}
	
	@PostMapping(value= {"/get/id/{id}"})
	public Object getEmailById(@PathVariable(value = "id") long id, HttpServletRequest req) {
		logger.debug("------>>start getEmailById()<<------");
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("id", id+"");
		
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", reqParam);
		dataTrans.addInput("id", id);
		
		dataTrans = this.personEmailService.getById(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_EMAIL_GET_BY,req);
		logger.debug("------>>end getEmailById()<<------");
		return res;
	}
	
	@PostMapping(value= {"/get/email/{email}"})
	public Object getEmailByEmail(@PathVariable(value = "email") String email, HttpServletRequest req) {
		logger.debug("------>>start getEmailByEmail()<<------");
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("email", email);
		
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", reqParam);
		dataTrans.addInput("email", email);
		
		dataTrans = this.personEmailService.getByEmail(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_EMAIL_GET_LIKE,req);
		logger.debug("------>>end getEmailByEmail()<<------");
		return res;
	}
	
	@PostMapping(value= {"/get/emaillike/{email}"})
	public Object getEmailByEmailLike(@PathVariable(value = "email") String email, HttpServletRequest req) {
		logger.debug("------>>start getEmailByEmailLike()<<------");
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("email", email);
		
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", reqParam);
		dataTrans.addInput("email", email);
		
		dataTrans = this.personEmailService.getByEmailLike(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_EMAIL_GET_LIKE,req);
		logger.debug("------>>end getEmailByEmailLike()<<------");
		return res;
	}
	
	@PostMapping(value= {"/get/status/{status}"})
	public Object getEmailByStatus(@PathVariable(value = "status") String status, HttpServletRequest req) {
		logger.debug("------>>start getEmailByStatus()<<------");
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("status", status);
		
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", reqParam);
		dataTrans.addInput("status", status);
		
		dataTrans = this.personEmailService.getByStatus(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_EMAIL_GET_LIKE,req);
		logger.debug("------>>end getEmailByStatus()<<------");
		return res;
	}
	
}
