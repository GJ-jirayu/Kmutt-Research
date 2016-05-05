package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the PATENT_CREATOR database table.
 * 
 */
@Embeddable
public class PatentCreatorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="INVENTION_ID", insertable=false, updatable=false)
	private Integer inventionId;

	@Column(name="CREATOR_ITEM_LIST")
	private Integer creatorItemList;

	public PatentCreatorPK() {
	}
	public Integer getInventionId() {
		return this.inventionId;
	}
	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
	}
	public Integer getCreatorItemList() {
		return this.creatorItemList;
	}
	public void setCreatorItemList(Integer creatorItemList) {
		this.creatorItemList = creatorItemList;
	}

}