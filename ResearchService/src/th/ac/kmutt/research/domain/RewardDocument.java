package th.ac.kmutt.research.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the REWARD_DOCUMENT database table.
 * 
 */
@Entity
@Table(name="REWARD_DOCUMENT")
@NamedQuery(name="RewardDocument.findAll", query="SELECT r FROM RewardDocument r")
public class RewardDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RewardDocumentPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String description;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	@Column(name="REF_HOTLINK")
	private String refHotlink;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	//bi-directional many-to-one association to Reward
	@ManyToOne
	@JoinColumn(name="REWARD_ID",insertable=false,updatable=false)
	private Reward reward;

	public RewardDocument() {
	}

	public RewardDocumentPK getId() {
		return this.id;
	}

	public void setId(RewardDocumentPK id) {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRefHotlink() {
		return this.refHotlink;
	}

	public void setRefHotlink(String refHotlink) {
		this.refHotlink = refHotlink;
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

	public Reward getReward() {
		return this.reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

}