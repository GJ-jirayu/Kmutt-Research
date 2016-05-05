package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("ResearcherGroupM")
public class ResearcherGroupM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer researchGroupId;

	
	private Integer researcherId;


	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	private ResearchGroupM researchGroup;

	public ResearcherGroupM() {
	}

	public Integer getResearchGroupId() {
		return researchGroupId;
	}

	public void setResearchGroupId(Integer researchGroupId) {
		this.researchGroupId = researchGroupId;
	}

	public Integer getResearcherId() {
		return researcherId;
	}

	public void setResearcherId(Integer researcherId) {
		this.researcherId = researcherId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public ResearchGroupM getResearchGroup() {
		return researchGroup;
	}

	public void setResearchGroup(ResearchGroupM researchGroup) {
		this.researchGroup = researchGroup;
	}

	

}