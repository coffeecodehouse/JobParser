package io.github.coffeecodehouse.personal_jobseeker.parser;

import io.github.coffeecodehouse.personal_jobseeker.parser.domain.JobTitle;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class FerchauParser {

  public JobTitle parse(Document doc) {
    Elements jobRequirementElement = doc.select(".job_requirement");
    if (jobRequirementElement.isEmpty()) {
      throw new NullPointerException("Expected html element with job_requirement class");
    }
    JobTitle job = new JobTitle();
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

}
