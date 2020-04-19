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
public class PersonAddressForm extends Form{
	
	private long id;
	private String addressl1;
	private String addressl2;
	private String addressl3;
	private String addressl4;
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sys_add_date;
	private long person_id;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddressl1() {
		return addressl1;
	}
	public void setAddressl1(String addressl1) {
		this.addressl1 = addressl1;
	}
	public String getAddressl2() {
		return addressl2;
	}
	public void setAddressl2(String addressl2) {
		this.addressl2 = addressl2;
	}
	public String getAddressl3() {
		return addressl3;
	}
	public void setAddressl3(String addressl3) {
		this.addressl3 = addressl3;
	}
	public String getAddressl4() {
		return addressl4;
	}
	public void setAddressl4(String addressl4) {
		this.addressl4 = addressl4;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getSys_add_date() {
		return sys_add_date;
	}
	public void setSys_add_date(Date sys_add_date) {
		this.sys_add_date = sys_add_date;
	}
	public long getPerson_id() {
		return person_id;
	}
	public void setPerson_id(long person_id) {
		this.person_id = person_id;
	}
	
}
