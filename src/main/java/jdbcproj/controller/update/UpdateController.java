package jdbcproj.controller.update;


import jdbcproj.databaseservice.dao.DAOStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateController {

    private DAOStudent daoStudent;

    @Autowired
    public UpdateController(DAOStudent daoStudent){
        this.daoStudent = daoStudent;
    }

    @RequestMapping(value = "/jsp/update/ChangeGroupServ", method = RequestMethod.POST)
    public String changeGroup(@RequestParam("studentID") int studentID, @RequestParam("newGroupName") String newGroupName,
                              Model model){

        String message = null;

        try{
            daoStudent.updateGroup(studentID, newGroupName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
        }finally{
            model.addAttribute("message", message);
            return "search/StudentsSearch";
        }
    }
}
