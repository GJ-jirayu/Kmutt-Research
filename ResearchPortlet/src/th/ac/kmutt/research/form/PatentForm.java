package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.PatentM;

public class PatentForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6259325217543717053L;
    private PatentM patentM;

    private String proposeDate;
    private String verifyDate;
    private String announcementDate;
    private String announcementPayDate;
    private String proposeBookDate;
    private String receiveDate;
    private String patentDate;
    private String payDate;

    public PatentForm(PatentM patentM) {
        super();
        this.patentM = patentM;
    }

    public PatentForm() {
        super();
        patentM = new PatentM();
        // TODO Auto-generated constructor stub
    }

    public PatentM getPatentM() {
        return patentM;
    }

    public void setPatentM(PatentM patentM) {
        this.patentM = patentM;
    }

    public String getProposeDate() {
        return proposeDate;
    }

    public void setProposeDate(String proposeDate) {
        this.proposeDate = proposeDate;
    }

    public String getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(String verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(String announcementDate) {
        this.announcementDate = announcementDate;
    }

    public String getAnnouncementPayDate() {
        return announcementPayDate;
    }

    public void setAnnouncementPayDate(String announcementPayDate) {
        this.announcementPayDate = announcementPayDate;
    }

    public String getProposeBookDate() {
        return proposeBookDate;
    }

    public void setProposeBookDate(String proposeBookDate) {
        this.proposeBookDate = proposeBookDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getPatentDate() {
        return patentDate;
    }

    public void setPatentDate(String patentDate) {
        this.patentDate = patentDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

}
