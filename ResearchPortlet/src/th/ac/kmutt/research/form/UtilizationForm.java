package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.UtilizationM;

public class UtilizationForm extends CommonForm  implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6259325217543717053L;
	private UtilizationM utilizationM;
	public UtilizationForm(UtilizationM utilizationM) {
		super();
		this.utilizationM = utilizationM;
	}
	public UtilizationForm() {
		super();
		utilizationM=new UtilizationM();
		// TODO Auto-generated constructor stub
	}
	public UtilizationM getUtilizationM() {
		return utilizationM;
	}
	public void setUtilizationM(UtilizationM utilizationM) {
		this.utilizationM = utilizationM;
	}

}
