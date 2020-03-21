package br.com.mdsysfolha.action.common;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import br.com.mdsysfolha.util.HibernateUtil;

public class GlobalExceptionHandler extends ExceptionHandler {
	
    @Override
    public ActionForward execute(
                Exception ex, 
                ExceptionConfig exConfig, 
                ActionMapping mapping, 
                ActionForm formInstance, 
                HttpServletRequest request, 
                HttpServletResponse response) 
    throws ServletException {
          
        request.setAttribute("tipo", ex.getClass().getName());
        request.setAttribute("causa", ex.getCause());
        request.setAttribute("mensagem", ex.getMessage() + "\n\n" + ex.getCause());
        request.setAttribute("stacktrace", stackTraceToString(ex));     
       
        request.getSession().setAttribute("errorSystem", "S");
        if(HibernateUtil.currentSession().getTransaction() != null)
        	HibernateUtil.currentSession().getTransaction().rollback();
        HibernateUtil.clearSession();
        
        return mapping.findForward("error");                 
         
          
    }
    
    private static String stackTraceToString(Throwable t) {
          StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

	
}
