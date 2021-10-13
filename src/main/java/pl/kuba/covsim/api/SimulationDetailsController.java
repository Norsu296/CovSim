package pl.kuba.covsim.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kuba.covsim.model.SimulationDetails;
import pl.kuba.covsim.service.implementation.SimulationDetailsServiceImpl;

@RestController
@RequestMapping("/simulation/details")
@RequiredArgsConstructor
public class SimulationDetailsController {

    private final SimulationDetailsServiceImpl simulationDetailsService;

    @GetMapping("/{id}")
    public SimulationDetails details(@PathVariable Long id){
        return simulationDetailsService.findById(id);
    }

}
