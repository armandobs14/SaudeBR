/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadosabertos.kernel;

import DTO.CityDTO;
import DTO.StateDTO;
import DTO.UBSDTO;
import com.csvreader.CsvReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joint_codegen_nsbf6996f0.City;
import joint_codegen_nsbf6996f0.State;
import joint_codegen_nsbf6996f0.UBS;

/**
 *
 * @author tbastos
 */
public class ClassePrincipalProvisoria {

    private final String repository_URL = "http://localhost:8080/openrdf-sesame/repositories/c2";
    private final String ontologyURIFoaf = "http://xmlns.com/foaf/0.1/";
    private final String ontologyURIOntoOpenData = "http://www.nees.com.br/ontologies/2013/opendata";
    String[] uf = {
        "ac", "al", "am", "ap",
        "ba", "ce", "df", "es",
        "go", "ma", "mg", "ms",
        "mt", "pa", "pb", "pe",
        "pi", "pr", "rj", "rn",
        "ro", "rr", "rs", "sc",
        "se", "sp", "to"};
    String[] estados = {"Acre", "Alagoas", "Amazonas", "Amapá",
        "Bahia", "Ceará", "Distrito Federal", "Espírito Santo",
        "Goiás", "Maranhão", "Minas Gerais", "Mato Grosso do Sul",
        "Mato Grosso", "Pará", "Paraiba", "Pernambuco",
        "Piauí", "Paraná", "Rio de Janeiro", "Rio Grande do Norte",
        "Rondônia", "Roraima", "Rio Grande do Sul", "Santa Catarina",
        "Sergipe", "São Paulo", "Tocantins"};

    public void createStates() {
        int id = 0;
        for (int x = 0; x < estados.length; x++) {
            Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
            State state = kaoState.create("STATE_" + uf[x].toUpperCase());
            List<State> aux = kaoState.retrieveAllInstances();
            if (aux == null) {
                id = 0;
            } else {
                id += 1;
            }
            state.setId(id);
            state.setFoafName(estados[x]);
            state.setUf(uf[x].toUpperCase());
            state.setNumberOfUBSCompleted(0);
            state.setNumberOfUBSProvided(0);
            state.setPopulation(0);
            kaoState.save();
        }
    }

    public StateDTO retrieveState(String uf) {
        Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
        State state = kaoState.retrieveInstance("STATE_" + uf.toUpperCase());
        StateDTO stateDTO = new StateDTO();
        stateDTO.setId(state.getId());
        stateDTO.setName(state.getFoafName());
        stateDTO.setUf(state.getUf());
        stateDTO.setNumberOfUBSCompleted(state.getNumberOfUBSCompleted());
        stateDTO.setNumberOfUBSProvided(state.getNumberOfUBSProvided());
        kaoState.save();
        return stateDTO;
    }

    public ArrayList<StateDTO> retrieveAllState() {
        Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
        List<State> states = kaoState.retrieveAllInstances();
        ArrayList<StateDTO> statesDTO = new ArrayList<StateDTO>();
        for (State s : states) {
            StateDTO sdto = new StateDTO();
            sdto.setId(s.getId());
            sdto.setName(s.getFoafName());
            sdto.setNumberOfUBSCompleted(s.getNumberOfUBSCompleted());
            sdto.setNumberOfUBSProvided(s.getNumberOfUBSProvided());
            sdto.setPopulation(s.getPopulation());
            sdto.setUf(s.getUf());
            statesDTO.add(sdto);
        }
        kaoState.save();
        return statesDTO;
    }

