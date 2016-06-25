package dictionary.muktobangla.model;

import javafx.beans.property.SimpleStringProperty;

public class OvidhanMeaning {
    private SimpleStringProperty enword;
    private SimpleStringProperty bnword;

    public OvidhanMeaning(String enword, String bnword) {
        this.enword = new SimpleStringProperty(enword);
        this.bnword = new SimpleStringProperty(bnword);
    }

    public OvidhanMeaning(int i) {
    }

    public String getenword() {
        return enword.get();
    }

    public SimpleStringProperty enwordProperty() {
        return enword;
    }

    public String getbnword() {
        return bnword.get();
    }

    public SimpleStringProperty bnwordProperty() {
        return bnword;
    }

    public boolean contains(String filter) {
        if (this.getenword()==(filter))
            return true;
        else
            return false;
    }
}
