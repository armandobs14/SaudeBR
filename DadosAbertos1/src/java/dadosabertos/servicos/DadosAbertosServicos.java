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
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import joint_codegen_foaf.Person;
import joint_codegen_nsbf6996f0.City;
import joint_codegen_nsbf6996f0.State;
import joint_codegen_nsbf6996f0.UBS;

/**
 *
 * @author tbastos
 */
@WebService(serviceName = "DadosAbertosServicos")
public class DadosAbertosServicos {

//    @WebMethod(operationName = "createState")
//    public void createStates(@WebParam(name = "id") int id, @WebParam(name = "name") String name, @WebParam(name = "population") int population) {
//        
//        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
//        objeto.createStates(id, name, population);
//    }
    /*    @WebMethod(operationName = "createPerson")
     public void createPerson(@WebParam(name = "name") String name, @WebParam(name = "phone") String phone){
     ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
     objeto.createPerson(name, phone);
     }*/
    @WebMethod(operationName = "createCity")
    public void createCity() {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        objeto.createCity();
    }

    @WebMethod(operationName = "createUBS")
    public void createUBS() {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        objeto.createUBSReady();
        objeto.createUBS_PAC();
        objeto.countPopulationAndUBS_ByState();
    }

    @WebMethod(operationName = "retrieveAllState")
    public ArrayList<StateDTO> retrieveAllState() {

        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveAllState();
    }

    @WebMethod(operationName = "retrieveCity")
    public CityDTO retrieveCity(@WebParam(name = "id") int id) {

        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveCity(id);
    }

    @WebMethod(operationName = "retrieveAllCity_ByState")
    public ArrayList<CityDTO> retrieveAllCity_ByState(@WebParam(name = "uf") String uf) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveAllCity_ByState(uf);
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
    public ArrayList<UBSDTO> retrieveAllUBS_ByState(@WebParam(name = "uf") String uf) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveAllUBS_ByState(uf);
    }

    @WebMethod(operationName = "retrieveAllUBS_ByCity")
    public ArrayList<UBSDTO> retrieveAllUBS_ByCity(@WebParam(name = "uf") String uf, @WebParam(name = "nameCity") String nameCity) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.retrieveAllUBS_ByCity(uf, nameCity);
    }

    @WebMethod(operationName = "currentAveragePopulationByUBS")
    public Double currentAveragePopulationByUBS(@WebParam(name = "uf") String uf) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.currentAveragePopulationByUBS(uf);
    }

    @WebMethod(operationName = "providedAveragePopulationByUBS")
    public Double providedAveragePopulationByUBS(@WebParam(name = "uf") String uf) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.providedAveragePopulationByUBS(uf);
    }

    @WebMethod(operationName = "currentAveragePopulationByUBS_City")
    public Double currentAveragePopulationByUBS_City(@WebParam(name = "uf") String uf, @WebParam(name = "cityName") String cityName) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.currentAveragePopulationByUBS_City(uf, cityName);
    }

    @WebMethod(operationName = "providedAveragePopulationByUBS_City")
    public Double providedAveragePopulationByUBS_City(@WebParam(name = "uf") String uf, @WebParam(name = "cityName") String cityName) {
        ClassePrincipalProvisoria objeto = new ClassePrincipalProvisoria();
        return objeto.providedAveragePopulationByUBS_City(uf, cityName);
    }
}
