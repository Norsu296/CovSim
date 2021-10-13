package pl.kuba.covsim.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kuba.covsim.model.Simulation;
import pl.kuba.covsim.model.SimulationDetails;
import pl.kuba.covsim.repository.SimulationDetailsRepository;
import pl.kuba.covsim.service.implementation.SimulationServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/simulation")
@RequiredArgsConstructor
public class SimulationController {

    private final SimulationServiceImpl simulationServiceImpl;
    private final SimulationDetailsRepository simulationDetailsRepository;

    @GetMapping
    public List<Simulation> findAll(){
        return simulationServiceImpl.findAll();
    }
    @GetMapping("/{id}")
    public Simulation findById(@PathVariable Long id){
        return simulationServiceImpl.findById(id);
    }
    @PostMapping
    Simulation create(@RequestBody Simulation simulation){
        return simulationServiceImpl.create(simulation);
    }
    @PutMapping("/{id}")
    Simulation edit(@RequestBody Simulation newSimulation, @PathVariable Long id){
        return simulationServiceImpl.edit(newSimulation,id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        simulationServiceImpl.deleteById(id);
    }

    @GetMapping("/calculate/{id}")
    public List<SimulationDetails> calculate(@PathVariable Long id){
        return simulationServiceImpl.calculate(id);
    }

    @GetMapping("/details/{id}")
    public SimulationDetails details(@PathVariable Long id){
        return simulationDetailsRepository.findById(id).get();
    }
}
