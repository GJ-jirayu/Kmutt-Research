package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.FundingResourceM;

public class FundingResourceMasterForm
        extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private FundingResourceM fundingResourceM;

    public FundingResourceMasterForm(FundingResourceM fundingResourceM) {
        super();
        this.fundingResourceM = fundingResourceM;
    }

    public FundingResourceMasterForm() {
        super();
        fundingResourceM = new FundingResourceM();
        // TODO Auto-generated constructor stub
    }

    public FundingResourceM getFundingResourceM() {
        return fundingResourceM;
    }

    public void setFundingResourceM(FundingResourceM fundingResourceM) {
        this.fundingResourceM = fundingResourceM;
    }

}
