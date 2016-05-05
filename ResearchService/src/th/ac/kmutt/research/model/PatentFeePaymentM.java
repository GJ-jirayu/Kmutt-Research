package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PatentFeePaymentM")
public class PatentFeePaymentM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer inventionId;
	private Integer itemList;
	

	private BigDecimal amount;
	private String amountShow; 

	private Integer years;	
	private String createdBy;

	
	private Date createdDate;

	private Date date;
	private String dateShow;

	private String ispay;

	private String remark;

	
	private String updatedBy;

	
	private Date updatedDate;
	
	

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}


	private PatentM patent;
	public Integer getItemList() {
		return itemList;
	}

	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}
	public PatentFeePaymentM() {
	}

	public Integer getInventionId() {
		return this.inventionId;
	}

	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public String getAmountShow() {
		return amountShow;
	}

	public void setAmountShow(String amountShow) {
		this.amountShow = amountShow;
	}

	public String getDateShow() {
		return dateShow;
	}

	public void setDateShow(String dateShow) {
		this.dateShow = dateShow;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIspay() {
		return this.ispay;
	}

	public void setIspay(String ispay) {
		this.ispay = ispay;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public PatentM getPatent() {
		return patent;
	}

	public void setPatent(PatentM patent) {
		this.patent = patent;
	}

}