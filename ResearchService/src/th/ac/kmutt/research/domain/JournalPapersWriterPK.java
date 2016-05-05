package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the JOURNAL_PAPERS_WRITER database table.
 * 
 */
@Embeddable
public class JournalPapersWriterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_LIST")
	private Integer itemList;

	@Column(name="JOURNAL_PAPERS_ID", insertable=false, updatable=false)
	private Integer journalPapersId;

	@Column(name="WRITER_ITEM_ID")
	private Integer writerItemId;

	public JournalPapersWriterPK() {
	}
	public Integer getItemList() {
		return this.itemList;
	}
	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}
	public Integer getJournalPapersId() {
		return this.journalPapersId;
	}
	public void setJournalPapersId(Integer journalPapersId) {
		this.journalPapersId = journalPapersId;
	}
	public Integer getWriterItemId() {
		return this.writerItemId;
	}
	public void setWriterItemId(Integer writerItemId) {
		this.writerItemId = writerItemId;
	}

	
}