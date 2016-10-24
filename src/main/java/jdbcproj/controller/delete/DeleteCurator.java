package jdbcproj.controller.delete;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.dao.DAOTeacher;
import jdbcproj.dao.daoteacher.DAOTeacherHibernate;
import jdbcproj.hibernateutil.HibernateUtil;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Servlet delete curator of specific group
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class DeleteCurator extends HttpServlet {

    private static final long serialVersionUID = 25370000004441L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        DAOTeacher dao = new DAOTeacherHibernate(HibernateUtil.getSessionFactory());

        try{
            int teacherID = Integer.parseInt(req.getParameter("teacherID"));
            String groupName= req.getParameter("groupName");

            dao.deleteCurator(teacherID, groupName);
        }catch(SQLException e){
            e.printStackTrace();
        }

        RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/search/TeachersSearch.jsp");
        reqDisp.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }
}
