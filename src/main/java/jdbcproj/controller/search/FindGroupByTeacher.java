package jdbcproj.controller.search;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.databaseservice.dao.DAOGroup;
import jdbcproj.databaseservice.dao.daogroup.DAOGroupHibernate;
import jdbcproj.data.Group;
import jdbcproj.databaseservice.hibernateutil.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Servlet search specific group of teacher
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Component
public class FindGroupByTeacher extends HttpServlet{

    private static final long serialVersionUID = 73111096375035L;

    @Autowired
    private DAOGroup daoGroup;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    	try{
    		String groupName = req.getParameter("groupName");
    		Group group = daoGroup.getByName(groupName);
    		
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
