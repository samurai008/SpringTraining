package utilities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtility {

	//XML based configuration
	private static SessionFactory sessionFactory;
		
    private static SessionFactory buildSessionFactory() {
        try {
            //Step1: Create the SessionFactory from hibernate.cfg.xml
        	Configuration configuration = new Configuration().
        			configure("services/hibernate.cfg.xml");
        	//configuration.addAnnotatedClass(EmployeeEntityBean.class);
        	
        	System.out.println("Hibernate Configuration loaded");
        	
        	//Step2: Registering the configurations 
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        	System.out.println("Hibernate serviceRegistry created");
        	
        	//Step3: Creating the session factory usng the configurations and srviceregistery
        	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	System.out.println("Created Session Factory");
        	
        	
        	/*final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        	sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();*/
        	
        	
            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
	
	public static void closeSessionFactory(){
		
		if(sessionFactory != null && sessionFactory.isClosed()==false ) sessionFactory.close();
	}
	
}
