package jdbcproj.controller.add;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.databaseservice.dao.DAOTeacher;
import jdbcproj.databaseservice.dao.daoteacher.DAOTeacherHibernate;
import jdbcproj.databaseservice.hibernateutil.HibernateUtil;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Servlet add curator of specific group
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class AddCurator extends HttpServlet {
    private static final long serialVersionUID = 25777523771141L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        DAOTeacher daoTeachers = new DAOTeacherHibernate(HibernateUtil.getSessionFactory());

        try{
            int teacherID = Integer.parseInt(req.getParameter("teacherID"));
            String groupName = req.getParameter("groupName");

            daoTeachers.addGroup(teacherID, groupName);

        }catch(SQLException e){
        	e.printStackTrace();
            String message = "Can't do this operation.";
            req.setAttribute("message", message);

            RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/add/AddCurator.jsp");
            reqDisp.forward(req, res);
        }

        RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/search/TeachersSearch.jsp");
        reqDisp.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }
}
