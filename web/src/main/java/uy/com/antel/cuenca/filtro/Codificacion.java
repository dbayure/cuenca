package uy.com.antel.cuenca.filtro;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Codificacion implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest pedido, ServletResponse respuesta,
			FilterChain cadena) throws IOException, ServletException {
			respuesta.setCharacterEncoding("UTF-8");
			pedido.setCharacterEncoding("UTF-8");
			cadena.doFilter(pedido, respuesta);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}