/**
 * 
 */
package gdc.person.controllers.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author suhada
 *
 */
@Component
public class ResponseGeneratorFactoryUtil {

	public enum ResponseKey{
		PERSON_GET_ALL,PERSON_ADD,PERSON_UPDATE,PERSON_GET_BY,PERSON_GET_LIKE,
		PERSON_EMAIL_ADD,PERSON_EMAIL_GET_BY,PERSON_EMAIL_GET_LIKE,
		PERSON_CONTACT_ADD,PERSON_CONTACT_GET_LIKE,PERSON_CONTACT_GET_BY
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseGeneratorFactoryUtil.class);
	
	public Class getResponseClass(ResponseKey key) {
		Class type = null;
		switch (key) {
		case PERSON_GET_ALL:
			type = ResponseForPersonGetAll.class;
			break;
		case PERSON_ADD:
			type = ResponseForPersonAdd.class;
			break;
		case PERSON_UPDATE:
			type = ResponseForPersonAdd.class;
			break;
		case PERSON_GET_BY:
			type = ResponseForPersonGetBy.class;
			break;
		case PERSON_GET_LIKE:
			type = ResponseForPersonGetLike.class;
			break;
		case PERSON_EMAIL_ADD:
			type = ResponseForPersonEmailAdd.class;
			break;
		case PERSON_EMAIL_GET_BY:
			type = ResponseForPersonEmailGetBy.class;
			break;
		case PERSON_EMAIL_GET_LIKE:
			type = ResponseForPersonEmailGetLike.class;
			break;
		case PERSON_CONTACT_ADD:
			type = ResponseForPersonContactAdd.class;
			break;
		case PERSON_CONTACT_GET_BY:
			type = ResponseForPersonContactGetBy.class;
			break;
		default:
			type = null;
			break;
		}
		return type;
	}
}
