/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadosabertos.servicos;

import DTO.CityDTO;
import DTO.StateDTO;
import DTO.UBSDTO;
import dadosabertos.kernel.ClassePrincipalProvisoria;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author tbastos
 */
@WebService(serviceName = "DadosAbertosServicos")
public class DadosAbertosServicos {

    @WebMethod(operationName = "retrieveAllState")
    public ArrayList<StateDTO> retrieveAllState() {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveAllState();
    }

    @WebMethod(operationName = "retrieveState")
    public StateDTO retrieveState(@WebParam(name = "nameState") String nameState) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveState(nameState);
    }

    @WebMethod(operationName = "retrieveCity")
    public CityDTO retrieveCity(@WebParam(name = "nameState") String nameState, @WebParam(name = "nameCity") String nameCity) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveCity(nameState, nameCity);
    }

    @WebMethod(operationName = "retrieveAllCity_ByState")
    public ArrayList<CityDTO> retrieveAllCity_ByState(@WebParam(name = "nameState") String nameState) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveAllCity_ByState(nameState);
    }

    @WebMethod(operationName = "retrieveAllCity")
    public ArrayList<CityDTO> retrieveAllCity() {

        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveAllCity();
    }

    @WebMethod(operationName = "retrieveAllUBS")
    public ArrayList<UBSDTO> retrieveAllUBS() {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveAllUBS();
    }

    @WebMethod(operationName = "retrieveAllUBS_ByState")
    public ArrayList<UBSDTO> retrieveAllUBS_ByState(@WebParam(name = "nameState") String nameState) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveAllUBS_ByState(nameState);
    }

    @WebMethod(operationName = "retrieveAllUBS_ByCity")
    public ArrayList<UBSDTO> retrieveAllUBS_ByCity(@WebParam(name = "nameState") String uf, @WebParam(name = "nameCity") String nameCity) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveAllUBS_ByCity(uf, nameCity);
    }

    @WebMethod(operationName = "currentQuantityUBS_ByState")
    public Integer currentQuantityUBS_ByState(@WebParam(name = "nameState") String nameState) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.currentQuantityUBS_ByState(nameState);
    }

    @WebMethod(operationName = "providedQuantityUBS_ByState")
    public ArrayList<Integer> providedQuantityUBS_ByState(@WebParam(name = "nameState") String nameState) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.providedQuantityUBS_ByState(nameState);
    }

    @WebMethod(operationName = "currentQuantityUBS_ByCity")
    public Integer currentQuantityUBS_ByCity(@WebParam(name = "stateName") String stateName, @WebParam(name = "cityName") String cityName) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.currentQuantityUBS_ByCity(stateName, cityName);
    }

    @WebMethod(operationName = "providedQuantityUBS_ByCity")
    public ArrayList<Integer> providedQuantityUBS_ByCity(@WebParam(name = "stateName") String stateName, @WebParam(name = "cityName") String cityName) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.providedQuantityUBS_ByCity(stateName, cityName);
    }

    @WebMethod(operationName = "investmentUBS_ByState")
    public Double investmentUBS_ByState(@WebParam(name = "nameState") String nameState) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.investmentUBS_ByState(nameState);
    }

    @WebMethod(operationName = "investmentUBS_ByCity")
    public Double investmentUBS_ByCity(@WebParam(name = "stateName") String stateName, @WebParam(name = "cityName") String cityName) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.investmentUBS_ByCity(stateName, cityName);
    }

}
