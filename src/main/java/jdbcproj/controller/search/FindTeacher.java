package jdbcproj.controller.search;


import jdbcproj.dao.DAOTeacher;
import jdbcproj.dao.daoteacher.DAOTeacherHibernate;
import jdbcproj.data.Teacher;
import jdbcproj.hibernateutil.HibernateUtil;

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
public class FindTeacher extends HttpServlet {
    private static final long serialVersionUID = 9878761265153L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        DAOTeacher connToTeacher = new DAOTeacherHibernate(HibernateUtil.getSessionFactory());

        String name = req.getParameter("name");
        String familyName = req.getParameter("familyName");

        List<Teacher> list = new ArrayList<Teacher>();
        try{
            if("".equals(name) && "".equals(familyName)){
                list = connToTeacher.getAll();
            }else if(!"".equals(name) && "".equals(familyName)){
                list = connToTeacher.getByName(name);
            } else if("".equals(name) && !"".equals(familyName)){
                list = connToTeacher.getByFamilyName(familyName);
            }else{
                list = connToTeacher.getTeacher(name, familyName);
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
