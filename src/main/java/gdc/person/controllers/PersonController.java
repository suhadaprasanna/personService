package gdc.person.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gdc.person.common.form.PersonForm;
import gdc.person.common.form.RequestParamForm;
import gdc.person.controllers.response.ResponseGeneratorFactory;
import gdc.person.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.person.service.PersonService;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

@RestController
@RequestMapping(value = { "/person" })
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;
	
	@Autowired
	ResponseGeneratorFactory resFactory;

	@RequestMapping(value = { "", "/" })
	public String person() {
		logger.debug("------>>start person<<------");
		return "Welcome to person service (person)";
	}

	@PostMapping(value = { "/add" })
	public Object addPerson(@RequestBody PersonForm form, HttpServletRequest req) {
		logger.debug("------>>start addPerson<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("personForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans = this.personService.addPerson(dataTrans);
		
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_ADD,req);
		logger.debug("------>>end addPerson<<------");
		return res;
	}

	@PostMapping(value = { "/update" })
	public Object updatePerson(@RequestBody PersonForm form, HttpServletRequest req) {
		logger.debug("------>>start updatePerson<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans.addInput("personForm", form);
		dataTrans = this.personService.updatePerson(dataTrans);
		
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_UPDATE,req);
		logger.debug("------>>end updatePerson<<------");
		return res;
	}

	@PostMapping(value = { "/delete/id/{id}" })
	public Object deletePerson(@PathVariable(value = "id") long id, HttpServletRequest req) {
		logger.debug("------>>start deletePerson<<------");
		DataTransfer dataTrans = new DataTransfer();
		logger.debug("----id : " + id);
		if (id <= 0) {
			dataTrans.addOutput(Key.WARNING_LIST, "Please enter valid id");
			return dataTrans;
		}
		dataTrans.addInput("id", id);
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans = this.personService.deletePerson(dataTrans);
		logger.debug("------>>end deletePerson<<------");
		return dataTrans;
	}

	@PostMapping(value = { "/get" })
	public Object getAllPerson(
			@RequestParam(name="count",required=false,defaultValue="0")int count,
			@RequestParam(name="start",required=false,defaultValue="0")int start,
			@RequestParam(name="order",required=false,defaultValue="ASC")String order,
			@RequestParam(name="other_details",required=false,defaultValue="null")String other_details,
			@RequestParam(name="ctype",required=false,defaultValue="N/A")String ctype,
			HttpServletRequest req) {
		logger.debug("------>>start getAllPerson<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("count", count);
		dataTrans.addInput("start", start);
		dataTrans.addInput("order", order);
		dataTrans.addInput("other_details", other_details);
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans = this.personService.getPersonList(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_GET_ALL,req);
		logger.debug("------>>end getAllPerson<<------");
		return res;
	}

	@PostMapping(value = { "/get/id/{id}" })
	public Object getPersonById(
			@PathVariable(value = "id") long id, 
			@ModelAttribute("form") RequestParamForm form,
			HttpServletRequest req) {
		logger.debug("------>>start getPersonById<<------");
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("id", id+"");
		
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", reqParam);
		dataTrans.addInput("id", id);
		
		dataTrans = this.personService.getById(dataTrans);
		
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_GET_BY,req);
		logger.debug("------>>end getPersonById<<------");
		return res;
	}
	
	@PostMapping(value = { "/get/ids/{ids}" })
	public Object getPersonsByIds(
			@PathVariable(value = "ids") Long[] id_list, 
			@ModelAttribute("form") RequestParamForm form,
			HttpServletRequest req) {
		logger.debug("------>>start getPersonsByIds<<------");
		
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", form);
		dataTrans.addInput("id_list", id_list);
		
		dataTrans = this.personService.getByIds(dataTrans);
		
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_GET_ALL,req);
		logger.debug("------>>end getPersonsByIds<<------");
		return res;
	}

	@PostMapping(value = { "/get/nic/{nic}" })
	public Object getPersonByNIC(@PathVariable(value = "nic") String nic,HttpServletRequest req) {
		logger.debug("------>>start getPersonByNIC<<------");
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("nic", nic);
		
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("nic", nic);
		dataTrans.addInput("reqParam", reqParam);
		dataTrans = this.personService.getByNIC(dataTrans);
		
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_GET_BY,req);
		logger.debug("------>>end getPersonByNIC<<------");
		return res;
	}

	@PostMapping(value = { "/get/niclike/{nic}" })
	public Object getPersonByNICLike(@PathVariable(value = "nic") String nic,HttpServletRequest req) {
		logger.debug("------>>start getPersonByNICLike<<------");
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("nic", nic);

		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", reqParam);
		dataTrans.addInput("nic", nic);
		
		dataTrans = this.personService.getByNICLike(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_GET_LIKE,req);
		logger.debug("------>>end getPersonByNICLike<<------");
		return res;
	}

	@PostMapping(value = { "/get/email/{email}" })
	public Object getPersonByEmail(@PathVariable(value = "email") String email,HttpServletRequest req) {
		logger.debug("------>>start getPersonByEmail<<------");
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("email", email);

		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", reqParam);
		dataTrans.addInput("email", email);
		
		dataTrans = this.personService.getByEmail(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_GET_BY,req);
		logger.debug("------>>end getPersonByEmail<<------");
		return res;
	}

	@PostMapping(value = { "/get/emaillike/{email}" })
	public Object getPersonByEmailLike(@PathVariable(value = "email") String email,HttpServletRequest req) {
		logger.debug("------>>start getPersonByEmailLike<<------");
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("email", email);

		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", reqParam);
		dataTrans.addInput("email", email);
		dataTrans = this.personService.getByEmailLike(dataTrans);
		
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_GET_LIKE,req);
		logger.debug("------>>end getPersonByEmailLike<<------");
		return res;
	}

}
