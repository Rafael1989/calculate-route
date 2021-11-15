package pt.rafael.pwc.calculateroute.service;

import java.util.List;

public interface CalculateRouteService {

    List<String> route(String origin, String destination);
}
