package pt.rafael.pwc.calculateroute.service;

import pt.rafael.pwc.calculateroute.model.Country;
import java.util.Map;

public interface CountryService {

    Map<String, Country> getAllCountries();
}
