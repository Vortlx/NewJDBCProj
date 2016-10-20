package jdbcproj.data;


import java.util.Set;
import java.util.HashSet;

import javax.persistence.ManyToMany;


/**
 * This class describe teacher.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class Teacher extends Person {

    @ManyToMany(mappedBy="teachers")
    private Set<Group> groups;

    public Teacher(){
        super();
    }

    public Teacher(int id, String name, String familyName){
        super(id, name, familyName);
        groups = new HashSet<Group>();
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group newGroup){
        groups.add(newGroup);
    }

    public void set(HashSet<Group> newGroups){
        groups = newGroups;
    }
}
