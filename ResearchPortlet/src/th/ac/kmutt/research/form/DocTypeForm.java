package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.DocTypeM;

public class DocTypeForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6259325217543717053L;
    private DocTypeM docTypeM;

    private String[] isView;
    private String[] isUpdate;
    private String[] isDelete;
    private String[] isCreate;
    private String[] isDisableForAdmin;

    public DocTypeForm(DocTypeM docTypeM) {
        super();
        this.docTypeM = docTypeM;
    }

    public DocTypeForm() {
        super();
        docTypeM = new DocTypeM();
        // TODO Auto-generated constructor stub
    }

    public DocTypeM getDocTypeM() {
        return docTypeM;
    }

    public void setDocTypeM(DocTypeM docTypeM) {
        this.docTypeM = docTypeM;
    }

    public String[] getIsView() {
        return isView;
    }

    public void setIsView(String[] isView) {
        this.isView = isView;
    }

    public String[] getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String[] isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String[] getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String[] isDelete) {
        this.isDelete = isDelete;
    }

    public String[] getIsCreate() {
        return isCreate;
    }

    public void setIsCreate(String[] isCreate) {
        this.isCreate = isCreate;
    }

    public String[] getIsDisableForAdmin() {
        return isDisableForAdmin;
    }

    public void setIsDisableForAdmin(String[] isDisableForAdmin) {
        this.isDisableForAdmin = isDisableForAdmin;
    }

}
