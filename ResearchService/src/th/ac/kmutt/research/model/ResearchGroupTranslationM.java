package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ResearchGroupTranslationM")
public class ResearchGroupTranslationM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;


	
	private Integer researchGroupId;

	private String language;


	
	private String createdBy;

	
	private Date createdDate;

	private String name;

	
	private String updatedBy;

	
	private Date updatedDate;

	public ResearchGroupTranslationM() {
	}

	public Integer getResearchGroupId() {
		return researchGroupId;
	}

	public void setResearchGroupId(Integer researchGroupId) {
		this.researchGroupId = researchGroupId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}