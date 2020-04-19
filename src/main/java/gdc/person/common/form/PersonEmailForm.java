/**
 * 
 */
package gdc.person.common.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author suhada
 *
 */
public class PersonEmailForm extends Form{
	
	private long id;
	private String email;
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sys_add_date;
	private long person_id;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the sys_add_date
	 */
	public Date getSys_add_date() {
		return sys_add_date;
	}
	/**
	 * @param sys_add_date the sys_add_date to set
	 */
	public void setSys_add_date(Date sys_add_date) {
		this.sys_add_date = sys_add_date;
	}
	/**
	 * @return the person_id
	 */
	public long getPerson_id() {
		return person_id;
	}
	/**
	 * @param person_id the person_id to set
	 */
	public void setPerson_id(long person_id) {
		this.person_id = person_id;
	}
	
	
}
