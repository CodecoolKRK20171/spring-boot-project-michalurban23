package com.codecool.michalurban.flightconnector.airport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Airport")
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

}
