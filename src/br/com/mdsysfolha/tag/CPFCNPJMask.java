package br.com.mdsysfolha.tag;

public class CPFCNPJMask {

    private static CPFCNPJMask instance = new CPFCNPJMask();  

    private CPFCNPJMask() { }   

    public static final CPFCNPJMask getInstance() {
          return instance;
    }   

    public String format(String value) {        

    	String sRet;
    	value = value.trim();
      	int len = value.length();

      	if (len > 11) {
      		if (len == 14) {
      			sRet = value.substring(0, 2) + "." + value.substring(2, 5) + "." + value.substring(5, 8) + "/" + value.substring(8, 12) + "-" + value.substring(12, 14);
      		} else {
      			sRet = value.substring(0, 3) + "." + value.substring(3, 6) + "." + value.substring(6, 9) + "/" + value.substring(9, 13) + "-" + value.substring(13, 15);
      		}
      	} else {
      		// CPF 999.999.999-99
      		sRet = value.substring(0, 3) + "." + value.substring(3, 6) + "." + value.substring(6, 9) + "-" + value.substring(9, 11);
      	}

      	return sRet;       

    }

    public String unFormat(String value) {
          return null;
    }

}
