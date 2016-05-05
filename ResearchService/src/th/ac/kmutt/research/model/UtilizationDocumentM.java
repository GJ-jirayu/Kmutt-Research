package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the UTILIZATION_DOCUMENT database table.
 * 
 */
@XStreamAlias("UtilizationDocumentM")
public class UtilizationDocumentM extends ImakeXML implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer itemList;

	private Integer utilizationItemList;

	private Integer researchProjectId;

	private String createdBy;

	private Date createdDate;

	private String description;

	private String fileName;

	private String filePath;

	private String refHotlink;

	private String updatedBy;

	private Date updatedDate;

	
	private UtilizationM utilization;


	public Integer getItemList() {
		return itemList;
	}


	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}


	public Integer getUtilizationItemList() {
		return utilizationItemList;
	}


	public void setUtilizationItemList(Integer utilizationItemList) {
		this.utilizationItemList = utilizationItemList;
	}


	public Integer getResearchProjectId() {
		return researchProjectId;
	}


	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


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


	public UtilizationM getUtilization() {
		return utilization;
	}


	public void setUtilization(UtilizationM utilization) {
		this.utilization = utilization;
	}

	

}