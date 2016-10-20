package jdbcproj.data;


import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private Set<Student> students;
    
    @ManyToMany
    @JoinTable(name="curator", 
            joinColumns={@JoinColumn(name="id_group")}, 
            inverseJoinColumns={@JoinColumn(name="id_teacher")})
	private Set<Teacher> teachers;
	
	public Group(){
		
	}
	
	public Group(int id, String name){
		this.id = id;
		this.name = name;
		students = new HashSet<Student>();
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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}