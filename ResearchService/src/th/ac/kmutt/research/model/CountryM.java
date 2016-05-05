package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("CountryM")
public class CountryM  extends ImakeXML implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer countryId;

	
	private String countryCode;

	
	private String countryNameEng;

	
	private String countryNameTh;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	//bi-directional many-to-one association to FundingResource

	public CountryM() {
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryNameEng() {
		return this.countryNameEng;
	}

	public void setCountryNameEng(String countryNameEng) {
		this.countryNameEng = countryNameEng;
	}

	public String getCountryNameTh() {
		return this.countryNameTh;
	}

	public void setCountryNameTh(String countryNameTh) {
		this.countryNameTh = countryNameTh;
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