package jdbcproj.controller.delete;

import jdbcproj.databaseservice.dao.DAOGroup;
import jdbcproj.databaseservice.dao.DAOStudent;
import jdbcproj.databaseservice.dao.DAOTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/NewJDBCProj/jsp/delete")
public class DeleteController {

    @Autowired
    private DAOGroup daoGroup;
    
    @Autowired
    private DAOStudent daoStudent;
    
    @Autowired
    private DAOTeacher daoTeacher;

    
    @RequestMapping(value = "/DeleteCuratorServ", method = RequestMethod.POST)
    public String deleteCurator(@RequestParam("teacherID") int teacherID, @RequestParam("groupName") String groupName,
                                Model model){

        String message = null;

        try{
            daoTeacher.deleteCurator(teacherID, groupName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
            model.addAttribute("message", message);
        }finally{
            model.addAttribute("message", message);
            return "search/TeachersSearch";
        }
    }

    @RequestMapping(value = "/DeleteGroupServ", method = RequestMethod.POST)
    public String deleteGroup(@RequestParam("name") String groupName, Model model){

        String message = null;

        try{
            daoGroup.delete(groupName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
            model.addAttribute("message", message);
        }finally{
            model.addAttribute("message", message);
            return "delete/DeleteGroup";
        }
    }

    @RequestMapping(value = "/DeleteStudentServ", method = RequestMethod.POST)
    public String deleteStudent(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                                Model model){

        String message = null;

        try{
            daoStudent.delete(name, familyName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
            model.addAttribute("message", message);
        }finally{
            model.addAttribute("message", message);
            return "delete/DeleteStudent";
        }
    }

    @RequestMapping(value = "/DeleteTeacherServ", method = RequestMethod.POST)
    public String deleteTeacher(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                                Model model){

        String message = null;

        try{
            daoTeacher.delete(name, familyName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
            model.addAttribute("message", message);
        }finally{
            model.addAttribute("message", message);
            return "delete/DeleteTeacher";
        }
    }
}