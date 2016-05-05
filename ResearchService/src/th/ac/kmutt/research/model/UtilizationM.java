package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("UtilizationM")
public class UtilizationM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;


	
	//private Integer researchProjectId;
	private ResearchProjectM researchProject;

	
	private Integer utilizationItemList;


	
	private Integer budgetYear;

	
	private String createdBy;

	
	private Date createdDate;

	
	private String docType;

	
	private String updatedBy;

	
	private Date updatedDate;

	private String fileName;
	private String filePath;
	private String refHotlink;
	private String flag;
	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	private UtilizationDocumentM utilizationDocument;
	
	private List<UtilizationM> utilizations;
	private List<DocAssignMappingM> docAssignMappings;
	

	private List<UtilizationDocumentM> utilizationDocuments;	
	
	private Integer utilizationTypeId;
	public Integer getUtilizationTypeId() {
		return utilizationTypeId;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getFilePath() {
		return filePath;
	}



	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public String getRefHotlink() {
		return refHotlink;
	}



	public void setRefHotlink(String refHotlink) {
		this.refHotlink = refHotlink;
	}



	public void setUtilizationTypeId(Integer utilizationTypeId) {
		this.utilizationTypeId = utilizationTypeId;
	}



	private UtilizationTypeM utilizationType;
	private String outCome;
	public String getOutCome() {
		return outCome;
	}



	public List<DocAssignMappingM> getDocAssignMappings() {
		return docAssignMappings;
	}



	public void setDocAssignMappings(List<DocAssignMappingM> docAssignMappings) {
		this.docAssignMappings = docAssignMappings;
	}



	public void setOutCome(String outCome) {
		this.outCome = outCome;
	}



	public UtilizationM() {
		researchProject=new ResearchProjectM();
	}

	

	public ResearchProjectM getResearchProject() {
		return researchProject;
	}



	public void setResearchProject(ResearchProjectM researchProject) {
		this.researchProject = researchProject;
	}



	public Integer getUtilizationItemList() {
		return utilizationItemList;
	}

	public void setUtilizationItemList(Integer utilizationItemList) {
		this.utilizationItemList = utilizationItemList;
	}

	public Integer getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(Integer budgetYear) {
		this.budgetYear = budgetYear;
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

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
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



	public UtilizationTypeM getUtilizationType() {
		return utilizationType;
	}



	public void setUtilizationType(UtilizationTypeM utilizationType) {
		this.utilizationType = utilizationType;
	}



	public List<UtilizationM> getUtilizations() {
		return utilizations;
	}



	public List<UtilizationDocumentM> getUtilizationDocuments() {
		return utilizationDocuments;
	}



	public void setUtilizationDocuments(
			List<UtilizationDocumentM> utilizationDocuments) {
		this.utilizationDocuments = utilizationDocuments;
	}



	public void setUtilizations(List<UtilizationM> utilizations) {
		this.utilizations = utilizations;
	}



	public UtilizationDocumentM getUtilizationDocument() {
		return utilizationDocument;
	}



	public void setUtilizationDocument(UtilizationDocumentM utilizationDocument) {
		this.utilizationDocument = utilizationDocument;
	}

	
}