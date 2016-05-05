package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.CountryM;
import th.ac.kmutt.research.model.PublishLevelM;

public class PublishedLevelMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private PublishLevelM publishLevelM;

    public PublishedLevelMasterForm(PublishLevelM publishLevelM) {
        super();
        this.publishLevelM = publishLevelM;
    }

    public PublishedLevelMasterForm() {
        super();
        publishLevelM = new PublishLevelM();
        // TODO Auto-generated constructor stub
    }

    public PublishLevelM getPublishLevelM() {
        return publishLevelM;
    }

    public void setPublishLevelM(PublishLevelM publishLevelM) {
        this.publishLevelM = publishLevelM;
    }

}
