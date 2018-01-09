package com.codecool.michalurban.flightconnector.airline;

import com.codecool.michalurban.flightconnector.airport.Airport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Airline")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String countryOfOrigin;

    @ManyToMany(mappedBy = "airlines")
    private Set<Airport> airports;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getCountryOfOrigin() {

        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {

        this.countryOfOrigin = countryOfOrigin;
    }

    public Set<Airport> getAirports() {

        return airports;
    }

    public void setAirports(Set<Airport> airports) {

        this.airports = airports;
    }
}