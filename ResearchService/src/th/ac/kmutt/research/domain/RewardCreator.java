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
 * The persistent class for the REWARD_CREATOR database table.
 * 
 */
@Entity
@Table(name="REWARD_CREATOR")
@NamedQuery(name="RewardCreator.findAll", query="SELECT r FROM RewardCreator r")
public class RewardCreator implements Serializable {
	public RewardCreatorPK getId() {
		return id;
	}



	public void setId(RewardCreatorPK id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;


	@EmbeddedId
	private RewardCreatorPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATOR_ID")
	private String creatorId;

	@Column(name="CREATOR_TYPE")
	private String creatorType;

	/*@Column(name="ORGANIZATION_ID")
	private Integer organizationId;*/
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")
	private Organization organization;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	/*//bi-directional one-to-one association to Reward
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne
	@JoinColumn(name="REWARD_ID",insertable=false,updatable=false)
	private Reward reward;
*/
	public RewardCreator() {
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

	public String getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	

	public String getCreatorType() {
		return this.creatorType;
	}

	public void setCreatorType(String creatorType) {
		this.creatorType = creatorType;
	}

	

	public Organization getOrganization() {
		return organization;
	}



	public void setOrganization(Organization organization) {
		this.organization = organization;
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