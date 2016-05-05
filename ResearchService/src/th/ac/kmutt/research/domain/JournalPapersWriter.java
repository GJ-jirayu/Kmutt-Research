package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * The persistent class for the JOURNAL_PAPERS_WRITER database table.
 * 
 */
@Entity
@Table(name="JOURNAL_PAPERS_WRITER")
@NamedQuery(name="JournalPapersWriter.findAll", query="SELECT j FROM JournalPapersWriter j")
public class JournalPapersWriter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JournalPapersWriterPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="IS_MAIN")
	private String isMain;

	/*@Column(name="ORGANIZATION_ID")
	private Integer organizationId;*/
	
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")
	private Organization organization;
	/*@Column(name="POSITION_ID")
	private Integer positionId;*/
	
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="POSITION_ID")
	private Position position;// positionId; 
	
	/*@Column(name="ORGANIZATION_ID")
	private Integer organizationId;

	@Column(name="POSITION_ID")
	private Integer positionId;*/

	/*@Column(name="RESEARCHER_ID")
	private Integer researcherId;*/
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="RESEARCHER_ID")
	private Researcher researcher;// positionId; 

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="WORK_LOAD_RATIO")
	private Integer workLoadRatio;

	

	public JournalPapersWriter() {
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

	
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getWorkLoadRatio() {
		return this.workLoadRatio;
	}

	public void setWorkLoadRatio(Integer workLoadRatio) {
		this.workLoadRatio = workLoadRatio;
	}



	public JournalPapersWriterPK getId() {
		return id;
	}



	public void setId(JournalPapersWriterPK id) {
		this.id = id;
	}



	public Organization getOrganization() {
		return organization;
	}



	public void setOrganization(Organization organization) {
		this.organization = organization;
	}



	public Position getPosition() {
		return position;
	}



	public void setPosition(Position position) {
		this.position = position;
	}



	public Researcher getResearcher() {
		return researcher;
	}



	public void setResearcher(Researcher researcher) {
		this.researcher = researcher;
	}






}