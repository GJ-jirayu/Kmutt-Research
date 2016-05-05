package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the TITLE database table.
 * 
 */
@Entity
@Table(name="TITLE")
public class Title implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TITLE_ID")
	private Integer titleId;

	@Column(name="ACADEMIC_TITLE_CODE")
	private String academicTitleCode;

	@Column(name="ACADEMIC_TITLE_NAME")
	private String academicTitleName;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="TITLE_CODE")
	private String titleCode;

	@Column(name="TITLE_NAME")
	private String titleName;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	public Title() {
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