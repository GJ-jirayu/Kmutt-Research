package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("JournalPapersDocumentM")
public class JournalPapersDocumentM extends ImakeXML implements Serializable {
	public Integer getItemList() {
		return itemList;
	}

	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}

	private static final long serialVersionUID = 1L;
	
	private Integer journalPapersId;
	private Integer itemList;
	
	
	private Integer documentId;

	private String description;
	
	private String createdBy;

	
	private Date createdDate;

	
	private String updatedBy;

	
	private Date updatedDate;

	private JournalPaperM journalPaper;
	private String fileName;
	private String filePath;
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

	public JournalPapersDocumentM() {
	}

	public Integer getJournalPapersId() {
		return journalPapersId;
	}

	public void setJournalPapersId(Integer journalPapersId) {
		this.journalPapersId = journalPapersId;
	}

	public Integer getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public JournalPaperM getJournalPaper() {
		return journalPaper;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setJournalPaper(JournalPaperM journalPaper) {
		this.journalPaper = journalPaper;
	}

}