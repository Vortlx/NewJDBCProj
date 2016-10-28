package jdbcproj.controller.search;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.databaseservice.dao.DAOTeacher;
import jdbcproj.databaseservice.dao.daoteacher.DAOTeacherHibernate;
import jdbcproj.data.Teacher;
import jdbcproj.databaseservice.hibernateutil.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Servlet search teachers who curatoring specific group
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Component
public class FindTeachersByGroup extends HttpServlet{

    private static final long serialVersionUID = 731035L;

    @Autowired
    DAOTeacher daoTeacher;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    	try{
    		String groupName = req.getParameter("groupName");
    		List<Teacher> teachers = daoTeacher.getByGroup(groupName);
    		
    		req.setAttribute("groupName", groupName);
    		req.setAttribute("teachers", teachers);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	
    	ServletContext servletContext = getServletContext();
        RequestDispatcher disp = servletContext.getRequestDispatcher("/jsp/search/TeachersByGroup.jsp");
        disp.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }
}
