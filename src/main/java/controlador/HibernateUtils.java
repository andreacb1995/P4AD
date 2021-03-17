package controlador;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.xdevapi.Session;

public enum HibernateUtils {
	INSTANCE;

	public static SessionFactory getSessionFactory() {
		return new Configuration().configure().buildSessionFactory();
	}

	public void cerrarSessionFactory() {
		getSessionFactory().close();
	}

}