    public void createCity() {

        try {
            String file = "./basesDeDados/munic.CSV";
            CsvReader munic = new CsvReader(file);
            munic.readHeaders();
            while (munic.readRecord()) {
                String uf = munic.get("UF");
                String code = munic.get("CÓDIGO").substring(0, 6);
                String name = munic.get("NOME");
                int population = 0;
                String file2 = "./basesDeDados/censo.CSV";
                CsvReader censo = new CsvReader(file2);
                censo.readHeaders();

                while (censo.readRecord()) {
                    String uf_ = censo.get("UF");
                    String nameCity = censo.get("Municipio");
                    if (uf_.equals(uf) && nameCity.equals(name)) {
                        String pop = censo.get("Populacao2010");
                        population = Integer.parseInt(pop);
                        censo.close();
                        break;
                    }
                }
                censo.close();
                // perform program logic here
                System.out.println(uf + ":" + name + ":" + population);
                int statePopulation = 0;
                Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
                State state = kaoState.retrieveInstance("STATE_" + uf.toUpperCase());
                kaoState.save();
                Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
                City city = kaoCity.create("CITY_" + code);
                city.setId(Integer.parseInt(code));
                city.setFoafName(name);
                city.setHasState(state);
                city.setPopulation(population);
                kaoCity.save();
            }
            munic.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public CityDTO retrieveCity(int codeCity) {
        Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
        City city = kaoCity.retrieveInstance("CITY_" + codeCity);
        CityDTO cdto = new CityDTO();
        cdto.setId(codeCity);
        cdto.setName(city.getFoafName());
        cdto.setPopulation(city.getPopulation());
        cdto.setState(city.getHasState());
        cdto.setUf(city.getHasState().getUf());
        return cdto;
    }

    public ArrayList<CityDTO> retrieveAllCity_ByState(String uf) {
        StateDTO state = retrieveState(uf);
        Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
        List<City> allCity = kaoCity.retrieveAllInstances();
        ArrayList<CityDTO> allCityDTO = new ArrayList<CityDTO>();
        for (City c : allCity) {
            if (c.getHasState().equals(state)) {
                CityDTO cdto = new CityDTO();
                cdto.setId(c.getId());
                cdto.setName(c.getFoafName());
                cdto.setPopulation(c.getPopulation());
                cdto.setState(c.getHasState());
                cdto.setUf(c.getHasState().getUf());
                allCityDTO.add(cdto);
            }
        }
        return allCityDTO;
    }

    public CityDTO retrieveCity(String uf, String name) {
        Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
        State state = kaoState.retrieveInstance(uf.toUpperCase());
        kaoState.save();
        CityDTO cdto = new CityDTO();
        Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
        List<City> cityList = kaoCity.retrieveAllInstances();
        for (City c : cityList) {
            if (c.getHasState().equals(state) && c.getFoafName().equals(name.toUpperCase())) {
                cdto.setId(c.getId());
                cdto.setName(c.getFoafName());
                cdto.setPopulation(c.getPopulation());
                cdto.setState(c.getHasState());
                cdto.setUf(c.getHasState().getUf());
                return cdto;
            }
        }
        return cdto;
    }

    public ArrayList<CityDTO> retrieveAllCity() {
        Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
        List<City> cities = kaoCity.retrieveAllInstances();
        ArrayList<CityDTO> listDTO = new ArrayList<CityDTO>();
        for (City c : cities) {
            CityDTO cdto = new CityDTO();
            cdto.setId(c.getId());
            cdto.setName(c.getFoafName());
            cdto.setPopulation(c.getPopulation());
            cdto.setState(c.getHasState());
            cdto.setUf(c.getHasState().getUf());
            listDTO.add(cdto);
        }
        kaoCity.save();
        return listDTO;
    }

    public void createUBSReady() {
        int id = 0;
        try {
            String file = "./basesDeDados/ubs_base1.csv";
            CsvReader ubs_base = new CsvReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            ubs_base.readHeaders();

            while (ubs_base.readRecord()) {
                String code = ubs_base.get("cod_munic");
                String name = ubs_base.get("nom_estab");
                String street = ubs_base.get("dsc_endereco");
                String district = ubs_base.get("dsc_bairro");
                String phone = ubs_base.get("dsc_telefone");
                // perform program logic here
                System.out.println(code + ":" + name);
                Kao kaoUBS = new Kao(UBS.class, ontologyURIOntoOpenData, repository_URL);
                List<UBS> aux = kaoUBS.retrieveAllInstances();
                if (aux == null) {
                    id = 0;
                } else {
                    id += 1;
                }
                UBS ubs = kaoUBS.create("UBS_" + id);
                ubs.setId(id);
                ubs.setFoafName(name);
                //CityDTO city = retrieveCity(Integer.parseInt(code));
                Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
                City city = kaoCity.retrieveInstance("CITY_" + code);
                ubs.setHasCity(city);
                kaoCity.save();
                ubs.setHasState(city.getHasState());
                ubs.setStreet(street);
                ubs.setDistrict(district);
                ubs.setPhone(phone);
                ubs.setType("Não fornecedido");
                ubs.setFoafStatus("Completed");
                kaoUBS.save();
            }

            ubs_base.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUBS_PAC() {
        int id = 0;
        try {
            String file = "./basesDeDados/PAC_2013_08.csv";
            CsvReader ubs_base = new CsvReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            ubs_base.readHeaders();
            while (ubs_base.readRecord()) {
                String department = ubs_base.get("dsc_orgao");
                if (department.equals("Ministério da Saúde")) {
                    String[] ufs = ubs_base.get("sig_uf").split(" ");
                    String name = ubs_base.get("dsc_titulo");
                    String type = "";
                    if (name.contains("Ampliação")) {
                        type = "Ampliação";
                    } else {
                        String[] t = name.split(" ");
                        type = t[t.length - 1];
                    }
                    String[] cities = ubs_base.get("txt_municipios").split(",");
                    String val_2011_2014 = ubs_base.get("val_2011_2014");
                    String val_pos_2014 = ubs_base.get("val_pos_2014");
                    String investiment_total = ubs_base.get("investimento_total");
                    String executor = ubs_base.get("txt_executores");
                    String status = ubs_base.get("idn_estagio");
                    String note = ubs_base.get("observacao");
                    // perform program logic here
                    System.out.println(type + ":" + name);
                    for (String s : ufs) {
                        for (String c : cities) {
                            if (c.contains("/")) {
                                String d[] = department.split("/");
                                c = d[0];
                            }
                            Kao kaoUBS = new Kao(UBS.class, ontologyURIOntoOpenData, repository_URL);
                            List<UBS> aux = kaoUBS.retrieveAllInstances();
                            if (aux == null) {
                                id = 0;
                            } else {
                                id += 1;
                            }

                            UBS ubs = kaoUBS.create("UBS_" + id);
                            ubs.setId(id);
                            ubs.setFoafName(name);
                            Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
                            State state = kaoState.retrieveInstance("STATE_" + s.toUpperCase());
                            ubs.setHasState(state);
                            kaoState.save();
                            Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
                            List<City> cityList = kaoCity.retrieveAllInstances();
                            City city = null;
                            for (City ca : cityList) {
                                if (ca.getHasState().equals(state) && ca.getFoafName().equals(name.toUpperCase())) {
                                    city = ca;
                                }
                            }
                            ubs.setHasCity(city);
                            kaoCity.save();
                            if (val_2011_2014.isEmpty()) {
                                ubs.setVal_2011_2014(0.0);
                            } else {
                                ubs.setVal_2011_2014(Double.parseDouble(val_2011_2014));
                            }

                            if (val_pos_2014.isEmpty()) {
                                ubs.setVal_pos_2014(0.0);
                            } else {
                                ubs.setVal_pos_2014(Double.parseDouble(val_pos_2014));
                            }
                            if (investiment_total.isEmpty()) {
                                ubs.setInvestiment_total(0.0);
                            } else {
                                ubs.setInvestiment_total(Double.parseDouble(investiment_total));
                            }
                            ubs.setDepartment(department);
                            ubs.setFoafStatus(status);
                            ubs.setNote(note);
                            ubs.setType(type);
                            kaoUBS.save();
                        }
                    }
                }
            }

            ubs_base.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<UBSDTO> retrieveAllUBS() {
        Kao kaoUBS = new Kao(UBS.class, ontologyURIOntoOpenData, repository_URL);
        List<UBS> ubss = kaoUBS.retrieveAllInstances();
        ArrayList<UBSDTO> listDTO = new ArrayList<UBSDTO>();
        for (UBS ubs : ubss) {
            UBSDTO u = new UBSDTO();
            u.setCity(ubs.getHasCity());
            u.setDepartment(ubs.getDepartment());
            u.setDistrict(ubs.getDistrict());
            u.setExecutor(ubs.getExecutor());
            u.setId(ubs.getId());
            u.setInvestiment_total(ubs.getInvestiment_total());
            u.setName(ubs.getFoafName());
            u.setPhone(ubs.getPhone());
            u.setState(ubs.getHasState());
            u.setStatus(ubs.getFoafStatus());
            u.setStreet(ubs.getStreet());
            u.setType(ubs.getType());
            u.setVal_2011_2014(ubs.getVal_2011_2014());
            u.setVal_pos_2014(ubs.getVal_pos_2014());
            listDTO.add(u);

        }
        kaoUBS.save();
        return listDTO;
    }

    public ArrayList<UBSDTO> retrieveAllUBS_ByState(String uf) {
        Kao kaoUBS_ByState = new Kao(UBS.class, ontologyURIOntoOpenData, repository_URL);
        List<UBS> allUBS_ByState = kaoUBS_ByState.retrieveAllInstances();
        ArrayList<UBSDTO> listDTO = new ArrayList<UBSDTO>();

        for (UBS ubs : allUBS_ByState) {
            if (ubs.getHasState().getUf().equals(uf.toUpperCase())) {
                UBSDTO u = new UBSDTO();

                u.setCity(ubs.getHasCity());
                u.setDepartment(ubs.getDepartment());
                u.setDistrict(ubs.getDistrict());
                u.setExecutor(ubs.getExecutor());
                u.setId(ubs.getId());
                if (ubs.getVal_2011_2014() == null) {
                    u.setVal_2011_2014(0.0);
                } else {
                    u.setVal_2011_2014(ubs.getVal_2011_2014());
                }

                if (ubs.getVal_pos_2014() == null) {
                    u.setVal_pos_2014(0.0);
                } else {
                    u.setVal_pos_2014(ubs.getVal_pos_2014());
                }
                if (ubs.getInvestiment_total() == null) {
                    u.setInvestiment_total(0.0);
                } else {
                    u.setInvestiment_total(ubs.getInvestiment_total());
                }

                u.setName(ubs.getFoafName());
                u.setPhone(ubs.getPhone());
                u.setState(ubs.getHasState());
                u.setStatus(ubs.getFoafStatus());
                u.setStreet(ubs.getStreet());
                u.setType(ubs.getType());
                listDTO.add(u);
            }
        }

        kaoUBS_ByState.save();
        return listDTO;

    }

    public ArrayList<UBSDTO> retrieveAllUBS_ByCity(String uf, String nameCity) {
        List<UBSDTO> listUBS_ByState = retrieveAllUBS_ByState(uf);
        CityDTO city = retrieveCity(uf, nameCity);
        ArrayList<UBSDTO> listUBS_ByCity = new ArrayList<UBSDTO>();
        int numberOfUBSCompleted = 0;
        int numberOfUBSProvided = 0;
        for (UBSDTO ubs : listUBS_ByState) {
            if (ubs.getCity().equals(city)) {
                listUBS_ByCity.add(ubs);
                if (ubs.getStatus().equals("Completed")) {
                    numberOfUBSCompleted += 1;
                } else {
                    numberOfUBSProvided += 1;

                }
            }
        }
        Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
        City c = kaoCity.retrieveInstance("CITY_" + city.getId());

        c.setNumberOfUBSCompleted(numberOfUBSCompleted);

        c.setNumberOfUBSProvided(numberOfUBSProvided);

        kaoCity.save();
        return listUBS_ByCity;
    }

    public void countPopulationAndUBS_ByState() {
        for (int x = 0; x < uf.length; x++) {
            int population = 0;
            int qtdUBSCompleted = 0;
            int qtdUBSProvided = 0;
            for (CityDTO city : retrieveAllCity_ByState(uf[x])) {
                population += city.getPopulation();
            }
            for (UBSDTO ubs : retrieveAllUBS_ByState(uf[x])) {
                if (ubs.getStatus().equals("Completed")) {
                    qtdUBSCompleted += 1;
                } else {
                    qtdUBSProvided += 1;

                }
            }
            Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
            State state = kaoState.retrieveInstance("STATE_" + uf[x].toUpperCase());

            state.setPopulation(population);

            state.setNumberOfUBSCompleted(qtdUBSCompleted);

            state.setNumberOfUBSProvided(qtdUBSProvided);

            kaoState.save();
        }
    }

    public Double currentAveragePopulationByUBS(String uf) {
        StateDTO state = retrieveState(uf);
        double currentAveragePopUBS = (double) (state.getPopulation() / state.getNumberOfUBSCompleted());
        return currentAveragePopUBS;
    }

    public Double providedAveragePopulationByUBS(String uf) {
        StateDTO state = retrieveState(uf);
        //(numerodeUBS1*0,2+numerodeUBS2*0,4+numerodeUBS3*0.6+numerodeUBS4*0.8) / numerodehabitantes
        double providedAveragePopUBS = (double) (state.getPopulation() / state.getNumberOfUBSProvided());
        return providedAveragePopUBS;
    }

    public Double currentAveragePopulationByUBS_City(String uf, String cityName) {
        CityDTO city = retrieveCity(uf, cityName);
        double currentAveragePopUBS = (double) (city.getPopulation() / city.getNumberOfUBSCompleted());
        return currentAveragePopUBS;
    }

    public Double providedAveragePopulationByUBS_City(String uf, String cityName) {
        CityDTO city = retrieveCity(uf, cityName);
        double providedAveragePopUBS = (double) (city.getPopulation() / city.getNumberOfUBSProvided());
        return providedAveragePopUBS;
    }

    public static void main(String[] args) {
        ClassePrincipalProvisoria cp = new ClassePrincipalProvisoria();
        //cp.createStates();
        //cp.createCity();
        //cp.createUBSReady();
        //cp.createUBS_PAC();
        //cp.countPopulationAndUBS_ByState();
        /*List<CityDTO> l = cp.retrieveAllCity_ByState("al");

         for (CityDTO s : l) {
         System.out.println(s.getName() + "-->" + s.getUf());
         }*/
    }

}
