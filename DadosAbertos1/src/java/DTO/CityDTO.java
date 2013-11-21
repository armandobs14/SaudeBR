/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
/*
 *
 * @author Judson
 */

public class CityDTO implements Serializable {

    private String name;
    private Integer id;
    private String stateName;
    private int population;
    private int numberOfUBSCompleted;
    private int numberOfUBSProvided;
    private Double val_2011_2014;
    private Double val_pos_2014;
    private Double investiment;
    private Double investiment_total;

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

    public void setInvestiment(Double investment) {
        this.investiment = investment;
    }

    public Double getInvestiment_total() {
        return investiment_total;
    }

    public void setInvestiment_total(Double investment_total) {
        this.investiment_total = investment_total;
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

    public String getState() {       
        
        return stateName;
    }

    public void setState(String stateName) {
        /*stateDTO = new StateDTO();
        stateDTO.setId(state.getId());
        stateDTO.setName(state.getFoafName());
        stateDTO.setUf(state.getUf());
        stateDTO.setNumberOfUBSCompleted(state.getNumberOfUBSCompleted());
        stateDTO.setNumberOfUBSProvided(state.getNumberOfUBSProvided());
        stateDTO.setInvestiment(state.getInvestiment());
        stateDTO.setVal_2011_2014(state.getVal_2011_2014());
        stateDTO.setVal_pos_2014(state.getVal_pos_2014());
        stateDTO.setPopulation(state.getPopulation());
        stateDTO.setInvestiment_total(state.getInvestiment_total());*/
        this.stateName = stateName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;

    }

}
