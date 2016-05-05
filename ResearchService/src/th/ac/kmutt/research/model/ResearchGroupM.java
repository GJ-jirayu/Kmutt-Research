package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("ResearchGroupM")
public class ResearchGroupM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer researchGroupId;

	
	private String createdBy;

	
	private Date createdDate;

	
	private String groupCode;

	
	private String groupEng;

	
	private String groupTh;

	private String permissions;

	
	private String updatedBy;

	
	private Date updatedDate;

	public ResearchGroupM() {
	}

	public Integer getResearchGroupId() {
		return this.researchGroupId;
	}

	public void setResearchGroupId(Integer researchGroupId) {
		this.researchGroupId = researchGroupId;
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

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupEng() {
		return this.groupEng;
	}

	public void setGroupEng(String groupEng) {
		this.groupEng = groupEng;
	}

	public String getGroupTh() {
		return this.groupTh;
	}

	public void setGroupTh(String groupTh) {
		this.groupTh = groupTh;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
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

	

}