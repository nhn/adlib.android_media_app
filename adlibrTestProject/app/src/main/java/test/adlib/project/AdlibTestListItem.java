package test.adlib.project;

public enum AdlibTestListItem {

    BANNER_HEADER("Banner", true),
    BANNER_MEDIATION("Mediation"),
    BANNER_DYNAMIC("Dynamic"),
    HALF_HEADER("Half", true),
    HALF_MEDIATION("Mediation"),
    INTERSTITIAL_HEADER("Interstitial", true),
    INTERSTITIAL_MEDIATION("Mediation"),
    INTERSTITIAL_DYNAMIC("Dynamic");

    private String value;
    private boolean isHeader = false;

    AdlibTestListItem(String value) {
        this.value = value;
    }

    AdlibTestListItem(String value, boolean isHeader) {
        this.value = value;
        this.isHeader = isHeader;
    }

    public String getValue() {
        return value;
    }

    public boolean isHeader() {
        return isHeader;
    }
}
