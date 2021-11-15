package pt.rafael.pwc.calculateroute.service;

import static org.springframework.http.HttpMethod.GET;

import pt.rafael.pwc.calculateroute.model.Country;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryServiceImpl implements CountryService {

    @Value("${data.link.url}")
    private String dataLinkUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Map<String, Country> getAllCountries() {
        ResponseEntity<List<Country>> response =
                restTemplate.exchange(dataLinkUrl, GET, null, new ParameterizedTypeReference<List<Country>>() {
                });

        List<Country> countries = response.getBody();

        return countries.stream()
                .collect(Collectors.toMap(Country::getName, c -> c));
    }
}
