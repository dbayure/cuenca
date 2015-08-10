package uy.com.antel.cuenca.bean;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean {
	public void logout() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		ec.invalidateSession();
		ec.redirect(ec.getRequestContextPath() + "/paginas/persona");
	}
}