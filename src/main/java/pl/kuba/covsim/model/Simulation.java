package pl.kuba.covsim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "simulation")
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String N;
    private double P;
    private double I;
    private double R;
    private double M;
    private Integer Ti;
    private Integer Tm;
    private Integer Ts;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "simulation_id")
    private List<SimulationDetails> simulationDetailsList;

}
