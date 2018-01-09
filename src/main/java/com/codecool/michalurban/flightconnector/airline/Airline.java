package com.codecool.michalurban.flightconnector.airline;

import com.codecool.michalurban.flightconnector.airport.Airport;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
// @JsonIdentityInfo(
//         generator = ObjectIdGenerators.PropertyGenerator.class,
//         property = "id")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String countryOfOrigin;

    @ManyToMany(mappedBy = "airlines")
    // @JsonBackReference
    @JsonIgnore
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