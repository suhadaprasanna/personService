/**
 * 
 */
package gdc.person.common.form.validation;

import java.util.Date;

import org.springframework.stereotype.Component;

import gdc.person.common.form.Form;
import gdc.person.common.form.PersonAddressForm;
import gdc.person.common.form.PersonForm;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Component
public class PersonAddressFormValidation implements FormValidation {

	/* (non-Javadoc)
	 * @see gdc.person.common.form.validation.FormValidation#validate(gdc.person.common.form.Form, gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public boolean validate(Form _form, DataTransfer dataTrans) {
		PersonAddressForm form = (PersonAddressForm) _form;
		boolean status = true;
		if(form.getAddressl1() == null || form.getAddressl1().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "address line 1 required");
			status=false;
		}
		if(form.getAddressl2() == null || form.getAddressl2().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "address line 2 required");
			status=false;
		}
		if(form.getAddressl3() == null || form.getAddressl3().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "address line 3 required");
			status=false;
		}
		if(form.getAddressl4()!=null && !form.getAddressl4().equals("")
				&& (form.getAddressl1()==null || form.getAddressl1().equals("")
				|| form.getAddressl2()==null || form.getAddressl2().equals("")
				|| form.getAddressl3()==null || form.getAddressl3().equals(""))) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "enter complete address");
			status=false;
		}
		return status;
	}

}
