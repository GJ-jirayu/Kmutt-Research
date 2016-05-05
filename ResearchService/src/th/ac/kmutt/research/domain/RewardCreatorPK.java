package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the REWARD_CREATOR database table.
 * 
 */
@Embeddable
public class RewardCreatorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="REWARD_ID", insertable=false, updatable=false)
	private Integer rewardId;

	@Column(name="CREATOR_ITEM_LIST")
	private Integer creatorItemList;

	public RewardCreatorPK() {
	}
	public Integer getRewardId() {
		return this.rewardId;
	}
	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}
	public Integer getCreatorItemList() {
		return this.creatorItemList;
	}
	public void setCreatorItemList(Integer creatorItemList) {
		this.creatorItemList = creatorItemList;
	}

	
}