package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the PATENT_EDIT_DATE database table.
 * 
 */
@Embeddable
public class PatentEditDatePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="INVENTION_ID", insertable=false, updatable=false)
	private Integer inventionId;

	@Column(name="EDIT_ITEM_LIST")
	private Integer editItemList;
	//private Integer years;	
	/*public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}*/
	public PatentEditDatePK() {
	}
	public Integer getInventionId() {
		return this.inventionId;
	}
	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
	}
	public Integer getEditItemList() {
		return this.editItemList;
	}
	public void setEditItemList(Integer editItemList) {
		this.editItemList = editItemList;
	}

}