package br.com.mdsysfolha.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mdsysfolha.entity.UsuarioEntity;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		if(req.getSession().getAttribute("UsuarioSistema") != null){
			UsuarioEntity usuarioSession = (UsuarioEntity)req.getSession().getAttribute("UsuarioSistema");
			if(usuarioSession.getIdUsuario() == null){
				res.sendRedirect(req.getContextPath() + "/formlogin.do?method=sessaoinvalida");
			}
			chain.doFilter(request, response);
		}else{
			res.sendRedirect(req.getContextPath() + "/formlogin.do?method=sessaoinvalida");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
