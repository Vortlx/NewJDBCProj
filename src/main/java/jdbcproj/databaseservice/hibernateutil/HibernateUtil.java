package jdbcproj.databaseservice.hibernateutil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static{
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch(Throwable ex){
            System.err.println("NOOOOOOOOOOOOOOOO" + ex);
            throw new ExceptionInInitializerError();
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    public static void closeSessionFactory(){
        sessionFactory.close();
    }
}
