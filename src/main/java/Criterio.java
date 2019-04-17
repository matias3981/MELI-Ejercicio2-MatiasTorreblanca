public enum Criterio {
    ADDRESS_LINE ("address_line"),
    AGENCY_CODE ("agency_code"),
    DISTANCE ("distance");

    private String criteria;

    Criterio(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}
