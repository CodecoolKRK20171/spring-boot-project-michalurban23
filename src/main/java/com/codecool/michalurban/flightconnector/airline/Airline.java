package com.codecool.michalurban.flightconnector.airline;

import com.codecool.michalurban.flightconnector.airport.Airport;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@JsonSerialize(using = AirlineSerializer.class)
@JsonDeserialize(using = AirlineDeserializer.class)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "archived"})})
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    private String countryOfOrigin;

    @NotNull
    private Boolean archived = false;

    @ManyToMany(mappedBy = "airlines", cascade = {CascadeType.DETACH})
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

    public Boolean getArchived() {

        return archived;
    }

    public void setArchived(Boolean archived) {

        this.archived = archived;
    }
}