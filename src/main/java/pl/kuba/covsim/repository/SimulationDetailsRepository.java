package pl.kuba.covsim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.covsim.model.SimulationDetails;

public interface SimulationDetailsRepository extends JpaRepository<SimulationDetails, Long> {
}
