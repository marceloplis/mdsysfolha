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
public class FuncionarioFile {

	@FieldFixedLength(11)
	public String cpf;
	
	@FieldAlign(alignMode=AlignMode.Right)
    @FieldFixedLength(100)
	public String nome;
		
    @FieldAlign(alignMode = AlignMode.Right, alignChar = '0')
    @FieldFixedLength(10)
    public double salario;
	
	@FieldAlign(alignMode=AlignMode.Right)
    @FieldFixedLength(2)
	public Integer loja;
	
	
	@FieldTrim(trimMode=TrimMode.Right)
    @FieldFixedLength(10)
    @FieldConverter(converter = ConverterKind.Date, format = "dd-MM-yyyy")
    public Date dataAdmissao;
	
	@FieldAlign(alignMode=AlignMode.Right)
    @FieldFixedLength(1)
	public String status;
	
}
