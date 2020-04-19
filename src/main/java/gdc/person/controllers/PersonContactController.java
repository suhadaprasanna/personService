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

import gdc.person.common.form.PersonContactForm;
import gdc.person.controllers.response.ResponseGeneratorFactory;
import gdc.person.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.person.service.PersonContactService;
import gdc.utility.dataservice.DataTransfer;

@RestController
@RequestMapping(value= {"/personcontact"})
public class PersonContactController {

	private static final Logger logger = LoggerFactory.getLogger(PersonContactController.class);
	
	@Autowired
	private PersonContactService personContactService;
	
	@Autowired
	ResponseGeneratorFactory resFactory;
	
	@RequestMapping(value= {"","/"})
	public Object personContact() {
		return "Welcome to person service (person contact)";
	}
	
	@PostMapping(value= {"/add"})
	public Object addContact(@ModelAttribute("personContactForm") PersonContactForm form, HttpServletRequest req) {
		logger.debug("------>>start addContact()<<------");
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans.addInput("personContactForm", form);
		
		this.personContactService.addContact(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_CONTACT_ADD,req);
		logger.debug("------>>start addContact()<<------");
		return res;
	}
	
	@PostMapping(value= {"/get/id","/get/id/{id}"})
	public Object getContactById(@PathVariable(value="id",required=false) Long id,HttpServletRequest req) {
		logger.debug("------>>start getContactById<<------");
		
		if(id == null || id.longValue()<=0) {
			if(req.getParameter("id") !=null ) {				
				id = Long.parseLong(req.getParameter("id"));
			}
		}
		logger.debug("------>> id:"+id);
		
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("id", id+"");
		
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addInput("reqParam", reqParam);
		dataTrans.addInput("id", id);
		
		dataTrans = this.personContactService.getById(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_CONTACT_GET_BY,req);
		logger.debug("------>>end getContactById<<------");
		return res;
	}
	
	@PostMapping(value= {"/get/pid","/get/perosnid/{perosnid}"})
	public Object getPersonContactByPersonId(@PathVariable(value="perosnid")Long personId,HttpServletRequest req) {
		logger.debug("------>>Start getPersonContactByPersonId()<<------");
		
		if(personId==null || personId.longValue()<=0 ) {
			if(req.getParameter("person_id") !=null ) {
				personId = Long.parseLong(req.getParameter("person_id"));
			}
		}
		
		logger.debug("------>> person id:"+personId);
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("person_id", personId+"");
		
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addInput("personId", personId);
		dataTrans = this.personContactService.getByPersonID(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_CONTACT_GET_LIKE,req);
		logger.debug("------>>End getPersonContactByPersonId()<<------");
		return res;
	}
	
	@PostMapping(value= {"/get/nic","/get/nic/{nic}"})
	public Object getPersonContactByNIC(@PathVariable(value="nic")String nic,HttpServletRequest req) {
		logger.debug("------>> Start getPersonContactByNIC()<<------");
		
		if(nic==null || nic.equals("") ) {
			if(req.getParameter("person_id") !=null ) {
				nic = req.getParameter("nic");
			}
		}
		logger.debug("------>> nic:"+nic);
		HashMap<String,String> reqParam = new HashMap<>();
		reqParam.put("nic", nic+"");
		DataTransfer  dataTrans = new DataTransfer();
		dataTrans.addInput("nic", nic);
		dataTrans = this.personContactService.getByPersonNIC(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.PERSON_CONTACT_GET_BY,req);
		logger.debug("------>> End getPersonContactByNIC()<<------");
		return res;
	}
	
}
