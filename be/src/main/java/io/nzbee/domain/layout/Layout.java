package io.nzbee.domain.layout;

/* 
 * a layout has an association with many categories or many brands
 * we keep it generic for now to cater for both. The order in which the objects 
 * are pulled should not be included / pollute the domain model since the order
 * of object is not a domain model concern, in fact we don't even need to 
 * worry about it in the ORM since the attribute will only be used in the order by
 * clause of the SQL query. In regarding to persisting categories / brands in a specific order 
 * a DTO can be used to read-in the order, sort by that order property, and then persist
 * objects to the DB in that order, it's up-to the adapter to remove the existing mappings in the 
 * DB and persist new ones
 */
public class Layout {
	
	private String layoutCode;
	
	private String layoutDesc;
	
	public Layout(String layoutCode,
				  String layoutDesc) {
		this.layoutCode = layoutCode;
		this.layoutDesc = layoutDesc;
	}
	
	public String getLayoutCode() {
		return layoutCode;
	}

	public String getLayoutDesc() {
		return layoutDesc;
	}
	
}
