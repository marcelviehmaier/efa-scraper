package de.hspf.efabwscraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {
    private final String url = "https://www.efa-bw.de/nvbw/XSLT_TRIP_REQUEST2?language=de&itdLPxx_calcMethod=BW&itdLPxx_frames=&sessionID=0&requestID=0&ptOptionsActive=1&useProxFootSearch=1&lineRestriction=400";

    public List<Connection> loadConnetions(String origin, String departure) {
        List<Connection> connections = new ArrayList<>();

        try (final WebClient webClient = new WebClient()) {
            // Variables
            final HtmlPage startPage;
            final HtmlForm form;
            final HtmlElement submitButton;
            final HtmlTextInput inputFieldOrigin;
            final HtmlTextInput inputFieldDestination;

            // Load elements from website
            startPage = webClient.getPage(url);
            form = startPage.getForms().get(0);
            submitButton = (HtmlElement) form.getByXPath("//button[@class='btn_send']").get(0);
            inputFieldOrigin = form.getInputByName("name_origin");
            inputFieldDestination = form.getInputByName("name_destination");

            // Change values of elements
            inputFieldOrigin.type(origin);
            inputFieldDestination.type(departure);

            final HtmlPage resultPage = submitButton.click();
            final List<HtmlTable> tables = resultPage.getByXPath("//table[@class='table-detailtable']");

            // Loop through all connections
            for(int i = 0; i < tables.size(); i++){
                HtmlTableCell connectionTitle;
                Connection connection = new Connection();
                HtmlTableCell connectionDate;
                List<HtmlElement> routes;

                connectionTitle = (HtmlTableCell) tables.get(i).getByXPath(".//th[@class='table-detailtable-th-fahrt']").get(0);
                connectionDate = (HtmlTableCell) tables.get(i).getByXPath(".//th[@class='table-detailtable-th-datum']").get(0);

                connection.setConnectionDate(connectionDate.asText());
                connection.setTitle(connectionTitle.asText());
                routes = tables.get(i).getByXPath(".//div[@class='table-detailtable-detail']");
                for(int y = 0; y < routes.size(); y++){
                    connection = this.loadRoute(routes.get(y), connection);
                }
                connections.add(connection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connections;
    }

    private Connection loadRoute(HtmlElement route, Connection connection){
        // Variables
        RoutePart routePart = new RoutePart();
        HtmlElement originDatePlanned;
        HtmlElement originDateActual;
        HtmlElement destinationDateActual;
        HtmlElement transportationName;
        HtmlElement destinationDatePlanned;
        HtmlElement originPlace;
        HtmlElement destinationPlace;
        HtmlElement transportationType;

        // Load data from website
        originDatePlanned = (HtmlElement) route.getByXPath(".//div[@class='table-detailtable-detail-times']").get(0);
        destinationDatePlanned = (HtmlElement) route.getByXPath(".//div[@class='table-detailtable-detail-times']").get(1);
        originPlace = (HtmlElement) route.getByXPath(".//span[@class='table-detailtable-detail-place']").get(0);
        destinationPlace = (HtmlElement) route.getByXPath(".//span[@class='table-detailtable-detail-place']").get(1);
        transportationType = (HtmlElement) route.getByXPath(".//img").get(2);
        try {
            originDateActual = (HtmlElement) route.getByXPath(".//div[@class='minorDelay todayTime']").get(0);
            routePart.setOriginDateActual(originDateActual.getTextContent());
        }catch (Exception e){}
        try {
            destinationDateActual = (HtmlElement) route.getByXPath(".//div[@class='minorDelay todayTime']").get(1);
            routePart.setDestinationDateActual(destinationDateActual.getTextContent());
        }catch (Exception e){}

        try{
            transportationName = (HtmlElement) route.getByXPath(".//a").get(4);
            routePart.setTransportationName(transportationName.getTextContent());
        }catch (Exception e){}

        // Fill objects
        routePart.setOriginDatePlanned(originDatePlanned.getTextContent());
        routePart.setDestinationDatePlanned(destinationDatePlanned.getTextContent());
        routePart.setOriginPlace(originPlace.getTextContent());
        routePart.setDestinationPlace(destinationPlace.getTextContent());
        routePart.setTransportationType(transportationType.getAttribute("alt"));
        connection.getRouteParts().add(routePart);

        return connection;
    }
}
