package SoftwareDev.ExMAp.Model;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id; 
import javax.persistence.Table; 
import javax.persistence.UniqueConstraint;
import javax.persistence.Column;
/**
 * 
 */
@Entity
@Table(name ="experiment",
uniqueConstraints = {@UniqueConstraint(columnNames = { "id" }) })
public class Experiment {

    /**
     * Default constructor
     */
    public Experiment() {
    }
    
    private String id;


	/**
     * 
     */
    private String description;

    /**
     * 
     */
    private String domain;

    /**
     * 
     */
    private String goal;

    /**
     * 
     */
    private Date startDate;


    /**
     * 
     */
    private String name;

    @Id
    @Column(name = "ID")
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 150, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "DESCRIPTION", length = 500, nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "DOMAIN", length = 150, nullable = false)
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "GOAL", length = 150, nullable = false)
	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}




}