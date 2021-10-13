package pl.kuba.covsim.service;

import pl.kuba.covsim.model.SimulationDetails;

import java.util.List;

public interface SimulationDetailsService {

    List<SimulationDetails> findAll();
    SimulationDetails findById(Long id);
    void create (SimulationDetails simulationDetails);
}
