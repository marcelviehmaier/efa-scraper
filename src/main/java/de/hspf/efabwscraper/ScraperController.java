package de.hspf.efabwscraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScraperController {
    @Autowired
    ScraperService service;

    @PostMapping(value = "/connections/from/{origin}/to/{departure}/at/{timestamp}/transportationTypes/{transportationTypes}")
    @ResponseStatus(HttpStatus.OK)
    public List<Connection> postConnections(@PathVariable String origin, @PathVariable String departure, @PathVariable long timestamp, @PathVariable String transportationTypes) {
        return service.loadConnetions(origin, departure, timestamp, transportationTypes);
    }
}
