package pl.kuba.covsim.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kuba.covsim.model.SimulationDetails;
import pl.kuba.covsim.repository.SimulationDetailsRepository;
import pl.kuba.covsim.service.SimulationDetailsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulationDetailsServiceImpl implements SimulationDetailsService {
    
    private final SimulationDetailsRepository simulationDetailsRepository;

    public List<SimulationDetails> findAll(){
        return simulationDetailsRepository.findAll();
    }
    public SimulationDetails findById(Long id){
        return simulationDetailsRepository.findById(id).get();
    }
    public void create(SimulationDetails simulationDetails){
        simulationDetailsRepository.save(simulationDetails);
    }
    
}
