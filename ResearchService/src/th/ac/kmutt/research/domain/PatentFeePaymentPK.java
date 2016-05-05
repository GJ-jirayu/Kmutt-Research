package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the PATENT_FEE_PAYMENT database table.
 * 
 */
@Embeddable
public class PatentFeePaymentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_LIST")
	private Integer itemList;

	@Column(name="INVENTION_ID", insertable=false, updatable=false)
	private Integer inventionId;

	private Integer years;

	public PatentFeePaymentPK() {
	}
	public Integer getItemList() {
		return this.itemList;
	}
	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}
	public Integer getInventionId() {
		return this.inventionId;
	}
	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
	}
	public Integer getYears() {
		return this.years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}

	
}