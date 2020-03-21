package br.com.mdsysfolha.enums;

public enum TipoArquivoEnum {


	FUNCIONARIO("F"),
    LANCAMENTOS("L");

    private final String value;

    private TipoArquivoEnum(final String newValue) {
        value = newValue;
    }

    public String getValue() { return value; }
	
}
