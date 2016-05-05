package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * The persistent class for the JOURNAL_PAPERS_DOCUMENT database table.
 * 
 */
@Entity
@Table(name="JOURNAL_PAPERS_DOCUMENT")
@NamedQuery(name="JournalPapersDocument.findAll", query="SELECT j FROM JournalPapersDocument j")
public class JournalPapersDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JournalPapersDocumentPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	@Column(name="REF_HOTLINK")
	private String refHotlink;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRefHotlink() {
		return refHotlink;
	}

	public void setRefHotlink(String refHotlink) {
		this.refHotlink = refHotlink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//bi-directional many-to-one association to JournalPaper
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="JOURNAL_PAPERS_ID",insertable=false,updatable=false)
	private JournalPaper journalPaper;

	public JournalPapersDocument() {
	}

	public JournalPapersDocumentPK getId() {
		return this.id;
	}

	public void setId(JournalPapersDocumentPK id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public JournalPaper getJournalPaper() {
		return this.journalPaper;
	}

	public void setJournalPaper(JournalPaper journalPaper) {
		this.journalPaper = journalPaper;
	}

}