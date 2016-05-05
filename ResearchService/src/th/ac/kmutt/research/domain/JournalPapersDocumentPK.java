package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the JOURNAL_PAPERS_DOCUMENT database table.
 * 
 */
@Embeddable
public class JournalPapersDocumentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_LIST")
	private Integer itemList;
	
	@Column(name="JOURNAL_PAPERS_ID", insertable=false, updatable=false)
	private Integer journalPapersId;

	@Column(name="DOCUMENT_ID")
	private Integer documentId;

	public JournalPapersDocumentPK() {
	}
	public Integer getJournalPapersId() {
		return this.journalPapersId;
	}
	public void setJournalPapersId(Integer journalPapersId) {
		this.journalPapersId = journalPapersId;
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