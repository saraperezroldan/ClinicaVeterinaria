package com.clinica.clinicaVeterinaria.web.config;

import java.lang.reflect.Method;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Configuración para controlar cuando los contextos de la aplicación son destruidos
 * @author Vailos Portatil
 *
 */
@Configuration
public class ServletContextListenerConfig implements ServletContextListener{

	private static final Logger log = LoggerFactory.getLogger(ServletContextListenerConfig.class);

	@Value("${spring.datasource.custom.abandoned-connection.package : #{null}}")
	private String packageConnectionAbandoned;
	
	@Value("${spring.datasource.custom.abandoned-connection.method : #{null}}")
	private String methodConnectionAbandoned;
	
	@Override
    public final void contextDestroyed(ServletContextEvent servletContextEvent) {
		log.info("Destroying Context...");
		if(!canCleanAbandonedConnection(packageConnectionAbandoned, methodConnectionAbandoned)) {
			return;
		}
		cleanAbandonedConnection();
    }

	private void cleanAbandonedConnection() {
        log.info("Calling {} shutdown", packageConnectionAbandoned.concat(".").concat(methodConnectionAbandoned));
        
        // Find class and method by reflection and execute it.
        try {
            Class<?> clazz=Class.forName(packageConnectionAbandoned);
            Method method=(clazz==null ? null : clazz.getMethod(methodConnectionAbandoned));
            if(method!=null) { 
            	method.invoke(null); 
            }
        } catch (Throwable thr) {
            thr.printStackTrace();
        }
        
        deregisterJdbcDrivers();
	}
    
	private void deregisterJdbcDrivers() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == classLoader) {
                try {
                    log.info("Deregistering JDBC driver {}", driver);
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    log.error("Error deregistering JDBC driver {}", driver, ex);
                }
            }
        }
	}
	
	private boolean canCleanAbandonedConnection(String pack, String method) {
		return (StringUtils.hasText(pack) && StringUtils.hasText(method));
	}
}
