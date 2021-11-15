package pt.rafael.pwc.calculateroute.controller;

import pt.rafael.pwc.calculateroute.model.CalculateRouteResult;
import pt.rafael.pwc.calculateroute.service.CalculateRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateRouteController {

    @Autowired
    private CalculateRouteService calculateRouteService;

    @GetMapping("/routing/{origin}/{destination}")
    public CalculateRouteResult route(
            @PathVariable String origin,
            @PathVariable String destination
    ) {
        CalculateRouteResult result = new CalculateRouteResult();
        result.setRoute(calculateRouteService.route(origin, destination));
        return result;
    }
}
