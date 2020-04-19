/**
 * 
 */
package gdc.person.common.form;

/**
 * @author suhada
 *
 */
public class RequestParamForm {

	/* for pagination */
	private int start;
	private int count;
	// ase or desc
	private String order;
	/*--------------------------------------------------------------------*/

	/* for response arc*/
	private String ctype;
	/*--------------------------------------------------------------------*/
	
	/* get with other details in other services */
	
	/*--------------------------------------------------------------------*/
	
	/**/
	private long id;
	/*--------------------------------------------------------------------*/
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
