package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the UTILIZATION_DOCUMENT database table.
 * 
 */
@Embeddable
public class UtilizationDocumentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_LIST")
	private Integer itemList;

	@Column(name="UTILIZATION_ITEM_LIST", insertable=false, updatable=false)
	private Integer utilizationItemList;

	@Column(name="RESEARCH_PROJECT_ID", insertable=false, updatable=false)
	private Integer researchProjectId;

	public UtilizationDocumentPK() {
	}
	public Integer getItemList() {
		return this.itemList;
	}
	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}
	public Integer getUtilizationItemList() {
		return this.utilizationItemList;
	}
	public void setUtilizationItemList(Integer utilizationItemList) {
		this.utilizationItemList = utilizationItemList;
	}
	public Integer getResearchProjectId() {
		return this.researchProjectId;
	}
	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}

		
}