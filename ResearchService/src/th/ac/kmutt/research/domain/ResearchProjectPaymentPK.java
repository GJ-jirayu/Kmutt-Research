package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the RESEARCH_PROJECT_PAYMENT database table.
 * 
 */
@Embeddable
public class ResearchProjectPaymentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;


	@Column(name="ITEM_LIST")
	private Integer itemList;
	
	@Column(name="RESEARCH_PROJECT_ID", insertable=false, updatable=false)
	private Integer researchProjectId;

	@Column(name="PAYMENT_ITEM_ID")
	private Integer paymentItemId;

	public ResearchProjectPaymentPK() {
	}
	public Integer getResearchProjectId() {
		return this.researchProjectId;
	}
	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}
	public Integer getPaymentItemId() {
		return this.paymentItemId;
	}
	public void setPaymentItemId(Integer paymentItemId) {
		this.paymentItemId = paymentItemId;
	}
	public Integer getItemList() {
		return itemList;
	}
	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}

}