package jdbcproj.data;


import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * This class describe groups in university
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Entity
@Table(name="groups")
public class Group {

    @Id
    @Column(name="id")
	int id;
    
    @Column(name="name")
	private String name;
    
    @OneToMany(mappedBy="group")
	private List<Student> students;
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="curator", 
            joinColumns={@JoinColumn(name="id_group")}, 
            inverseJoinColumns={@JoinColumn(name="id_teacher")})
	private Set<Teacher> teachers;
	
	public Group(){
		
	}
	
	public Group(int id, String name){
		this.id = id;
		this.name = name;
		students = new ArrayList<Student>();
		teachers = new HashSet<Teacher>();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
	    this.id = id;
	}

	public void addStudent(Student student){
		students.add(student);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((students == null) ? 0 : students.hashCode());
        result = prime * result + ((teachers == null) ? 0 : teachers.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (students == null) {
            if (other.students != null)
                return false;
        } else if (!students.equals(other.students))
            return false;
        if (teachers == null) {
            if (other.teachers != null)
                return false;
        } else if (!teachers.equals(other.teachers))
            return false;
        return true;
    }
}