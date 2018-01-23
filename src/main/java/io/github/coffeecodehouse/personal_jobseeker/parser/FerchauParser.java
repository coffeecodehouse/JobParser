package io.github.coffeecodehouse.personal_jobseeker.parser;

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


  public FerchauParser parse(Document doc) {
    Elements jobRequirementElement = doc.select(".job_requirement");
    if (jobRequirementElement.isEmpty()) {
      throw new NullPointerException("Expected html element with job_requirement class");
    }
    requirements = jobRequirementElement.select("li").stream().map(Element::text)
        .collect(Collectors.toList());
    Elements jobTasksElement = doc.select(".job_task");
    if(jobTasksElement.isEmpty()) {
      // TODO: 1/23/18 decide to throw exception
    }
    jobTasks = jobTasksElement.select("li").stream().map(Element::text)
        .collect(Collectors.toList());
    location = doc.select("h3[itemprop=jobLocation]").text();
    jobTitle = doc.select("h2[itemprop=title]").text();
    return this;
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
