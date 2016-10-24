package jdbcproj;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

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

	    DAOGroup daoGroup = new DAOGroupHibernate(HibernateUtil.getSessionFactory());
	    
	    try{
	        List<Group> groups = daoGroup.getAll();
	        
	        List<Student> students = groups.get(0).getStudents();
	        for(Student student: students){
	            System.out.println(student.getName() + " " + student.getFamilyName());
	        }
	    }catch(SQLException e){
	        e.printStackTrace();
	    }

	}
}