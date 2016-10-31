package jdbcproj.controller.search;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.databaseservice.dao.DAOStudent;
import jdbcproj.databaseservice.dao.daostudent.DAOStudentHibernate;
import jdbcproj.data.Student;
import jdbcproj.databaseservice.hibernateutil.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Servlet search students with specific properties
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Component
public class FindStudent extends HttpServlet{

	private static final long serialVersionUID = 2387567823658L;

	@Autowired
	private DAOStudent daoStudent;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		String name = req.getParameter("name");
		String familyName = req.getParameter("familyName");
		
		List<Student> list = new ArrayList<Student>();
		try{
			if("".equals(name) && "".equals(familyName)){
				list = daoStudent.getAll();
			}else if(!"".equals(name) && "".equals(familyName)){
				list = daoStudent.getByName(name);
			} else if("".equals(name) && !"".equals(familyName)){
				list = daoStudent.getByFamilyName(familyName);
			}else{
				list = daoStudent.getStudent(name, familyName);
			}
		
			req.setAttribute("students", list);
		}catch(SQLException e){
			e.printStackTrace();
		}

		ServletContext servletContext = getServletContext();
		RequestDispatcher disp = servletContext.getRequestDispatcher("/jsp/search/StudentsSearch.jsp");
		disp.forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}