package com.codecool.michalurban.flightconnector.airport;

import com.codecool.michalurban.flightconnector.airline.Airline;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
// @JsonIdentityInfo(
//         generator = ObjectIdGenerators.PropertyGenerator.class,
//         property = "id")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String country;

    @NotNull
    @Column(unique = true)
    private String shortName;

    private String longName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "airport_airline",
            joinColumns = { @JoinColumn(name = "airport_id") },
            inverseJoinColumns = { @JoinColumn(name = "airline_id") }
    )
    // @JsonManagedReference
    private Set<Airline> airlines;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getShortName() {

        return shortName;
    }

    public void setShortName(String shortName) {

        this.shortName = shortName;
    }

    public String getLongName() {

        return longName;
    }

    public void setLongName(String longName) {

        this.longName = longName;
    }

    public Set<Airline> getAirlines() {

        return airlines;
    }

    public void setAirlines(Set<Airline> airlines) {

        this.airlines = airlines;
    }
}
