package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the RESEARCH_PROJECT_DOCUMENT database table.
 * 
 */
@Embeddable
public class ResearchProjectDocumentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_LIST")
	private Integer itemList;
	
	
	@Column(name="RESEARCH_PROJECT_ID", insertable=false, updatable=false)
	private Integer researchProjectId;

	@Column(name="DOCUMENT_ID")
	private Integer documentId;

	public ResearchProjectDocumentPK() {
	}
	public Integer getResearchProjectId() {
		return this.researchProjectId;
	}
	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}
	public Integer getDocumentId() {
		return this.documentId;
	}
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}
	
	public Integer getItemList() {
		return itemList;
	}
	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}

}