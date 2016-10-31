package jdbcproj.controller.delete;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcproj.databaseservice.dao.DAOTeacher;
import jdbcproj.databaseservice.dao.daoteacher.DAOTeacherHibernate;
import jdbcproj.databaseservice.hibernateutil.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Servlet delete curator of specific group
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Deprecated
public class DeleteCurator extends HttpServlet {

    private static final long serialVersionUID = 25370000004441L;

    private DAOTeacher daoTeacher;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try{
            int teacherID = Integer.parseInt(req.getParameter("teacherID"));
            String groupName= req.getParameter("groupName");

            daoTeacher.deleteCurator(teacherID, groupName);
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
