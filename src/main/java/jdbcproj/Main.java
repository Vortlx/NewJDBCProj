package jdbcproj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jdbcproj.data.Group;
import jdbcproj.data.Student;
import jdbcproj.data.Teacher;
import jdbcproj.hibernateutil.HibernateUtil;

/**
 * This Class using for testing other classes.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class Main{
	public static void main(String[] args){

	    SessionFactory sessionFactory = null;
	    
	    try{
	        sessionFactory = HibernateUtil.getSessionFactory();
	        
	        Session session = sessionFactory.getCurrentSession();
	        session.beginTransaction();
	        
	        Group group = (Group) session.get(Group.class, 4);
	        
	        System.out.println();
            System.out.println("Teachers: \n");
            for(Teacher teacher: group.getTeachers()){
                System.out.println(teacher.getName() + " " + teacher.getFamilyName());
            }
	        
	        System.out.println();
	        System.out.println("Students: \n");
	        for(Student student: group.getStudents()){
	            System.out.println(student.getName() + " " + student.getFamilyName());
	        }
	        
	        session.getTransaction().commit();
	    }finally{
	        if(sessionFactory != null && sessionFactory.isOpen())
	            HibernateUtil.closeSessionFactory();
	    }
	}
}