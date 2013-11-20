/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import joint.codegen.nsbf6996f0.State;
/*
 *
 * @author Judson
 */

public class CityDTO implements Serializable {

    private String name;
    private Integer id;
    private State state;
    private int population;
    private int numberOfUBSCompleted;
    private int numberOfUBSProvided;
    private Double val_2011_2014;
    private Double val_pos_2014;
    private Double investment;
    private Double investment_total;

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

    public Double getInvestment() {
        return investment;
    }

    public void setInvestment(Double investment) {
        this.investment = investment;
    }

    public Double getInvestment_total() {
        return investment_total;
    }

    public void setInvestment_total(Double investment_total) {
        this.investment_total = investment_total;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StateDTO getState() {
        StateDTO stateDTO = new StateDTO();
        stateDTO.setId(state.getId());
        stateDTO.setName(state.getFoafName());
        stateDTO.setUf(state.getUf());
        stateDTO.setNumberOfUBSCompleted(state.getNumberOfUBSCompleted());
        stateDTO.setNumberOfUBSProvided(state.getNumberOfUBSProvided());
        return stateDTO;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;

    }

}
