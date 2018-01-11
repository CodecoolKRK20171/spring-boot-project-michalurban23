package com.codecool.michalurban.flightconnector.airport;

import com.codecool.michalurban.flightconnector.airline.Airline;
import com.codecool.michalurban.flightconnector.airline.AirlineDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@JsonSerialize(using = AirportSerializer.class)
@JsonDeserialize(using = AirportDeserializer.class)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"shortName", "archived"})})
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String country;

    @NotNull
    private String shortName;

    @NotNull
    private Boolean archived = false;

    private String longName;

    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
            name = "airport_airline",
            joinColumns = { @JoinColumn(name = "airport_id") },
            inverseJoinColumns = { @JoinColumn(name = "airline_id") }
    )
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

    public Boolean getArchived() {

        return archived;
    }

    public void setArchived(Boolean archived) {

        this.archived = archived;
    }
}
