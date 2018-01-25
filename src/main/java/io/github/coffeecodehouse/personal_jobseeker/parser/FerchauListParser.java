package io.github.coffeecodehouse.personal_jobseeker.parser;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class FerchauListParser {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";

  public static void main(String[] args) throws IOException {
    List<String> links = new FerchauListParser().parse(Jsoup.connect(
        "https://www.ferchau.com/de/de/karriere/jobs-bewerbung/jobangebote/search/young-professional/deutschland/java/")
        .get());
    System.out.println(links.stream().map(link -> {
      try {
        return new FerchauParser().parse(Jsoup.connect(link).get());
      } catch (IOException e) {
        return null;
      }
    }).flatMap(job -> job.getRequirements().stream())
        .map(requirement -> {

          if(requirement.contains("Java")){
            return ANSI_RED+requirement;
          }
          return ANSI_RESET+requirement;
        })
        .collect(Collectors.joining("\n")));

  }

  public List<String> parse(Document doc) {
    Elements select = doc.select("div[id^=job]");
    return select.stream().filter(element -> !element.select("a[href^=/de/de/karriere").isEmpty()).map(element -> {
      Elements urls = element.select("a[href^=/de/de/karriere");
      return urls.first().attr("abs:href");
    }).collect(Collectors.toList());
  }

}
