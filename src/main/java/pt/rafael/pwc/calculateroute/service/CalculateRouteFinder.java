package pt.rafael.pwc.calculateroute.service;

import org.springframework.util.CollectionUtils;
import pt.rafael.pwc.calculateroute.model.Country;
import pt.rafael.pwc.calculateroute.model.CalculateRouteNotFoundException;

import java.util.*;

public class CalculateRouteFinder {

    private final Country toCountry;
    private final Country fromCountry;
    private final Map<String, Country> allCountries;
    private Set<String> visitedCountryNames = new HashSet<>();
    private List<LinkedList<Country>> routes = new LinkedList<>();


    public CalculateRouteFinder(Country fromCountry, Country toCountry, Map<String, Country> allCountries) {
        this.fromCountry = fromCountry;
        this.toCountry = toCountry;
        this.allCountries = allCountries;
    }

    public List<Country> findRoute() {
        LinkedList<Country> firstRoute = new LinkedList<>();
        firstRoute.add(fromCountry);
        this.routes.add(firstRoute);
        this.visitedCountryNames.add(fromCountry.getName());

        return expandRoutes();
    }

    private List<Country> expandRoutes() {
        List<LinkedList<Country>> newRoutes = new ArrayList<>();

        for (LinkedList<Country> route: routes) {
            Country lastCountry = route.getLast();
            if (CollectionUtils.isEmpty(lastCountry.getBorders())) {
                continue;
            }
            if (lastCountry.getBorders().contains(toCountry.getName())) {
                route.add(toCountry);
                return route;
            }
            for (String country: lastCountry.getBorders()) {
                if (visitedCountryNames.contains(country)) {
                    continue;
                } else {
                    visitedCountryNames.add(country);
                }
                LinkedList<Country> newRoute = new LinkedList<>(route);
                newRoute.add(allCountries.get(country));
                newRoutes.add(newRoute);
            }
        }
        if (visitedCountryNames.size() == allCountries.size() || newRoutes.size() == 0) {
            throw new CalculateRouteNotFoundException();
        }
        routes = newRoutes;
        return expandRoutes();
    }
}
