package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("ResearchProjectWithdrawM")
public class ResearchProjectWithdrawM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer itemList;	
	

	private Integer researchProjectId;

	
	private Integer withdrawItemId;

	
	private BigDecimal amountWithdraw;
	private String amountWithdrawShow;

	private BigDecimal balance;
	private String balanceShow; 
	
	private String createdBy;

	
	private Date createdDate;

	
	private String listItem;

	private String remark;

	
	private String updatedBy;

	
	private Date updatedDate;

	
	private Date withdrawDate;
	private String withdrawDateShow;

	public String getAmountWithdrawShow() {
		return amountWithdrawShow;
	}

	public void setAmountWithdrawShow(String amountWithdrawShow) {
		this.amountWithdrawShow = amountWithdrawShow;
	}

	public String getBalanceShow() {
		return balanceShow;
	}

	public void setBalanceShow(String balanceShow) {
		this.balanceShow = balanceShow;
	}

	public String getWithdrawDateShow() {
		return withdrawDateShow;
	}

	public void setWithdrawDateShow(String withdrawDateShow) {
		this.withdrawDateShow = withdrawDateShow;
	}

	private ResearchProjectM researchProject;

	public Integer getItemList() {
		return itemList;
	}

	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}
	
	public ResearchProjectWithdrawM() {
	}

	public Integer getResearchProjectId() {
		return researchProjectId;
	}

	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}

	public Integer getWithdrawItemId() {
		return withdrawItemId;
	}

	public void setWithdrawItemId(Integer withdrawItemId) {
		this.withdrawItemId = withdrawItemId;
	}

	public BigDecimal getAmountWithdraw() {
		return amountWithdraw;
	}

	public void setAmountWithdraw(BigDecimal amountWithdraw) {
		this.amountWithdraw = amountWithdraw;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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

	public String getListItem() {
		return listItem;
	}

	public void setListItem(String listItem) {
		this.listItem = listItem;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Date getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	public ResearchProjectM getResearchProject() {
		return researchProject;
	}

	public void setResearchProject(ResearchProjectM researchProject) {
		this.researchProject = researchProject;
	}



}