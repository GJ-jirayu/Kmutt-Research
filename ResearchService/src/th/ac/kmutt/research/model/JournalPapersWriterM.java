package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("JournalPapersWriterM")
public class JournalPapersWriterM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer itemList;

	private Integer journalPapersId;

	private Integer writerItemId;


	private String createdBy;

	private Date createdDate;

	private String isMain;

	/*private Integer organizationId;

	private Integer positionId;*/
	
	//private Integer organizationId;
	private OrganizationM organization;
	
	//private Integer positionId;
	private PositionM position;
	

	//private Integer researcherId;
	private ResearcherM researcher;// positionId; 

	private String updatedBy;

	private Date updatedDate;

	private Integer workLoadRatio;

	

	public JournalPapersWriterM() {
	}

	public Integer getJournalPapersId() {
		return this.journalPapersId;
	}

	public void setJournalPapersId(Integer journalPapersId) {
		this.journalPapersId = journalPapersId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getIsMain() {
		return this.isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	

	public Integer getItemList() {
		return itemList;
	}

	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}

	public Integer getWriterItemId() {
		return writerItemId;
	}

	public void setWriterItemId(Integer writerItemId) {
		this.writerItemId = writerItemId;
	}



	public OrganizationM getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationM organization) {
		this.organization = organization;
	}

	public PositionM getPosition() {
		return position;
	}

	public void setPosition(PositionM position) {
		this.position = position;
	}

	

	public ResearcherM getResearcher() {
		return researcher;
	}

	public void setResearcher(ResearcherM researcher) {
		this.researcher = researcher;
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

	public Integer getWorkLoadRatio() {
		return workLoadRatio;
	}

	public void setWorkLoadRatio(Integer workLoadRatio) {
		this.workLoadRatio = workLoadRatio;
	}


}