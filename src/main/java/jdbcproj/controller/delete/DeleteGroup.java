package jdbcproj.controller.delete;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.databaseservice.dao.DAOGroup;
import jdbcproj.databaseservice.dao.daogroup.DAOGroupHibernate;
import jdbcproj.databaseservice.hibernateutil.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 * Servlet delete group with specific name
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Component
public class DeleteGroup extends HttpServlet{

	private static final long serialVersionUID = 25376352337241L;

	@Autowired
	private DAOGroup daoGroup;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		String name = req.getParameter("name");
		String mes = "";
		try{
			daoGroup.delete(name);
			mes = "Operation was success";
		}catch(SQLException e){
			mes = "Can't do this operation.";
			e.printStackTrace();
		}

		req.setAttribute("message", mes);
		
		RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/delete/DeleteGroup.jsp");
		reqDisp.forward(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}