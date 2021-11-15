package pt.rafael.pwc.calculateroute.service;

import pt.rafael.pwc.calculateroute.model.Country;
import pt.rafael.pwc.calculateroute.model.CalculateRouteNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateRouteServiceImpl implements CalculateRouteService {

    private Map<String, Country> countries;

    @Autowired
    private CountryService countryService;

    @Override
    public List<String> route(String from, String to) {
        Map<String, Country> countries = countryService.getAllCountries();

        Country fromCountry = countries.get(from);
        if (fromCountry == null) {
            throw new CalculateRouteNotFoundException();
        }
        Country toCountry = countries.get(to);
        if (toCountry == null) {
            throw new CalculateRouteNotFoundException();
        }
        if (from.equals(to)) {
            return Collections.singletonList(fromCountry.getName());
        }
        CalculateRouteFinder routeFinder = new CalculateRouteFinder(fromCountry, toCountry, countries);
        return routeFinder.findRoute().stream()
                .map(c -> c.getName())
                .collect(Collectors.toList());
    }
}
