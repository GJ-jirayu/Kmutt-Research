package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the RESEARCH_PROJECT_RESEARCHER database table.
 * 
 */
@Embeddable
public class ResearchProjectResearcherPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_LIST")
	private Integer itemList;
	
	
	@Column(name="RESEARCH_PROJECT_ID", insertable=false, updatable=false)
	private Integer researchProjectId;

	@Column(name="RESEARCHER_ITEM_ID")
	private Integer researcherItemId;

	public Integer getItemList() {
		return itemList;
	}
	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}
	
	public ResearchProjectResearcherPK() {
	}
	public Integer getResearchProjectId() {
		return this.researchProjectId;
	}
	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}
	public Integer getResearcherItemId() {
		return this.researcherItemId;
	}
	public void setResearcherItemId(Integer researcherItemId) {
		this.researcherItemId = researcherItemId;
	}

}