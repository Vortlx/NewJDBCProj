package jdbcproj.controller.search;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.dao.DAOGroup;
import jdbcproj.dao.daogroup.DAOGroupHibernate;
import jdbcproj.data.Group;
import jdbcproj.hibernateutil.HibernateUtil;

/**
 * Servlet search specific group of teacher
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class FindGroupByTeacher extends HttpServlet{

    private static final long serialVersionUID = 73111096375035L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
    	DAOGroup dao = new DAOGroupHibernate(HibernateUtil.getSessionFactory());
   	
    	try{
    		String groupName = req.getParameter("groupName");
    		Group group = dao.getByName(groupName);
    		
    		req.setAttribute("group", group);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	
    	ServletContext servletContext = getServletContext();
        RequestDispatcher disp = servletContext.getRequestDispatcher("/jsp/search/GroupsSearchByTeacher.jsp");
        disp.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }
}
