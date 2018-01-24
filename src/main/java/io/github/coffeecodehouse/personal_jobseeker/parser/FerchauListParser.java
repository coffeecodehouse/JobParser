package io.github.coffeecodehouse.personal_jobseeker.parser;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class FerchauListParser {

  public static void main(String[] args) throws IOException {
    new FerchauListParser().parse(Jsoup.connect("https://www.ferchau.com/de/de/karriere/jobs-bewerbung/jobangebote/search/young-professional/deutschland/java/").get());
  }

  public List<String> parse(Document doc) {
    Elements select = doc.select("div[id^=job]");
    select.forEach(element ->  {
      Elements urls = element.select("a[href^=/de/de/karriere");
      if(!urls.isEmpty()) {
        String text = urls.first().text();
        System.out.println(text);
      }
    });
    return null;
  }

}
