package de.hspf.efabwscraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;

@Service
public class ScraperService {

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        System.out.println("Scrape website...");

        try (final WebClient webClient = new WebClient()) {

            final HtmlPage page1 = webClient.getPage("http://www.efa-bw.de/nvbw/XSLT_TRIP_REQUEST2");

            final HtmlForm form = page1.getForms().get(0);

            final HtmlElement button = form.getElementsByTagName("button").get(7);
            final HtmlTextInput textField = form.getInputByName("name_origin");
            final HtmlTextInput textField2 = form.getInputByName("name_destination");

            textField.type("Pforzheim Sonnenhof");
            textField2.type("Stuttgart Albschule");

            final HtmlPage page2 = button.click();
            final HtmlTable table = (HtmlTable) page2.getElementsByTagName("table").get(2);
            for (final HtmlTableRow row : table.getRows()) {
                System.out.println("Found row");
                for (final HtmlTableCell cell : row.getCells()) {
                    System.out.println("   Found cell: " + cell.asText());
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
