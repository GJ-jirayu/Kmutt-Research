package th.ac.kmutt.research.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the RESEARCH_GROUP_TRANSLATIONS database table.
 * 
 */
@Entity
@Table(name="RESEARCH_GROUP_TRANSLATIONS")
@NamedQuery(name="ResearchGroupTranslation.findAll", query="SELECT r FROM ResearchGroupTranslation r")
public class ResearchGroupTranslation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResearchGroupTranslationPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String name;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	public ResearchGroupTranslation() {
	}

	public ResearchGroupTranslationPK getId() {
		return this.id;
	}

	public void setId(ResearchGroupTranslationPK id) {
		this.id = id;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

}