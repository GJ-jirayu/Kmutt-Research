package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the COPYRIGHT_CREATOR database table.
 * 
 */
@Embeddable
public class CopyrightCreatorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COPYRIGHT_ID", insertable=false, updatable=false)
	private Integer copyrightId;

	@Column(name="CREATOR_ITEM_LIST")
	private Integer creatorItemList;

	public CopyrightCreatorPK() {
	}
	public Integer getCopyrightId() {
		return this.copyrightId;
	}
	public void setCopyrightId(Integer copyrightId) {
		this.copyrightId = copyrightId;
	}
	public Integer getCreatorItemList() {
		return this.creatorItemList;
	}
	public void setCreatorItemList(Integer creatorItemList) {
		this.creatorItemList = creatorItemList;
	}

}