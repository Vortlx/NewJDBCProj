package jdbcproj.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This abstract class describe a person. For example student or teacher.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@MappedSuperclass
public abstract class Person {
	
    @Id
    @Column(name="id")
	private int id;
    
    @Column(name="name")
	private String name;
    
    @Column(name="family_name")
	private String familyName;
	
	public Person(){
		
	}
	
	public Person(int id, String name, String familyName){
		this.id = id;
		this.name = name;
		this.familyName = familyName;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
}