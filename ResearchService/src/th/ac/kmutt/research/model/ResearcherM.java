package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("ResearcherM")
public class ResearcherM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer researcherId;
	private PositionM academicPosition; // academicPositionId
	private PositionM position;// positionId; xx
	private TitleM academicTitle; // academicTitle
	private TitleM title; 
	//private OrganizationExtM organization;//Integer organizationId; xx
	private OrganizationM organization;//Integer organizationId; xx
	private String campusCode; 
	private String deptCode;
	private String institutionCode;  
	private String workCode;
	private String cardId;
	private String createdBy;
	private Timestamp createdDate;
	private String nameEng;
	private String nameThai; 
	private String researcherCode;
	private String surnameEng;
	private String surnameThai;
	private String updatedBy;
	private Timestamp updatedDate;
	private Integer researcherTypeId;
	
	private List<ResearcherGroupM> researcherGroups;
	
	private ResearcherGroupM researcherGroup;
	public ResearcherM(){
		
	}
	public Integer getResearcherId() {
		return researcherId;
	}
	public void setResearcherId(Integer researcherId) {
		this.researcherId = researcherId;
	}
	public PositionM getAcademicPosition() {
		return academicPosition;
	}
	public void setAcademicPosition(PositionM academicPosition) {
		this.academicPosition = academicPosition;
	}
	public PositionM getPosition() {
		return position;
	}
	public void setPosition(PositionM position) {
		this.position = position;
	}
	public TitleM getAcademicTitle() {
		return academicTitle;
	}
	public void setAcademicTitle(TitleM academicTitle) {
		this.academicTitle = academicTitle;
	}
	public TitleM getTitle() {
		return title;
	}
	public void setTitle(TitleM title) {
		this.title = title;
	}
	
	public OrganizationM getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationM organization) {
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
	public List<ResearcherGroupM> getResearcherGroups() {
		return researcherGroups;
	}
	public ResearcherGroupM getResearcherGroup() {
		return researcherGroup;
	}
	public void setResearcherGroup(ResearcherGroupM researcherGroup) {
		this.researcherGroup = researcherGroup;
	}
	public void setResearcherGroups(List<ResearcherGroupM> researcherGroups) {
		this.researcherGroups = researcherGroups;
	}
	
}