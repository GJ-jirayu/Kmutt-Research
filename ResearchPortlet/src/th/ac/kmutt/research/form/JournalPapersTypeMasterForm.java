package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.JournalPapersTypeM;

public class JournalPapersTypeMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private JournalPapersTypeM journalPapersTypeM;

    public JournalPapersTypeMasterForm(JournalPapersTypeM journalPapersTypeM) {
        super();
        this.journalPapersTypeM = journalPapersTypeM;
    }

    public JournalPapersTypeMasterForm() {
        super();
        journalPapersTypeM = new JournalPapersTypeM();
        // TODO Auto-generated constructor stub
    }

    public JournalPapersTypeM getJournalPapersTypeM() {
        return journalPapersTypeM;
    }

    public void setJournalPapersTypeM(JournalPapersTypeM journalPapersTypeM) {
        this.journalPapersTypeM = journalPapersTypeM;
    }


}
