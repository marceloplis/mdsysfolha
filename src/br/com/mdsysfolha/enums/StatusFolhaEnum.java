package br.com.mdsysfolha.enums;

public enum StatusFolhaEnum {

	ABERTA("A"),
    GERADA("G");

    private final String value;

    private StatusFolhaEnum(final String newValue) {
        value = newValue;
    }

    public String getValue() { return value; }
	
}
