package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("ResearchProjectProgressM")
public class ResearchProjectProgressM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer itemList;	
	

	private Integer researchProjectId;

	
	private Integer progressItemId;

	
	private String createdBy;

	
	private Date createdDate;
	
	private Date dueDate;
	private String dueDateShow;

	
	public String getDueDateShow() {
		return dueDateShow;
	}

	public void setDueDateShow(String dueDateShow) {
		this.dueDateShow = dueDateShow;
	}

	public String getSubmitDateShow() {
		return submitDateShow;
	}

	public void setSubmitDateShow(String submitDateShow) {
		this.submitDateShow = submitDateShow;
	}
	private String progressPercentage;

	private String remark;

	
	private Date submitDate;
	private String submitDateShow;
	
	private String updatedBy;

	
	private Date updatedDate;

	public Integer getItemList() {
		return itemList;
	}

	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}	
	private ResearchProjectM researchProject;

	public ResearchProjectProgressM() {
	}

	public Integer getResearchProjectId() {
		return researchProjectId;
	}

	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}

	public Integer getProgressItemId() {
		return progressItemId;
	}

	public void setProgressItemId(Integer progressItemId) {
		this.progressItemId = progressItemId;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(String progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
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

	public ResearchProjectM getResearchProject() {
		return researchProject;
	}

	public void setResearchProject(ResearchProjectM researchProject) {
		this.researchProject = researchProject;
	}

	
}