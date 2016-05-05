package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * The persistent class for the RESEARCHER database table.
 * 
 */
@Entity
@Table(name="RESEARCHER")
@NamedQuery(name="Researcher.findAll", query="SELECT r FROM Researcher r")
public class Researcher implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RESEARCHER_ID")
	private Integer researcherId;


	@Column(name="RESEARCHER_TYPE_ID")
	private Integer researcherTypeId;

	
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="ACADEMIC_POSITION_ID")
	private Position academicPosition; // academicPositionId

	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="POSITION_ID")
	private Position position;// positionId; xx
	
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="ACADEMIC_TITLE")
	private Title academicTitle; // academicTitle
	
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="TITLE")
	private Title title; 
	
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")
	private Organization organization;//Integer organizationId; xx
	//private OrganizationExt organization;//Integer organizationId; xx

	@Column(name="CAMPUS_CODE")
	private String campusCode; 
	
	@Column(name="DEPT_CODE")
	private String deptCode;
	
	@Column(name="INSTITUTION_CODE")
	private String institutionCode;  
	
	@Column(name="WORK_CODE")
	private String workCode;

	@Column(name="CARD_ID")
	private String cardId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;


	@Column(name="NAME_ENG")
	private String nameEng;

	@Column(name="NAME_THAI")
	private String nameThai;
	

	@Column(name="RESEARCHER_CODE")
	private String researcherCode;

	@Column(name="SURNAME_ENG")
	private String surnameEng;

	@Column(name="SURNAME_THAI")
	private String surnameThai;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	

	public Researcher() {
	}

	public Integer getResearcherId() {
		return this.researcherId;
	}

	public void setResearcherId(Integer researcherId) {
		this.researcherId = researcherId;
	}

	public Position getAcademicPosition() {
		return academicPosition;
	}

	public void setAcademicPosition(Position academicPosition) {
		this.academicPosition = academicPosition;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Title getAcademicTitle() {
		return academicTitle;
	}

	public void setAcademicTitle(Title academicTitle) {
		this.academicTitle = academicTitle;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getCampusCode() {
		return campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getNameThai() {
		return nameThai;
	}

	public void setNameThai(String nameThai) {
		this.nameThai = nameThai;
	}

	public String getResearcherCode() {
		return researcherCode;
	}

	public void setResearcherCode(String researcherCode) {
		this.researcherCode = researcherCode;
	}

	public String getSurnameEng() {
		return surnameEng;
	}

	public void setSurnameEng(String surnameEng) {
		this.surnameEng = surnameEng;
	}

	public String getSurnameThai() {
		return surnameThai;
	}

	public void setSurnameThai(String surnameThai) {
		this.surnameThai = surnameThai;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getResearcherTypeId() {
		return researcherTypeId;
	}

	public void setResearcherTypeId(Integer researcherTypeId) {
		this.researcherTypeId = researcherTypeId;
	}

	public Organization getOrganization() {
		return organization;
	}



}