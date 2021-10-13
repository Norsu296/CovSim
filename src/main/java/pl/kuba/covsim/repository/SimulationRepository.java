package pl.kuba.covsim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.covsim.model.Simulation;

public interface SimulationRepository extends JpaRepository<Simulation, Long> {
}
