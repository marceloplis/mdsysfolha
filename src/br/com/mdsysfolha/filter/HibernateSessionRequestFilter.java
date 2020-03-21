package br.com.mdsysfolha.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import br.com.mdsysfolha.util.HibernateUtil;

public class HibernateSessionRequestFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		Transaction tx = null;
		try {
			tx = HibernateUtil.currentSession().beginTransaction();

			// Continua a executar a ação
			chain.doFilter(request, response);

			if (tx != null && !tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException e) {
			try {
				if (tx != null) {
					tx.rollback();
				}
			} catch (HibernateException e1) {
				throw new HibernateException("Erro de Persistencia na base de dados: " + e1);
			}
			throw new HibernateException("Erro de Persistencia na base de dados: " + e);
		}finally {
			HibernateUtil.closeSession();
		}
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}

}