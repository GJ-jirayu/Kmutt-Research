package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.PublishTypeM;

public class PublishedTypeMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private PublishTypeM publishTypeM;

    public PublishedTypeMasterForm(PublishTypeM publishTypeM) {
        super();
        this.publishTypeM = publishTypeM;
    }

    public PublishedTypeMasterForm() {
        super();
        publishTypeM = new PublishTypeM();
        // TODO Auto-generated constructor stub
    }

    public PublishTypeM getPublishTypeM() {
        return publishTypeM;
    }

    public void setPublishTypeM(PublishTypeM publishTypeM) {
        this.publishTypeM = publishTypeM;
    }

}
