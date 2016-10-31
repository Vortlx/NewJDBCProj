package jdbcproj.controller.search;


import jdbcproj.databaseservice.dao.DAOTeacher;
import jdbcproj.databaseservice.dao.daoteacher.DAOTeacherHibernate;
import jdbcproj.data.Teacher;
import jdbcproj.databaseservice.hibernateutil.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet search teachers with specific properties
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Component
public class FindTeacher extends HttpServlet {

    private static final long serialVersionUID = 9878761265153L;

    @Autowired
    private DAOTeacher daoTeacher;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String name = req.getParameter("name");
        String familyName = req.getParameter("familyName");

        List<Teacher> list = new ArrayList<Teacher>();
        try{
            if("".equals(name) && "".equals(familyName)){
                list = daoTeacher.getAll();
            }else if(!"".equals(name) && "".equals(familyName)){
                list = daoTeacher.getByName(name);
            } else if("".equals(name) && !"".equals(familyName)){
                list = daoTeacher.getByFamilyName(familyName);
            }else{
                list = daoTeacher.getTeacher(name, familyName);
            }

            req.setAttribute("teachers", list);
        }catch(SQLException e){
        	e.printStackTrace();
        }

        ServletContext servletContext = getServletContext();
        RequestDispatcher disp = servletContext.getRequestDispatcher("/jsp/search/TeachersSearch.jsp");
        disp.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }

}
