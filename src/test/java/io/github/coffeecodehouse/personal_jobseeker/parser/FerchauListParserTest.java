package io.github.coffeecodehouse.personal_jobseeker.parser;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import java.util.Collection;
import java.util.List;
import org.hamcrest.Matcher;
import org.jsoup.Jsoup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FerchauListParserTest {

  @Autowired
  FerchauListParser parser;

  @Before
  public void setUp() throws Exception {
  }


  @Test
  public void ferchauListParser_executeParseMethod_listIsFilled() throws Exception {
    List<String> parse = parser.parse(Jsoup.connect(
        "https://www.ferchau.com/de/de/karriere/jobs-bewerbung/jobangebote/search/young-professional/deutschland/java/")
        .get());
    assertThat(parse,is(not(empty())));

  }

  @After
  public void tearDown() throws Exception {
  }

}