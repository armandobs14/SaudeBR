/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import joint_codegen_nsbf6996f0.State;
/*
 *
 * @author Judson
 */

public class CityDTO implements Serializable {

    private String name;
    private String uf;
    private String codigo;
    private int id;
    private State state;
    private int population;
    private int numberOfUBSCompleted;
    private int numberOfUBSProvided;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;

    }

}
