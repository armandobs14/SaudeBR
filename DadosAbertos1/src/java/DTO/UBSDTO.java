/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author armando
 */
public class UBSDTO implements Serializable {

    private int id;
    private String name;
    private String cityName;
    private String stateName;
    private String street;
    private String district;
    private String phone;
    private String type;
    private String status;
    private String department;
    private String executor;
    private double investiment;
    private double val_2011_2014;
    private double val_pos_2014;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public Double getInvestiment() {
        return investiment;
    }

    public void setInvestiment(double investiment_total) {
        this.investiment = investiment_total;
    }

    public Double getVal_2011_2014() {
        return val_2011_2014;
    }

    public void setVal_2011_2014(double val_2011_2014) {
        this.val_2011_2014 = val_2011_2014;
    }

    public Double getVal_pos_2014() {
        return val_pos_2014;
    }

    public void setVal_pos_2014(double val_pos_2014) {
        this.val_pos_2014 = val_pos_2014;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {

        return cityName;
    }

    public void setCity(String city) {
        /*cityDTO = new CityDTO();
         cityDTO.setId(city.getId());
         cityDTO.setName(city.getFoafName());
         cityDTO.setPopulation(city.getPopulation());
         cityDTO.setState(city.getHasState());
         cityDTO.setVal_2011_2014(city.getVal_2011_2014());
         cityDTO.setVal_pos_2014(city.getVal_pos_2014());
         cityDTO.setInvestiment(city.getInvestiment());
         cityDTO.setInvestiment_total(city.getInvestiment_total());
         if (city.getNumberOfUBSCompleted() == null) {
         cityDTO.setNumberOfUBSCompleted(0);
         } else {
         cityDTO.setNumberOfUBSCompleted(city.getNumberOfUBSCompleted());
         }
         if (city.getNumberOfUBSProvided() == null) {
         cityDTO.setNumberOfUBSProvided(0);
         } else {
         cityDTO.setNumberOfUBSProvided(city.getNumberOfUBSProvided());
         }*/
        this.cityName = city;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String foafStatus) {
        this.status = foafStatus;
    }
}
