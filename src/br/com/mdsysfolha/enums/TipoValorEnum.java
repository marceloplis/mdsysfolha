package br.com.mdsysfolha.enums;

public enum TipoValorEnum {

	PORCENTAGEM("P"),
    MOEDA("M");

    private final String value;

    private TipoValorEnum(final String newValue) {
        value = newValue;
    }

    public String getValue() { return value; }
	
}
