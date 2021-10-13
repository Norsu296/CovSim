package pl.kuba.covsim.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kuba.covsim.model.Simulation;
import pl.kuba.covsim.model.SimulationDetails;
import pl.kuba.covsim.repository.SimulationDetailsRepository;
import pl.kuba.covsim.repository.SimulationRepository;
import pl.kuba.covsim.service.SimulationDetailsService;
import pl.kuba.covsim.service.SimulationService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulationServiceImpl implements SimulationService {

    private final SimulationRepository simulationRepository;
    private final SimulationDetailsServiceImpl simulationDetailsService;

    public List<Simulation> findAll() {
        return simulationRepository.findAll();
    }

    public Simulation findById(Long id) {
        return simulationRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        simulationRepository.deleteById(id);
    }

    public Simulation create(Simulation simulation) {
        return simulationRepository.save(simulation);
    }

    public Simulation edit(Simulation newSimulation, Long id) {
        return simulationRepository.findById(id)
                .map(simulation -> {
                    simulation.setN(newSimulation.getN());
                    simulation.setP(newSimulation.getP());
                    simulation.setR(newSimulation.getR());
                    simulation.setM(newSimulation.getM());
                    simulation.setTi(newSimulation.getTi());
                    simulation.setTm(newSimulation.getTm());
                    simulation.setTs(newSimulation.getTs());
                    return simulationRepository.save(simulation);
                })
                .orElseGet(() -> {
                    newSimulation.setId(id);
                    return simulationRepository.save(newSimulation);
                });
    }

    public List<SimulationDetails> calculate(Long id) {

        System.out.println(simulationRepository.findById(id));

        double I = simulationRepository.findById(id).get().getI();
        double P = simulationRepository.findById(id).get().getP();
        double R = simulationRepository.findById(id).get().getR();
        double M = simulationRepository.findById(id).get().getR();
        Integer Tm = simulationRepository.findById(id).get().getTm();
        Integer Ts = simulationRepository.findById(id).get().getTs();
        Integer Ti = simulationRepository.findById(id).get().getTi();

        List<SimulationDetails> simulationDetailsList = new ArrayList<>();

        double infectedDay = 0;
        double diedDay = 0;
        double recoverDay = 0;
        for(Long i = 0L; i <= Ts; i++){
            SimulationDetails simulationDetails = new SimulationDetails();
            infectedDay = I;

            if(i % Tm == 0 && i >= Tm && simulationDetailsService.findById(i - Tm +1).getPi() > 0){
                 diedDay = simulationDetailsService.findById(i+1).getPi() * M / 100;
                 simulationDetails.setPm(diedDay);
                 infectedDay = infectedDay - diedDay;
            }
            if(i % Ti == 0 && i >= Ti && simulationDetailsService.findById(i - Ti +1).getPi() > 0){
                recoverDay = simulationDetailsService.findById(i+1).getPi();
                simulationDetails.setPr(recoverDay);
            }
            infectedDay = infectedDay + infectedDay * R / 100;
            infectedDay = (infectedDay >= 0 ? infectedDay : 0);
            simulationDetails.setPi(infectedDay);
            simulationDetails.setPv(P - infectedDay - diedDay - recoverDay);
            simulationDetailsService.create(simulationDetails);
            simulationDetailsList.add(simulationDetails);
        }
        simulationRepository.findById(id).get().setSimulationDetailsList(simulationDetailsList);
        return simulationDetailsList;
    }

}
