package pl.kuba.covsim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "simulation_details")
public class SimulationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double Pi;
    private double Pv;
    private double Pm;
    private double Pr;

}
