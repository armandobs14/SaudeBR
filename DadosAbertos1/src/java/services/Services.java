/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DTO.CityDTO;
import DTO.StateDTO;
import DTO.UBSDTO;
import control.Control;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author tbastos
 */
@WebService(serviceName = "Services")
public class Services {

    @WebMethod(operationName = "retrieveAllState")
    public ArrayList<StateDTO> retrieveAllState() {
        Control objeto = new Control();
        return objeto.retrieveAllState();
    }
    @WebMethod(operationName = "retrieveState")
    public StateDTO retrieveState(@WebParam(name = "stateName") String stateName) {
        Control objeto = new Control();
        return objeto.retrieveState(stateName);
    }
//OK
    @WebMethod(operationName = "retrieveCity")
    public CityDTO retrieveCity(@WebParam(name = "stateName") String stateName, @WebParam(name = "cityName") String cityName) {
        Control objeto = new Control();
        return objeto.retrieveCity(stateName, cityName);
    }
//OK
    @WebMethod(operationName = "retrieveAllCity_ByState")
    public ArrayList<CityDTO> retrieveAllCity_ByState(@WebParam(name = "stateName") String stateName) {
        Control objeto = new Control();
        return objeto.retrieveAllCity_ByState(stateName);
    }
//OK
    @WebMethod(operationName = "retrieveAllCity")
    public ArrayList<CityDTO> retrieveAllCity() {

        Control objeto = new Control();
        return objeto.retrieveAllCity();
    }
//OK
    @WebMethod(operationName = "retrieveAllUBS")
    public ArrayList<UBSDTO> retrieveAllUBS() {
        Control objeto = new Control();
        return objeto.retrieveAllUBS();
    }
//OK
    @WebMethod(operationName = "retrieveAllUBS_ByState")
    public ArrayList<UBSDTO> retrieveAllUBS_ByState(@WebParam(name = "stateName") String stateName) {
        Control objeto = new Control();
        return objeto.retrieveAllUBS_ByState(stateName);
    }
//OK
    @WebMethod(operationName = "retrieveAllUBS_ByCity")
    public ArrayList<UBSDTO> retrieveAllUBS_ByCity(@WebParam(name = "stateName") String stateName, @WebParam(name = "cityName") String cityName) {
        Control objeto = new Control();
        return objeto.retrieveAllUBS_ByCity(stateName, cityName);
    }

    @WebMethod(operationName = "currentQuantityUBS_ByState")
    public Integer currentQuantityUBS_ByState(@WebParam(name = "stateName") String stateName) {
        Control objeto = new Control();
        return objeto.currentQuantityUBS_ByState(stateName);
    }

    @WebMethod(operationName = "providedQuantityUBS_ByState")
    public ArrayList<Integer> providedQuantityUBS_ByState(@WebParam(name = "stateName") String stateName) {
        Control objeto = new Control();
        return objeto.providedQuantityUBS_ByState(stateName);
    }

    @WebMethod(operationName = "currentQuantityUBS_ByCity")
    public Integer currentQuantityUBS_ByCity(@WebParam(name = "stateName") String stateName, @WebParam(name = "cityName") String cityName) {
        Control objeto = new Control();
        return objeto.currentQuantityUBS_ByCity(stateName, cityName);
    }

    @WebMethod(operationName = "providedQuantityUBS_ByCity")
    public ArrayList<Integer> providedQuantityUBS_ByCity(@WebParam(name = "stateName") String stateName, @WebParam(name = "cityName") String cityName) {
        Control objeto = new Control();
        return objeto.providedQuantityUBS_ByCity(stateName, cityName);
    }

    @WebMethod(operationName = "investimentUBS_ByState")
    public Double investimentUBS_ByState(@WebParam(name = "stateName") String stateName) {
        Control objeto = new Control();
        return objeto.investmentUBS_ByState(stateName);
    }

    @WebMethod(operationName = "investimentUBS_ByCity")
    public Double investimentUBS_ByCity(@WebParam(name = "stateName") String stateName, @WebParam(name = "cityName") String cityName) {
        Control objeto = new Control();
        return objeto.investmentUBS_ByCity(stateName, cityName);
    }

}
