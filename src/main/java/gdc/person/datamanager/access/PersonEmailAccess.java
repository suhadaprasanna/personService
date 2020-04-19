/**
 * 
 */
package gdc.person.datamanager.access;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.person.datamanager.dao.PersonEmailRepository;
import gdc.person.datamanager.pojo.PersonEmail;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;
import gdc.utility.validations.Validation;

/**
 * @author suhada
 *
 */
@Repository("personEmailAccess")
@Transactional
public class PersonEmailAccess {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonEmailAccess.class);
	
	@Autowired
	private PersonEmailRepository personEmailRepository;
	
	@Autowired
	private Validation validation;
	
	public DataTransfer save(DataTransfer dataTrans) {
		logger.debug("------>>start save<<------");
		PersonEmail personEmail = (PersonEmail)dataTrans.getInput("pojo_person_email");
		try {
			if(personEmail!=null) {
				personEmail = this.personEmailRepository.save(personEmail);
				if(personEmail.getId()>0) {
					dataTrans.setStatus(Status.SUCCESS);
					dataTrans.addOutput(Key.MESSAGE, "save success");
				}else{
					dataTrans.setStatus(Status.FAIL);
					dataTrans.addOutput(Key.MESSAGE, "save failed");
				}
			}else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.MESSAGE, "not found details");
			}
		}catch (Exception e) {
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.MESSAGE, "something went wrong");
			logger.error("Error in save : "+e);
		}
		logger.debug("------>>end save<<------");
		return dataTrans;
	}
	
	public PersonEmail getById(long id) {
		logger.debug("------>>start getById<<------");
		logger.debug("------>> id : "+id);
		PersonEmail personEmail = null;
		try {
			if(id>0) {
				Optional<PersonEmail> op = this.personEmailRepository.findById(id);
				if(op.isPresent()) {
					personEmail = op.get();
				}
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> personEmail : "+personEmail);
		logger.debug("------>>end getById<<------");
		return personEmail;
	}
	
	public List<PersonEmail> getByEmail(String email) {
		logger.debug("------>>start getByEmail<<------");
		List<PersonEmail> list = null;
		try {
			logger.debug("------>> email: "+email);
			if(email != null && !email.equals("") && this.validation.isEmail(email)) {
				list = this.personEmailRepository.findByEmail(email);
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>end getByEmail<<------");
		return list;
	}
	
	public List<PersonEmail> getByEmailLike(String email) {
		logger.debug("------>>start getByEmail<<------");
		List<PersonEmail> list = null;
		try {
			if(email == null || email.equals("")) {
				list = this.personEmailRepository.findByEmailLike(email);
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>end getByEmail<<------");
		return list;
	}
	
	public List<PersonEmail> getByStatus(String status) {
		logger.debug("------>>start getByStatus<<------");
		List<PersonEmail> list = null;
		try {
			if(status != null || !status.equals("")) {
				list = this.personEmailRepository.findByStatus(status);
			}
			
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>end getByStatus<<------");
		return list;
	}

	public PersonEmail getByEmailAndPersonId(String email, Long id) {
		logger.debug("------>>start getByEmailAndPersonId<<------");
		PersonEmail personEmail = null;
		try {
			if(email != null || !email.equals("") && id > 0) {
				personEmail = this.personEmailRepository.findbyEmailAndPersonId(email,id);
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> personEmail: "+personEmail);
		logger.debug("------>>end getByEmailAndPersonId<<------");
		return personEmail;
	}
}
