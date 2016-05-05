package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.PositionM;

public class PositionMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private PositionM positionM;

    public PositionMasterForm(PositionM positionM) {
        super();
        this.positionM = positionM;
    }

    public PositionMasterForm() {
        super();
        positionM = new PositionM();
        // TODO Auto-generated constructor stub
    }

    public PositionM getPositionM() {
        return positionM;
    }

    public void setPositionM(PositionM positionM) {
        this.positionM = positionM;
    }

}
