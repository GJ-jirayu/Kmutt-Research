package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the PATENT_INHERITED database table.
 * 
 */
@Embeddable
public class PatentInheritedPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="INVENTION_ID", insertable=false, updatable=false)
	private Integer inventionId;

	@Column(name="INHERITED_ITEM_LIST")
	private Integer inheritedItemList;

	public PatentInheritedPK() {
	}
	public Integer getInventionId() {
		return this.inventionId;
	}
	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
	}
	public Integer getInheritedItemList() {
		return this.inheritedItemList;
	}
	public void setInheritedItemList(Integer inheritedItemList) {
		this.inheritedItemList = inheritedItemList;
	}

}