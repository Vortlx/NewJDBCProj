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
	        List<Teacher> teachers = daoTeacher.getByGroup("111");
	        
	        for(Teacher teacher: teachers){
	            System.out.println(teacher.getName() + " " + teacher.getFamilyName());
	        }
	        
	    }catch(SQLException e){
	        e.printStackTrace();
	    }finally{
	    	//HibernateUtil.closeSessionFactory();
		}
	}
}