package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("FundingResourceM")
public class FundingResourceM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer fundingResourceId;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String frNameEng;

	
	private String frNameThai;

	
	private String frShortName;

	
	private String fundingResourceCode;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	
	private CountryM country;

	private FundingResourceTypeM fundingResourceType;

	public FundingResourceM() {
	}

	public Integer getFundingResourceId() {
		return this.fundingResourceId;
	}

	public void setFundingResourceId(Integer fundingResourceId) {
		this.fundingResourceId = fundingResourceId;
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

	public String getFrNameEng() {
		return this.frNameEng;
	}

	public void setFrNameEng(String frNameEng) {
		this.frNameEng = frNameEng;
	}

	public String getFrNameThai() {
		return this.frNameThai;
	}

	public void setFrNameThai(String frNameThai) {
		this.frNameThai = frNameThai;
	}

	public String getFrShortName() {
		return this.frShortName;
	}

	public void setFrShortName(String frShortName) {
		this.frShortName = frShortName;
	}

	public String getFundingResourceCode() {
		return this.fundingResourceCode;
	}

	public void setFundingResourceCode(String fundingResourceCode) {
		this.fundingResourceCode = fundingResourceCode;
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

	public CountryM getCountry() {
		return country;
	}

	public void setCountry(CountryM country) {
		this.country = country;
	}

	public FundingResourceTypeM getFundingResourceType() {
		return fundingResourceType;
	}

	public void setFundingResourceType(FundingResourceTypeM fundingResourceType) {
		this.fundingResourceType = fundingResourceType;
	}

	
}