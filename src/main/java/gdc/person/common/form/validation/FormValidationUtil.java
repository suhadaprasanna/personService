
/**
 * 
 */
package gdc.person.common.form.validation;


/**
 * @author suhada
 *
 */

public class FormValidationUtil {
	
	public enum FormKey {
		PERSONFORM,PERSONEMAILFORM,PERSONCONTACTFORM
	}
	
	public static Class getFormClass(FormKey key) {
		if(key == FormKey.PERSONFORM) {
			return PersonFormValidation.class;
		}else if(key == FormKey.PERSONEMAILFORM) {
			return PersonEmailFormValidator.class;
		}else if(key == FormKey.PERSONCONTACTFORM) {
			return PersonContactFormValidator.class;
		}else {
			return null;
		}
	}
}
