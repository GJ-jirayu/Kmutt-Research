package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.RewardM;

public class RewardForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5609598745139056441L;
    private RewardM rewardM;
    private String rewardDate;

    public RewardForm(RewardM rewardM) {
        super();
        this.rewardM = rewardM;
    }

    public RewardForm() {
        super();
        rewardM = new RewardM();
        // TODO Auto-generated constructor stub
    }

    public RewardM getRewardM() {
        return rewardM;
    }

    public void setRewardM(RewardM rewardM) {
        this.rewardM = rewardM;
    }

    public String getRewardDate() {
        return rewardDate;
    }

    public void setRewardDate(String rewardDate) {
        this.rewardDate = rewardDate;
    }
}
