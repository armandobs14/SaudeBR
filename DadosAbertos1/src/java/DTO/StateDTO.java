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
    private Double val_2011_2014;
    private Double val_pos_2014;
    private Double investiment;
    private Double investiment_total;

    public Double getInvestiment_total() {
        return investiment_total;
    }

    public void setInvestiment_total(Double investiment_total) {
        this.investiment_total = investiment_total;
    }

    public Double getVal_2011_2014() {
        return val_2011_2014;
    }

    public void setVal_2011_2014(Double val_2011_2014) {
        this.val_2011_2014 = val_2011_2014;
    }

    public Double getVal_pos_2014() {
        return val_pos_2014;
    }

    public void setVal_pos_2014(Double val_pos_2014) {
        this.val_pos_2014 = val_pos_2014;
    }

    public Double getInvestiment() {
        return investiment;
    }

    public void setInvestiment(Double investiment) {
        this.investiment = investiment;
    }

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
