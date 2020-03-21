package br.com.mdsysfolha.enums;

public enum TipoLancamentoEnum {

	CREDITO("C"),
    DEBITO("D");

    private final String value;

    private TipoLancamentoEnum(final String newValue) {
        value = newValue;
    }

    public String getValue() { return value; }
	
}
