package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PatentDocumentM")
public class PatentDocumentM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;


	
	private Integer inventionId;

	private Integer itemList;	
	public Integer getItemList() {
		return itemList;
	}

	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}

	private Integer documentId;


	
	private String createdBy;

	private String description;	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Date createdDate;

	
	private String updatedBy;

	
	private Date updatedDate;

	private PatentM patent;
	
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

	public PatentDocumentM() {
	}

	public Integer getInventionId() {
		return inventionId;
	}

	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
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

	public PatentM getPatent() {
		return patent;
	}

	public void setPatent(PatentM patent) {
		this.patent = patent;
	}

	
}