package jdbcproj.controller.add;


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
public class AddController {

    private DAOGroup daoGroup;
    private DAOStudent daoStudent;
    private DAOTeacher daoTeacher;

    @Autowired
    public AddController(DAOGroup daoGroup, DAOStudent daoStudent, DAOTeacher daoTeacher){
        this.daoGroup = daoGroup;
        this.daoStudent = daoStudent;
        this.daoTeacher = daoTeacher;
    }

    @RequestMapping(value = "/jsp/add/AddCuratorServ", method = RequestMethod.POST)
    public String addCurator(@RequestParam("teacherID") int teacherID,
                             @RequestParam("groupName") String groupName, Model model){

        String message = null;

        try{
            daoTeacher.addGroup(teacherID, groupName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";

        }finally{
            model.addAttribute("message", message);
            return "search/TeachersSearch";
        }
    }

    @RequestMapping(value = "/jsp/add/AddGroupServ", method = RequestMethod.POST)
    public String addGroup(@RequestParam("name") String groupName, Model model){

        String message = null;

        try{
            daoGroup.add(groupName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
            model.addAttribute("message", message);
        }finally{
            model.addAttribute("message", message);
            return "add/AddGroup";
        }
    }

    @RequestMapping(value = "/jsp/add/AddStudentServ", method = RequestMethod.POST)
    public String addStudent(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                             @RequestParam("group") String groupName, Model model){

        String message = null;

        try{
            daoStudent.add(name, familyName, groupName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
            model.addAttribute("message", message);
        }finally{
            model.addAttribute("message", message);
            return "add/AddStudent";
        }
    }

    @RequestMapping(value = "/jsp/add/AddTeacherServ", method = RequestMethod.POST)
    public String addTeacher(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                             Model model){

        String message = null;

        try{
            daoTeacher.add(name, familyName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
        }finally{
            model.addAttribute("message", message);
            return "add/AddTeacher";
        }
    }
}