package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the RESEARCH_PROJECT_WITHDRAW database table.
 * 
 */
@Embeddable
public class ResearchProjectWithdrawPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_LIST")
	private Integer itemList;
	
	@Column(name="RESEARCH_PROJECT_ID", insertable=false, updatable=false)
	private Integer researchProjectId;

	@Column(name="WITHDRAW_ITEM_ID")
	private Integer withdrawItemId;

	public ResearchProjectWithdrawPK() {
	}
	public Integer getResearchProjectId() {
		return this.researchProjectId;
	}
	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}
	public Integer getWithdrawItemId() {
		return this.withdrawItemId;
	}
	public void setWithdrawItemId(Integer withdrawItemId) {
		this.withdrawItemId = withdrawItemId;
	}
	public Integer getItemList() {
		return itemList;
	}
	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}

}