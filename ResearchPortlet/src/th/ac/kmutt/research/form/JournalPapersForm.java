package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.JournalPaperM;

public class JournalPapersForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6259325217543717053L;
    private JournalPaperM journalPaperM;
    private String pageInit;

    public JournalPapersForm(JournalPaperM journalPaperM) {
        super();
        this.journalPaperM = journalPaperM;
    }

    public JournalPapersForm() {
        super();
        journalPaperM = new JournalPaperM();
        // TODO Auto-generated constructor stub
    }

    public JournalPaperM getJournalPaperM() {
        return journalPaperM;
    }

    public void setJournalPaperM(JournalPaperM journalPaperM) {
        this.journalPaperM = journalPaperM;
    }

    public String getPageInit() {
        return pageInit;
    }

    public void setPageInit(String pageInit) {
        this.pageInit = pageInit;
    }

}
