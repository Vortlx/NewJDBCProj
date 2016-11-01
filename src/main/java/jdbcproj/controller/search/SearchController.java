package jdbcproj.controller.search;


import jdbcproj.data.Group;
import jdbcproj.data.Student;
import jdbcproj.data.Teacher;
import jdbcproj.databaseservice.dao.DAOGroup;
import jdbcproj.databaseservice.dao.DAOStudent;
import jdbcproj.databaseservice.dao.DAOTeacher;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    private DAOGroup daoGroup;
    private DAOStudent daoStudent;
    private DAOTeacher daoTeacher;

    @Autowired
    public SearchController(DAOGroup daoGroup, DAOStudent daoStudent, DAOTeacher daoTeacher){
        this.daoGroup = daoGroup;
        this.daoStudent = daoStudent;
        this.daoTeacher = daoTeacher;
    }

    @RequestMapping(value = "/jsp/search/FindGroupServ", method = RequestMethod.POST)
    public String findGroup(@RequestParam("name") String groupName, Model model){

        List<Group> groups = new ArrayList<Group>();

        try{
            if("".equals(groupName)){
                groups = daoGroup.getAll();
            }else if(!"".equals(groupName)) {
                Group oneGroup = daoGroup.getByName(groupName);
                groups.add(oneGroup);
            }
            model.addAttribute("groups", groups);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "search/GroupsSearch";
        }
    }

    @RequestMapping(value = "/jsp/search/FindGroupByTeacherServ", method = RequestMethod.POST)
    public String findGroupByTeacher(@RequestParam("groupName") String groupName, Model model){

        try{
            Group group = daoGroup.getByName(groupName);
            model.addAttribute("group", group);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "search/GroupsSearchByTeacher";
        }
    }

    @RequestMapping(value = "/jsp/search/FindStudentServ", method = RequestMethod.POST)
    public String findStudent(@RequestParam("name") String name, @RequestParam("familyName")  String familyName,
                              Model model){

        List<Student> students = new ArrayList<Student>();

        try{
            if("".equals(name) && "".equals(familyName)){
                students = daoStudent.getAll();
            }else if(!"".equals(name) && "".equals(familyName)){
                students = daoStudent.getByName(name);
            } else if("".equals(name) && !"".equals(familyName)){
                students = daoStudent.getByFamilyName(familyName);
            }else{
                students = daoStudent.getStudent(name, familyName);
            }

            model.addAttribute("students", students);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "search/StudentsSearch";
        }
    }

    @RequestMapping(value = "/jsp/search/FindTeacherServ", method = RequestMethod.POST)
    public String findTeacher(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                              Model model){

        List<Teacher> teachers = new ArrayList<Teacher>();

        try{
            if("".equals(name) && "".equals(familyName)){
                teachers = daoTeacher.getAll();
            }else if(!"".equals(name) && "".equals(familyName)){
                teachers = daoTeacher.getByName(name);
            } else if("".equals(name) && !"".equals(familyName)){
                teachers = daoTeacher.getByFamilyName(familyName);
            }else{
                teachers = daoTeacher.getTeacher(name, familyName);
            }

            model.addAttribute("teachers", teachers);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "search/TeachersSearch";
        }
    }

    @RequestMapping(value = "/jsp/search/TeachersByGroupServ", method = RequestMethod.POST)
    public String findTeacherByGroup(@RequestParam("groupName") String groupName, Model model){

        List<Teacher> teachers = new ArrayList<Teacher>();

        try{
            teachers = daoTeacher.getByGroup(groupName);

            model.addAttribute("groupName", groupName);
            model.addAttribute("teachers", teachers);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "search/TeachersByGroup";
        }
    }
}