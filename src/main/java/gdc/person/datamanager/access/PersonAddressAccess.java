/**
 * 
 */
package gdc.person.datamanager.access;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.person.datamanager.dao.PersonAddressRepository;
import gdc.person.datamanager.pojo.PersonAddress;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;
import gdc.utility.validations.Validation;

/**
 * @author suhada
 *
 */
@Repository("personAddressAccess")
@Transactional
public class PersonAddressAccess {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonAddressAccess.class);
	
	@Autowired
	private PersonAddressRepository personAddressRepository;
	
	@Autowired
	private Validation validation;
	
	public DataTransfer save(DataTransfer dataTrans) {
		logger.debug("------>>start save<<------");
		PersonAddress personAddress = (PersonAddress)dataTrans.getInput("pojo_person_address");
		try {
			if(personAddress!=null) {
				personAddress = this.personAddressRepository.save(personAddress);
				if(personAddress.getId()>0) {
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
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>end save<<------");
		return dataTrans;
	}
	
	public PersonAddress getById(long id) {
		logger.debug("------>>start getById<<------");
		logger.debug("------>> id : "+id);
		PersonAddress personEmail = null;
		try {
			if(id>0) {
				Optional<PersonAddress> op = this.personAddressRepository.findById(id);
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
	
}
