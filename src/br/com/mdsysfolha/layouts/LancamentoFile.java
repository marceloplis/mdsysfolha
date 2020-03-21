package br.com.mdsysfolha.layouts;

import java.util.Date;

import org.coury.jfilehelpers.annotations.FieldAlign;
import org.coury.jfilehelpers.annotations.FieldConverter;
import org.coury.jfilehelpers.annotations.FieldFixedLength;
import org.coury.jfilehelpers.annotations.FieldTrim;
import org.coury.jfilehelpers.annotations.FixedLengthRecord;
import org.coury.jfilehelpers.enums.AlignMode;
import org.coury.jfilehelpers.enums.ConverterKind;
import org.coury.jfilehelpers.enums.TrimMode;

@FixedLengthRecord()
public class LancamentoFile {

	@FieldFixedLength(1)
	public String tipoLancamento;
	
	@FieldAlign(alignMode=AlignMode.Right)
	@FieldFixedLength(11)
	public String cpf;
	
	@FieldTrim(trimMode=TrimMode.Right)
    @FieldFixedLength(10)
	@FieldConverter(converter = ConverterKind.Date, format = "dd-MM-yyyy")
    public Date data;	
	
	@FieldAlign(alignMode=AlignMode.Right)
    @FieldFixedLength(45)
	public String descricao;

	@FieldAlign(alignMode=AlignMode.Right)
	@FieldFixedLength(1)
	public String tipoMoeda;
	
	@FieldAlign(alignMode = AlignMode.Right, alignChar = '0')
    @FieldFixedLength(10)
    public double valor;
	
	
}
