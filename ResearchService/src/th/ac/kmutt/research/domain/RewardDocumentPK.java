package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the REWARD_DOCUMENT database table.
 * 
 */
@Embeddable
public class RewardDocumentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_LIST")
	private Integer itemList;

	@Column(name="REWARD_ID", insertable=false, updatable=false)
	private Integer rewardId;

	public RewardDocumentPK() {
	}
	public int getItemList() {
		return this.itemList;
	}
	public void setItemList(int itemList) {
		this.itemList = itemList;
	}
	public int getRewardId() {
		return this.rewardId;
	}
	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RewardDocumentPK)) {
			return false;
		}
		RewardDocumentPK castOther = (RewardDocumentPK)other;
		return 
			(this.itemList == castOther.itemList)
			&& (this.rewardId == castOther.rewardId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.itemList;
		hash = hash * prime + this.rewardId;
		
		return hash;
	}
}