package jdbcproj.data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This class describe student.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class Student extends Person {

    @ManyToOne
    @JoinColumn(name="id_group")
    private Group group;

    public Student(){
        super();
        group = new Group();
    }

    public Student(int id, String name, String familyName, Group group){
        super(id, name, familyName);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}