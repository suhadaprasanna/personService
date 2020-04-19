/**
 * 
 */
package gdc.person.common.form.validation;

import java.util.Date;

import org.springframework.stereotype.Component;

import gdc.person.common.form.Form;
import gdc.person.common.form.PersonForm;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Component
public class PersonFormValidation implements FormValidation {

	/* (non-Javadoc)
	 * @see gdc.person.common.form.validation.FormValidation#validate(gdc.person.common.form.Form, gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public boolean validate(Form _form, DataTransfer dataTrans) {
		PersonForm form = (PersonForm) _form;
		boolean status = true;
		
		if(form.getSur_name() != null && !form.getSur_name().equals("") && !this.validation.isString(form.getSur_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "sur name is not a valide name");
			status=false;
		}
		
		if(form.getFirst_name() == null || form.getFirst_name().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "first name required");
			status=false;
		}else if(form.getFirst_name() != null && !form.getFirst_name().equals("") && !this.validation.isString(form.getFirst_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "first name is not a valide name");
			status=false;
		}
		if(form.getMiddle_name() != null && !form.getMiddle_name().equals("") && !this.validation.isString(form.getMiddle_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "middle name is not a valide name");
			status=false;
		}else if(form.getLast_name() == null || form.getLast_name().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "last name required");
			status=false;
		}
		
		if(form.getLast_name() != null && !form.getLast_name().equals("") && !this.validation.isString(form.getLast_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "last name is not a valide name");
			status=false;
		}
		
		if(form.getEmail() != null && !form.getEmail().equals("") && !this.validation.isEmail(form.getEmail())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "enter valid email");
			status=false;
		}
		if(form.getBirth_day() != null && form.getBirth_day().getTime() < new Date().getTime()) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "enter valid birth day");
			status=false;
		}
		if(form.getNic() != null && !form.getNic().equals("") && !this.validation.isNIC(form.getNic())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "not a valid nic");
			status=false;
		}
		if((form.getAddressl1() != null && !form.getAddressl1().equals("")) 
				|| (form.getAddressl2() != null && !form.getAddressl2().equals(""))
				|| (form.getAddressl3() != null && !form.getAddressl3().equals(""))
				|| (form.getAddressl4() != null && !form.getAddressl4().equals(""))) {
			if(form.getAddressl1().equals("") && 
					(!form.getAddressl2().equals("") || !form.getAddressl3().equals("") || !form.getAddressl4().equals(""))) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING_LIST, "entered address invalid format");
				status=false;
			}
			if(form.getAddressl2().equals("") && 
					(!form.getAddressl3().equals("") || !form.getAddressl4().equals(""))) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING_LIST, "entered address invalid format");
				status=false;
			}
			if(form.getAddressl3().equals("") && !form.getAddressl4().equals("")) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING_LIST, "entered address invalid format");
				status=false;
			}
		}
		return status;
	}

}
