/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import joint_codegen_nsbf6996f0.City;
import joint_codegen_nsbf6996f0.State;

/**
 *
 * @author armando
 */
public class UBSDTO implements Serializable {

    private int id;
    private String name;
    private City city;
    private State state;
    private String street;
    private String district;
    private String phone;
    private String type;
    private String status;
    private String department;
    private String executor;
    private double investiment_total;
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

    public Double getInvestiment_total() {
        return investiment_total;
    }

    public void setInvestiment_total(double investiment_total) {
        this.investiment_total = investiment_total;
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

    public CityDTO getCity() {
        CityDTO cdto = new CityDTO();
        cdto.setId(city.getId());
        cdto.setName(city.getFoafName());
        cdto.setPopulation(city.getPopulation());
        cdto.setState(city.getHasState());
        cdto.setUf(city.getHasState().getUf());
        return cdto;
    }

    public void setCity(City city) {
        this.city = city;
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
