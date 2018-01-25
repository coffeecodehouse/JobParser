package io.github.coffeecodehouse.personal_jobseeker.parser.domain;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class JobTitle {

  @Id
  private Long id;
  private String location;
  private String jobTitle;
  @ElementCollection
  @CollectionTable(name="requirements", joinColumns=@JoinColumn(name="user_id"))
  private List<String> requirements;
  @ElementCollection
  @CollectionTable(name="job_tasks", joinColumns=@JoinColumn(name="user_id"))
  private List<String> jobTasks;


  public JobTitle() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public List<String> getRequirements() {
    return requirements;
  }

  public void setRequirements(List<String> requirements) {
    this.requirements = requirements;
  }

  public List<String> getJobTasks() {
    return jobTasks;
  }

  public void setJobTasks(List<String> jobTasks) {
    this.jobTasks = jobTasks;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("JobTitle{");
    sb.append("id=").append(id);
    sb.append(", location='").append(location).append('\'');
    sb.append(", jobTitle='").append(jobTitle).append('\'');
    sb.append(", requirements=").append(requirements);
    sb.append(", jobTasks=").append(jobTasks);
    sb.append('}');
    return sb.toString();
  }
}
