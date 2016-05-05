package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("FundingResourceTypeM")
public class FundingResourceTypeM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer fundingResourceTypeId;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String frtCode;

	
	private String frtName;

	
	private String frtSource;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	public FundingResourceTypeM() {
	}

	public Integer getFundingResourceTypeId() {
		return this.fundingResourceTypeId;
	}

	public void setFundingResourceTypeId(Integer fundingResourceTypeId) {
		this.fundingResourceTypeId = fundingResourceTypeId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getFrtCode() {
		return this.frtCode;
	}

	public void setFrtCode(String frtCode) {
		this.frtCode = frtCode;
	}

	public String getFrtName() {
		return this.frtName;
	}

	public void setFrtName(String frtName) {
		this.frtName = frtName;
	}

	public String getFrtSource() {
		return this.frtSource;
	}

	public void setFrtSource(String frtSource) {
		this.frtSource = frtSource;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}