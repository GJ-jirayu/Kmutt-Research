package th.ac.kmutt.research.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the COPYRIGHT_DOCUMENT database table.
 * 
 */
@Embeddable
public class CopyrightDocumentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_LIST")
	private Integer itemList;

	@Column(name="COPYRIGHT_ID", insertable=false, updatable=false)
	private Integer copyrightId;

	public CopyrightDocumentPK() {
	}

	public Integer getItemList() {
		return itemList;
	}

	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}

	public Integer getCopyrightId() {
		return copyrightId;
	}

	public void setCopyrightId(Integer copyrightId) {
		this.copyrightId = copyrightId;
	}
	
}