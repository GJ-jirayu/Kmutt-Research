package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.ResearchProjectM;


public class ReSearchProjectForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6259325217543717053L;
    private String endDate;
    private String startDate;
    private String reportDuedate;
    private String reportSubmitDate;

    private ResearchProjectM researchProjectM;

    public ReSearchProjectForm(ResearchProjectM researchProjectM) {
        super();
        this.researchProjectM = researchProjectM;
    }

    public ReSearchProjectForm() {
        super();
        researchProjectM = new ResearchProjectM();
        // TODO Auto-generated constructor stub
    }

    public ResearchProjectM getResearchProjectM() {
        return researchProjectM;
    }

    public void setResearchProjectM(ResearchProjectM researchProjectM) {
        this.researchProjectM = researchProjectM;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getReportDuedate() {
        return reportDuedate;
    }

    public void setReportDuedate(String reportDuedate) {
        this.reportDuedate = reportDuedate;
    }

    public String getReportSubmitDate() {
        return reportSubmitDate;
    }

    public void setReportSubmitDate(String reportSubmitDate) {
        this.reportSubmitDate = reportSubmitDate;
    }

}
