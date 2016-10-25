package jdbcproj;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import jdbcproj.dao.DAOTeacher;
import jdbcproj.dao.daoteacher.DAOTeacherHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jdbcproj.dao.DAOGroup;
import jdbcproj.dao.DAOStudent;
import jdbcproj.dao.daogroup.DAOGroupHibernate;
import jdbcproj.dao.daostudent.DAOStudentHibernate;
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

	    DAOTeacher daoTeacher = new DAOTeacherHibernate(HibernateUtil.getSessionFactory());
		DAOGroup daoGroup = new DAOGroupHibernate(HibernateUtil.getSessionFactory());
		DAOStudent daoStudent = new DAOStudentHibernate(HibernateUtil.getSessionFactory());

	    try{
	        
	    	Teacher teacher = daoTeacher.getTeacher("Cecilia", "Fleming").get(0);
			Group group = daoGroup.getByName("111");

			daoTeacher.addGroup(teacher.getId(), group.getName());

	         System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("WOW!!!!");
            System.out.println();
            System.out.println(teacher.getGroups().size());
            for(Group i: teacher.getGroups()){
                System.out.println(i.getName());
            }
			
			
			teacher = daoTeacher.getTeacher("Cecilia", "Fleming").get(0);
			Set<Group> groups = teacher.getGroups();


			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("WOW!!!!");
			System.out.println();
			System.out.println(groups.size());
			for(Group i: groups){
				System.out.println(i.getName());
			}
            
	        /*
	        Student student = daoStudent.getStudent("Gene", "Fox").get(0);
	        Group group131 = daoGroup.getByName("131");
	        Group group111 = daoGroup.getByName("111");
	        
	        daoStudent.updateGroup(student.getId(), group131.getName());
	        HibernateUtil.closeSessionFactory();
	        
	        System.out.println("SACCESS");
	        System.out.println();
	        
	        System.out.println(student.getName() + " " + student.getFamilyName() + " " + student.getGroup().getName());
	        
	        System.out.println("Students in 111: \n");
	        for(Student student1: group111.getStudents()){
	            System.out.println(student1.getName() + " " + student1.getFamilyName() + " " + student1.getGroup().getName());
	        }
	        
	        System.out.println("Students in 131: \n");
            for(Student student1: group131.getStudents()){
                System.out.println(student1.getName() + " " + student1.getFamilyName() + " " + student1.getGroup().getName());
            }
            */
	    }catch(SQLException e){
	        e.printStackTrace();
	    }finally{
	    	//HibernateUtil.closeSessionFactory();
		}
	}
}