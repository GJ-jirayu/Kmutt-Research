package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.sql.Timestamp;


/**
 * The persistent class for the RESEARCHER_GROUP database table.
 * 
 */
@Entity
@Table(name="RESEARCHER_GROUP")
@NamedQuery(name="ResearcherGroup.findAll", query="SELECT r FROM ResearcherGroup r")
public class ResearcherGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResearcherGroupPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to ResearchGroup
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="RESEARCH_GROUP_ID",insertable=false,updatable=false)
	private ResearchGroup researchGroup;

	public ResearcherGroup() {
	}

	public ResearcherGroupPK getId() {
		return this.id;
	}

	public void setId(ResearcherGroupPK id) {
		this.id = id;
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

	public ResearchGroup getResearchGroup() {
		return this.researchGroup;
	}

	public void setResearchGroup(ResearchGroup researchGroup) {
		this.researchGroup = researchGroup;
	}

}