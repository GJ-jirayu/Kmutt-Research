package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.model.CountryM;

public class CountryMasterForm extends CommonForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6259325217543717053L;
    private CountryM countryM;

    public CountryM getCountryM() {
        return countryM;
    }

    public void setCountryM(CountryM countryM) {
        this.countryM = countryM;
    }

    public CountryMasterForm(CountryM countryM) {
        super();
        this.countryM = countryM;
    }

    public CountryMasterForm() {
        super();
        countryM = new CountryM();
        // TODO Auto-generated constructor stub
    }

}
