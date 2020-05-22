package de.hspf.efabwscraper;

import java.util.ArrayList;
import java.util.List;

public class Connection {
    private String title;
    private String connectionDate;
    private List<RoutePart> routeParts = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(String connectionDate) {
        this.connectionDate = connectionDate;
    }

    public List<RoutePart> getRouteParts() {
        return routeParts;
    }

    public void setRouteParts(List<RoutePart> routeParts) {
        this.routeParts = routeParts;
    }
}
