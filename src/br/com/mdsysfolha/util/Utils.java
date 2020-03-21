package br.com.mdsysfolha.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String criptografaSenha (String senha) throws NoSuchAlgorithmException {   
	    MessageDigest md = MessageDigest.getInstance("MD5");   
	    BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));   
	    String s = hash.toString(32);   
	    if (s.length() %2 != 0)   
	        s = "0" + s;   
	    return s;   
	} 
	
	public static void main(String[] args) {
		try {
			System.out.println(criptografaSenha("123"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static final String converteDataString(Date data){ 	  
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");	  
	    return df.format(data);  
	}  
	  
	  
	public static final Date converteData(String data){	  
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
	    ParsePosition pos = new ParsePosition(0);  
	    Date retorno = df.parse(data, pos);  
	    return retorno;     
	}
	
	public static final String doubleToMoeda(double vlr){
		DecimalFormat df = new DecimalFormat();  
        df.applyPattern("#,##0.00");
		return df.format(vlr);
	}
	
	public static final Double MoedaStrToDouble(String vlr){	
		DecimalFormat df = new DecimalFormat("#,###,##0.00");	
		Double vlrDouble = 0.0;
		try {
			vlrDouble = Double.valueOf(df.parseObject(vlr).toString()).doubleValue();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vlrDouble;
	}
		
	public static String lpadS(String valueToPad, String filler, int size) {  
        StringBuilder builder = new StringBuilder();  
                  
        while (builder.length() + valueToPad.length() < size) {  
            builder.append(filler);  
        }  
        builder.append(valueToPad);  
        return builder.toString();  
    } 
}
