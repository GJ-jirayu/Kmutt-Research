package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("RewardCreatorM")
public class RewardCreatorM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer rewardId;

	
	private String createdBy;

	
	private Date createdDate;

	
	private String creatorId;

	
	private Integer creatorItemList;
	;
	
	private String creatorType;

	
//	private Integer organizationId;
	private OrganizationM organization;
	
	private String updatedBy;

	
	private Date updatedDate;

	private RewardM reward;

	public RewardCreatorM() {
	}

	public Integer getRewardId() {
		return this.rewardId;
	}

	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
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


	public Integer getCreatorItemList() {
		return creatorItemList;
	}

	public void setCreatorItemList(Integer creatorItemList) {
		this.creatorItemList = creatorItemList;
	}

	public String getCreatorType() {
		return this.creatorType;
	}

	public void setCreatorType(String creatorType) {
		this.creatorType = creatorType;
	}

	

	public OrganizationM getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationM organization) {
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

	public RewardM getReward() {
		return reward;
	}

	public void setReward(RewardM reward) {
		this.reward = reward;
	}

}