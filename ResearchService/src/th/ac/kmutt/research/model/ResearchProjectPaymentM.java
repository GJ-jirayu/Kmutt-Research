package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("ResearchProjectPaymentM")
public class ResearchProjectPaymentM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer itemList;	
	private Integer researchProjectId;

	
	private Integer paymentItemId;

	
	private String amountReceived;
	private String amountReceivedShow; 
	
	public String getAmountReceivedShow() {
		return amountReceivedShow;
	}

	public void setAmountReceivedShow(String amountReceivedShow) {
		this.amountReceivedShow = amountReceivedShow;
	}

	public String getReceivedDateShow() {
		return receivedDateShow;
	}

	public void setReceivedDateShow(String receivedDateShow) {
		this.receivedDateShow = receivedDateShow;
	}

	private String createdBy;

	
	private Date createdDate;

	

	private String receiptNo;

	
	private Date receivedDate;
	private String receivedDateShow;
	
	private String updatedBy;

	
	private Date updatedDate;

	private ResearchProjectM researchProject;


	public Integer getItemList() {
		return itemList;
	}

	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}
	public ResearchProjectPaymentM() {
	}

	public Integer getResearchProjectId() {
		return researchProjectId;
	}

	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}

	public Integer getPaymentItemId() {
		return paymentItemId;
	}

	public void setPaymentItemId(Integer paymentItemId) {
		this.paymentItemId = paymentItemId;
	}

	public String getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(String amountReceived) {
		this.amountReceived = amountReceived;
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

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}


	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
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