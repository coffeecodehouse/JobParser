package io.github.coffeecodehouse.personal_jobseeker.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.github.coffeecodehouse.personal_jobseeker.parser.domain.Job;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FerchauParserTest {

  @Autowired
  FerchauParser parser;

  @Test
  public void ferchauParser_executeParseMethod_saveTitle() throws Exception {
    Job job = parser.parse(Jsoup.connect(
        "https://www.ferchau.com/de/de/karriere/jobs-bewerbung/jobangebote/java-backend-entwickler-99620")
        .userAgent("Mozilla/4.0")
        .get());
    assertNotNull(job.getJobTitle());
    assertFalse(job.getJobTitle().isEmpty());
  }

  @Test
  public void ferchauParser_executeParseMethod_saveLocation() throws Exception {
    Job job = parser.parse(Jsoup.connect(
        "https://www.ferchau.com/de/de/karriere/jobs-bewerbung/jobangebote/java-backend-entwickler-99620")
        .userAgent("Mozilla/4.0")
        .get());
    assertNotNull(job.getLocation());
    assertFalse(job.getLocation().isEmpty());
  }

  @Test
  public void ferchauParser_executeParseMethod_saveJobTasks() throws Exception {
    Job job = parser.parse(Jsoup.connect(
        "https://www.ferchau.com/de/de/karriere/jobs-bewerbung/jobangebote/senior-developer-java-jee-159193")
        .get());
    assertNotNull(job.getJobTasks());
  }

  @Test
  public void ferchauParser_executeParseMethod_saveRequirements() throws Exception {
    Job job = parser.parse(Jsoup.connect(
        "https://www.ferchau.com/de/de/karriere/jobs-bewerbung/jobangebote/senior-developer-java-jee-159193")
        .get());
    assertNotNull(job.getRequirements());
  }


  @Test
  public void ferchauParser_executeParseMethodWithUnrealUrl_expectException() throws Exception {
    try {
      parser.parse(Jsoup.connect(
          "https://www.ferchau.com/de/de/karriere/jobs-bewerbung/jobangebote/NOT_SENIOR-developer-java-jee")
          .get());
      assertTrue(false);
    } catch (Exception e) {
      assertTrue(true);
    }
  }
}