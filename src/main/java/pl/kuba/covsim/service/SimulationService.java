package pl.kuba.covsim.service;

import pl.kuba.covsim.model.Simulation;
import pl.kuba.covsim.model.SimulationDetails;

import java.util.List;

public interface SimulationService {

    List<Simulation> findAll();
    Simulation findById(Long id);
    void deleteById(Long id);
    Simulation create(Simulation simulation);
    Simulation edit(Simulation newSimulation, Long id);
    List<SimulationDetails> calculate(Long id);

}
