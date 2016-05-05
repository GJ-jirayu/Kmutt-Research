package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the PATENT_RIGHTHOLDER database table.
 * 
 */
@Embeddable
public class PatentRightholderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="INVENTION_ID", insertable=false, updatable=false)
	private Integer inventionId;

	@Column(name="RIGHTHOLDER_ITEM_LIST")
	private Integer rightholderItemList;

	public PatentRightholderPK() {
	}
	public Integer getInventionId() {
		return this.inventionId;
	}
	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
	}
	public Integer getRightholderItemList() {
		return this.rightholderItemList;
	}
	public void setRightholderItemList(Integer rightholderItemList) {
		this.rightholderItemList = rightholderItemList;
	}

	
}