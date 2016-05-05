package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the PATENT_DOCUMENT database table.
 * 
 */
@Embeddable
public class PatentDocumentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_LIST")
	private Integer itemList;

	@Column(name="INVENTION_ID", insertable=false, updatable=false)
	private Integer inventionId;

	@Column(name="DOCUMENT_ID")
	private Integer documentId;

	public PatentDocumentPK() {
	}
	public Integer getItemList() {
		return this.itemList;
	}
	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}
	public Integer getInventionId() {
		return this.inventionId;
	}
	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
	}
	public Integer getDocumentId() {
		return this.documentId;
	}
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}


}