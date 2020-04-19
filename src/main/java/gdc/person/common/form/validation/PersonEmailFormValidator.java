/**
 * 
 */
package gdc.person.common.form.validation;

import org.springframework.stereotype.Component;

import gdc.person.common.form.Form;
import gdc.person.common.form.PersonEmailForm;
import gdc.person.common.form.PersonForm;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Component
public class PersonEmailFormValidator implements FormValidation{

	/* (non-Javadoc)
	 * @see gdc.person.common.form.validation.FormValidation#validate(gdc.person.common.form.Form, gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public boolean validate(Form _form, DataTransfer dataTrans) {
		PersonEmailForm form = (PersonEmailForm) _form;
		boolean status = true;
		if(form.getEmail() != null && !form.getEmail().equals("") && !this.validation.isEmail(form.getEmail())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "enter valid email");
			status=false;
		}
		return status;
	}

}
