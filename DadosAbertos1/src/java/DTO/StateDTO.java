/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author williams
 */
public class StateDTO implements Serializable {

    private int id;
    private int numberOfUBSCompleted;
    private int numberOfUBSProvided;
    private int population;
    private String uf;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfUBSCompleted() {
        return numberOfUBSCompleted;
    }

    public void setNumberOfUBSCompleted(int numberOfUBSCompleted) {
        this.numberOfUBSCompleted = numberOfUBSCompleted;
    }

    public int getNumberOfUBSProvided() {
        return numberOfUBSProvided;
    }

    public void setNumberOfUBSProvided(int numberOfUBSProvided) {
        this.numberOfUBSProvided = numberOfUBSProvided;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
