package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("TitleM")
public class TitleM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer titleId;

	
	private String academicTitleCode;

	
	private String academicTitleName;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String titleCode;

	
	private String titleName;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	public TitleM() {
	}

	public Integer getTitleId() {
		return this.titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getAcademicTitleCode() {
		return this.academicTitleCode;
	}

	public void setAcademicTitleCode(String academicTitleCode) {
		this.academicTitleCode = academicTitleCode;
	}

	public String getAcademicTitleName() {
		return this.academicTitleName;
	}

	public void setAcademicTitleName(String academicTitleName) {
		this.academicTitleName = academicTitleName;
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

	public String getTitleCode() {
		return this.titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public String getTitleName() {
		return this.titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
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