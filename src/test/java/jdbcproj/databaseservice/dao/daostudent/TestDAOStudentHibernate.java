package jdbcproj.databaseservice.dao.daostudent;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import jdbcproj.databaseservice.dao.DAOGroup;
import jdbcproj.databaseservice.dao.DAOStudent;
import jdbcproj.databaseservice.dao.daogroup.DAOGroupHibernate;
import jdbcproj.data.Group;
import jdbcproj.data.Student;
import jdbcproj.databaseservice.hibernateutil.HibernateUtil;

public class TestDAOStudentHibernate {

    private static final DAOGroup daoGroup = new DAOGroupHibernate(HibernateUtil.getSessionFactory());
    private static final DAOStudent daoStudent = new DAOStudentHibernate(HibernateUtil.getSessionFactory());
    private static final String studentName = "TestName";
    private static final String studentFamilyName = "TestFamilyName";
    private static final String groupName = "TestGroupName";
    
    @BeforeClass
    public static void addDataToTable(){
        try{
            daoGroup.add(groupName);
            daoStudent.add(studentName, studentFamilyName, groupName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void deleteDataFromTable(){
        try{
            daoGroup.delete(groupName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testUpdate(){
        
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testUpdateGroup(){
        
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testGetAll(){
        
    }
    
    @Test
    public void testGetByName(){
        try{
            Group group = daoGroup.getByName(groupName);
            Student student = new Student();
            student.setName(studentName);
            student.setFamilyName(studentFamilyName);
            student.setGroup(group);
            
            List<Student> students = daoStudent.getByName(studentName);

            Assert.assertTrue(students.contains(student));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetByFamilyName(){
        try{
            Group group = daoGroup.getByName(groupName);
            Student student = new Student();
            student.setName(studentName);
            student.setFamilyName(studentFamilyName);
            student.setGroup(group);
            
            List<Student> students = daoStudent.getByFamilyName(studentFamilyName);
            
            Assert.assertTrue(students.contains(student));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetStudent(){
        try{
            Group group = daoGroup.getByName(groupName);
            Student student = new Student();
            student.setName(studentName);
            student.setFamilyName(studentFamilyName);
            student.setGroup(group);

            List<Student> students = daoStudent.getStudent(studentName, studentFamilyName);
            
            Assert.assertTrue(students.contains(student));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}