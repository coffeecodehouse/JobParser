package io.github.coffeecodehouse.personal_jobseeker.parser;

import io.github.coffeecodehouse.personal_jobseeker.parser.domain.Job;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class FerchauParser {

  private List<String> requirements;
  private List<String> jobTasks;
  private String location;
  private String jobTitle;


  public Job parse(Document doc) {
    Elements jobRequirementElement = doc.select(".job_requirement");
    if (jobRequirementElement.isEmpty()) {
      throw new NullPointerException("Expected html element with job_requirement class");
    }
    Job job = new Job();
    job.setRequirements(jobRequirementElement.select("li").stream().map(Element::text)
        .collect(Collectors.toList()));
    Elements jobTasksElement = doc.select(".job_task");
    if(jobTasksElement.isEmpty()) {
      // TODO: 1/23/18 decide to throw exception
    }
    job.setJobTasks(jobTasksElement.select("li").stream().map(Element::text)
        .collect(Collectors.toList()));
    job.setLocation(doc.select("h3[itemprop=jobLocation]").text());
    job.setJobTitle(doc.select("h2[itemprop=title]").text());
    return job;
  }

  public List<String> getRequirements() {
    return requirements;
  }

  public List<String> getJobTasks() {
    return jobTasks;
  }

  public String getLocation() {
    return location;
  }

  public String getJobTitle() {
    return jobTitle;
  }
}
