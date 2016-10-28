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

/**
 * Servlet search teachers who curatoring specific group
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class FindTeachersByGroup extends HttpServlet{

    private static final long serialVersionUID = 731035L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
    	DAOTeacher dao = new DAOTeacherHibernate(HibernateUtil.getSessionFactory());

    	try{
    		String groupName = req.getParameter("groupName");
    		List<Teacher> teachers = dao.getByGroup(groupName);
    		
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
