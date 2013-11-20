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
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import joint.codegen.nsbf6996f0.City;
import joint.codegen.nsbf6996f0.State;
import joint.codegen.nsbf6996f0.UBS;

/**
 *
 * @author tbastos
 */
public class ClassePrincipalProvisoria {

    private final String repository_URL = "http://localhost:8080/openrdf-sesame/repositories/concurso";
    private final String ontologyURIFoaf = "http://xmlns.com/foaf/0.1/";
    private final String ontologyURIOntoOpenData = "http://www.nees.com.br/ontologies/2013/opendata";
    Collator collator = Collator.getInstance(Locale.US);

    public ClassePrincipalProvisoria() {
        collator.setStrength(Collator.PRIMARY);
    }

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
//OK

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
            state.setInvestiment_total(0.0);
            state.setVal_2011_2014(0.0);
            state.setVal_pos_2014(0.0);
            state.setInvestiment(0.0);
            kaoState.save();
        }
    }
//OK

    public StateDTO retrieveState(String nameState) {
        Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
        List<State> allState = kaoState.retrieveAllInstances();
        for (State state : allState) {
            if (collator.compare(state.getFoafName(), nameState) == 0) {
                StateDTO stateDTO = new StateDTO();
                stateDTO.setId(state.getId());
                stateDTO.setName(state.getFoafName());
                stateDTO.setUf(state.getUf());
                stateDTO.setNumberOfUBSCompleted(state.getNumberOfUBSCompleted());
                stateDTO.setNumberOfUBSProvided(state.getNumberOfUBSProvided());
                stateDTO.setPopulation(state.getPopulation());
                stateDTO.setInvestment(state.getInvestiment());
                stateDTO.setVal_2011_2014(state.getVal_2011_2014());
                stateDTO.setVal_pos_2014(state.getVal_pos_2014());
                stateDTO.setInvestment_total(state.getInvestiment_total());
                kaoState.save();

                return stateDTO;
            }
        }
        return null;
    }
//OK

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
            sdto.setInvestment(s.getInvestiment());
            sdto.setVal_2011_2014(s.getVal_2011_2014());
            sdto.setVal_pos_2014(s.getVal_pos_2014());
            sdto.setInvestment_total(s.getInvestiment_total());
            statesDTO.add(sdto);
        }
        kaoState.save();
        return statesDTO;
    }
//OK

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
                    if (collator.compare(uf_, uf) == 0) {
                        if (collator.compare(nameCity, name) == 0) {
                            String pop = censo.get("Populacao2010");
                            population = Integer.parseInt(pop);
                            censo.close();
                            break;
                        }
                    }
                }
                censo.close();
                // perform program logic here
                System.out.println(uf + ":" + name + ":" + population);
                Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
                State state = kaoState.retrieveInstance("STATE_" + uf.toUpperCase());
                state.setPopulation(state.getPopulation()+population);
                kaoState.save();
                Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
                City city = kaoCity.create("CITY_" + code);
                city.setId(Integer.parseInt(code));
                city.setFoafName(name);
                city.setHasState(state);
                city.setPopulation(population);
                city.setVal_2011_2014(0.0);
                city.setVal_pos_2014(0.0);
                city.setInvestiment(0.0);
                city.setInvestiment_total(0.0);
                city.setNumberOfUBSCompleted(0);
                city.setNumberOfUBSProvided(0);
                kaoCity.save();
            }
            munic.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//OK
/*
    public void countPopulation_ByState() {
        for (int x = 0; x < estados.length; x++) {
            int population = 0;
            List<CityDTO> allCity = retrieveAllCity_ByState(estados[x]);
            for (CityDTO c : allCity) {
                population += c.getPopulation();
            }
            Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
            State s = kaoState.retrieveInstance("STATE_" + uf[x].toUpperCase());
            s.setPopulation(population);
            kaoState.save();
        }
    }*/
//OK

    public CityDTO retrieveCity(int codeCity) {
        Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
        City city = kaoCity.retrieveInstance("CITY_" + codeCity);
        CityDTO cdto = new CityDTO();

        cdto.setId(codeCity);

        cdto.setName(city.getFoafName());
        cdto.setPopulation(city.getPopulation());
        cdto.setState(city.getHasState());
        return cdto;
    }
//OK

    public ArrayList<CityDTO> retrieveAllCity_ByState(String nameState) {
        System.out.println(nameState);
        StateDTO state = retrieveState(nameState);
        Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
        List<City> allCity = kaoCity.retrieveAllInstances();
        ArrayList<CityDTO> allCityDTO = new ArrayList<CityDTO>();
        for (City c : allCity) {
            if (collator.compare(c.getHasState().getUf(), state.getUf()) == 0) {
                CityDTO cdto = new CityDTO();
                cdto.setId(c.getId());
                cdto.setName(c.getFoafName());
                cdto.setPopulation(c.getPopulation());
                cdto.setState(c.getHasState());
                //cdto.setNumberOfUBSCompleted(c.getNumberOfUBSCompleted());
                cdto.setNumberOfUBSCompleted(0);
                cdto.setNumberOfUBSProvided(c.getNumberOfUBSProvided());
                cdto.setVal_2011_2014(c.getVal_2011_2014());
                cdto.setVal_pos_2014(c.getVal_pos_2014());
                cdto.setInvestment(c.getInvestiment());
                cdto.setInvestment_total(c.getInvestiment_total());

                allCityDTO.add(cdto);
            }
        }
        System.out.println(allCityDTO);
        return allCityDTO;
    }
//OK

    public CityDTO retrieveCity(String nameState, String nameCity) {
        CityDTO cdto = new CityDTO();
        Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
        List<City> cityList = kaoCity.retrieveAllInstances();
        for (City c : cityList) {
            if (collator.compare(c.getHasState().getFoafName(), nameState) == 0) {
                if (collator.compare(c.getFoafName(), nameCity) == 0) {
                    cdto.setId(c.getId());
                    cdto.setName(c.getFoafName());
                    cdto.setPopulation(c.getPopulation());
                    cdto.setState(c.getHasState());
                    cdto.setNumberOfUBSCompleted(c.getNumberOfUBSCompleted());
                    cdto.setNumberOfUBSProvided(c.getNumberOfUBSProvided());
                    cdto.setVal_2011_2014(c.getVal_2011_2014());
                    cdto.setVal_pos_2014(c.getVal_pos_2014());
                    cdto.setInvestment(c.getInvestiment());
                    cdto.setInvestment_total(c.getInvestiment_total());
                    return cdto;
                }
            }
        }
        return cdto;
    }
//OK

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
            cdto.setNumberOfUBSCompleted(c.getNumberOfUBSCompleted());
            cdto.setNumberOfUBSProvided(c.getNumberOfUBSProvided());
            cdto.setVal_2011_2014(c.getVal_2011_2014());
            cdto.setVal_pos_2014(c.getVal_pos_2014());
            cdto.setInvestment(c.getInvestiment());
            cdto.setInvestment_total(c.getInvestiment_total());
            listDTO.add(cdto);
        }

        kaoCity.save();
        return listDTO;
    }
//OK

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
                if (aux
                        == null) {
                    id = 0;
                } else {
                    id += 1;
                }
                UBS ubs = kaoUBS.create("UBS_" + id);

                ubs.setId(id);
                ubs.setFoafName(name);
                Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
                City city = kaoCity.retrieveInstance("CITY_" + code);
                ubs.setHasCity(city);
                kaoCity.save();
                ubs.setHasState(city.getHasState());
                ubs.setStreet(street);
                ubs.setDistrict(district);
                ubs.setDepartment("Não fornecido");
                ubs.setExecutor("Não fornecido");
                ubs.setPhone(phone);
                ubs.setType("Não fornecedido");
                ubs.setFoafStatus("Concluído");
                ubs.setExecutor("Não fornecido");
                ubs.setVal_2011_2014(0.0);
                ubs.setVal_pos_2014(0.0);
                ubs.setInvestiment(0.0);
                ubs.setNote("Não fornecido");

                kaoUBS.save();
            }

            ubs_base.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//OK

    public void createUBS_PAC() {
        int id = 37691;
        try {
            String file = "./basesDeDados/PAC_2013_08.csv";
            CsvReader ubs_base = new CsvReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            ubs_base.readHeaders();
            while (ubs_base.readRecord()) {
                String department = ubs_base.get("dsc_orgao");
                if (department.equals("Ministério da Saúde")) {
                    String name = ubs_base.get("dsc_titulo");
                    if (!name.contains(" UPA ")) {
                        String[] ufs = ubs_base.get("sig_uf").split(" ");
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
                        String investiment = ubs_base.get("investimento_total");
                        String executor = ubs_base.get("txt_executores");
                        String status = ubs_base.get("idn_estagio");
                        String note = ubs_base.get("observacao");
                        // perform program logic here
                        System.out.println(type + ":" + name);
                        for (String s : ufs) {
                            for (String c : cities) {
                                if (c.contains("/")) {
                                    String d[] = c.split("/");
                                    c = d[0];

                                }
                                Kao kaoState = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
                                State state = kaoState.retrieveInstance("STATE_" + s.toUpperCase());
                                Kao kaoCity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
                                List<City> cityList = kaoCity.retrieveAllInstances();

                                for (City city : cityList) {

                                    if (collator.compare(city.getHasState().getFoafName(), state.getFoafName()) == 0) {
                                        if (collator.compare(city.getFoafName(), c) == 0) {
                                            Kao kaoUBS = new Kao(UBS.class, ontologyURIOntoOpenData, repository_URL);

                                            UBS ubs = kaoUBS.create("UBS_" + id);

                                            ubs.setId(id);
                                            ubs.setFoafName(name);

                                            Double aux2011_2014 = 0.0;
                                            if (val_2011_2014.equals("")) {
                                                aux2011_2014 = 0.0;
                                            } else {
                                                aux2011_2014 = Double.parseDouble(val_2011_2014);
                                            }
                                            ubs.setVal_2011_2014(aux2011_2014);

                                            Double auxPos_2014 = 0.0;
                                            if (val_pos_2014.equals("")) {
                                                auxPos_2014 = 0.0;
                                            } else {
                                                auxPos_2014 = (Double.parseDouble(val_pos_2014));
                                            }
                                            ubs.setVal_pos_2014(auxPos_2014);

                                            Double auxInvestiment = 0.0;
                                            if (investiment.equals("")) {
                                                auxInvestiment = (0.0);
                                            } else {
                                                auxInvestiment = (Double.parseDouble(investiment));
                                            }
                                            ubs.setInvestiment(auxInvestiment);
                                            Kao kcity = new Kao(City.class, ontologyURIOntoOpenData, repository_URL);
                                            City cityTemp = kcity.retrieveInstance("CITY_" + city.getId());
                                            cityTemp.setVal_2011_2014(city.getVal_2011_2014() + aux2011_2014);
                                            cityTemp.setVal_pos_2014(city.getVal_pos_2014() + auxPos_2014);
                                            cityTemp.setInvestiment(city.getInvestiment() + auxInvestiment);
                                            cityTemp.setInvestiment_total(city.getInvestiment_total() + aux2011_2014 + auxPos_2014 + auxInvestiment);
                                            kcity.save();

                                            ubs.setHasCity(cityTemp);

                                            Kao kstate = new Kao(State.class, ontologyURIOntoOpenData, repository_URL);
                                            State stateTemp = kstate.retrieveInstance("STATE_" + state.getUf());
                                            stateTemp.setVal_2011_2014(state.getVal_2011_2014() + aux2011_2014);
                                            stateTemp.setVal_pos_2014(state.getVal_pos_2014() + auxPos_2014);
                                            stateTemp.setInvestiment(state.getInvestiment() + auxInvestiment);
                                            state.setInvestiment_total(state.getInvestiment_total() + aux2011_2014 + auxPos_2014 + auxInvestiment);
                                            ubs.setHasState(state);
                                            kstate.save();
                                            ubs.setDistrict("Não fornecido");
                                            ubs.setPhone("Não fornecido");
                                            ubs.setStreet("Não fornecido");
                                            ubs.setDepartment(department);
                                            ubs.setFoafStatus(status);
                                            ubs.setType(type);
                                            ubs.setExecutor(executor);
                                            ubs.setNote(note);
                                            kaoUBS.save();
                                            id += 1;
                                        }
                                    }
                                }
                                kaoState.save();

                                kaoCity.save();
                            }
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
            u.setId(ubs.getId());
            u.setCity(ubs.getHasCity());
            u.setDepartment(ubs.getDepartment());
            u.setDistrict(ubs.getDistrict());
            u.setExecutor(ubs.getExecutor());
            u.setName(ubs.getFoafName());
            u.setPhone(ubs.getPhone());
            u.setState(ubs.getHasState());
            u.setStatus(ubs.getFoafStatus());
            u.setStreet(ubs.getStreet());
            u.setType(ubs.getType());
            u.setVal_2011_2014(ubs.getVal_2011_2014());
            u.setVal_pos_2014(ubs.getVal_pos_2014());
            u.setInvestiment(ubs.getInvestiment());

            listDTO.add(u);

        }

        kaoUBS.save();
        return listDTO;
    }

    public ArrayList<UBSDTO> retrieveAllUBS_ByState(String nameState) {
        Kao kaoUBS_ByState = new Kao(UBS.class, ontologyURIOntoOpenData, repository_URL);
        List<UBS> allUBS = kaoUBS_ByState.retrieveAllInstances();
        ArrayList<UBSDTO> listDTO = new ArrayList<UBSDTO>();

        for (UBS ubs : allUBS) {
            
            if (collator.compare(ubs.getHasState().getFoafName(), nameState) == 0) {
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
                if (ubs.getInvestiment() == null) {
                    u.setInvestiment(0.0);
                } else {
                    u.setInvestiment(ubs.getInvestiment());
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

    public ArrayList<UBSDTO> retrieveAllUBS_ByCity(String nameState, String nameCity) {
        List<UBSDTO> listUBS_ByState = retrieveAllUBS_ByState(nameState);
        CityDTO city = retrieveCity(nameState, nameCity);
        ArrayList<UBSDTO> listUBS_ByCity = new ArrayList<UBSDTO>();
        int numberOfUBSCompleted = 0;
        int numberOfUBSProvided = 0;
        for (UBSDTO ubs : listUBS_ByState) {
            if (collator.compare(ubs.getCity().getName(), nameCity) == 0) {
                listUBS_ByCity.add(ubs);
                if (ubs.getStatus().equals("Concluído")) {
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

    public Double investmentUBS_ByState(String stateName) {
        Double investment = 0.0;
        List<UBSDTO> list = retrieveAllUBS_ByState(stateName);
        for (UBSDTO ubs : list) {
            investment += (ubs.getVal_2011_2014() + ubs.getVal_pos_2014() + ubs.getInvestiment());
        }
        return investment;
    }

    public Double investmentUBS_ByCity(String stateName, String cityName) {
        Double investment = 0.0;
        CityDTO city = retrieveCity(stateName, cityName);
        List<UBSDTO> list = retrieveAllUBS_ByCity(stateName, cityName);
        for (UBSDTO ubs : list) {
            investment += (ubs.getVal_2011_2014() + ubs.getVal_pos_2014() + ubs.getInvestiment());
        }
        return investment;
    }

    public Integer currentQuantityUBS_ByState(String nameState) {
        int qtdCompleted = 0;
        List<UBSDTO> listUBS = retrieveAllUBS_ByState(nameState);
        for (UBSDTO u : listUBS) {
            if (u.getType().equals("Não fornecedido")) {
                qtdCompleted += 1;
            }
        }
        return qtdCompleted;
    }

    public ArrayList<Integer> providedQuantityUBS_ByState(String nameState) {
        int qtdType1 = 0;
        int qtdType2 = 0;
        int qtdType3 = 0;
        int qtdType4 = 0;
        int qtdAmpliacao = 0;
        List<UBSDTO> listUBS = retrieveAllUBS_ByState(nameState);
        for (UBSDTO u : listUBS) {
            if (u.getType().equals("I")) {
                qtdType1 += 1;
            } else if (u.getType().equals("II")) {
                qtdType2 += 1;
            } else if (u.getType().equals("III")) {
                qtdType3 += 1;
            } else if (u.getType().equals("IV")) {
                qtdType4 += 1;
            } else if (u.getType().equals("Ampliação")) {
                qtdAmpliacao += 1;
            }
        }
        ArrayList<Integer> qt = new ArrayList<Integer>();
        qt.add(qtdType1);
        qt.add(qtdType2);
        qt.add(qtdType3);
        qt.add(qtdType4);
        qt.add(qtdAmpliacao);
        return qt;
    }

    public Integer currentQuantityUBS_ByCity(String nameState, String cityName) {
        int qtdCompleted = 0;
        List<UBSDTO> listUBS = retrieveAllUBS_ByCity(nameState, cityName);
        for (UBSDTO u : listUBS) {
            if (u.getType().equals("Não fornecedido")) {
                qtdCompleted += 1;
            }
        }
        return qtdCompleted;
    }

    public ArrayList<Integer> providedQuantityUBS_ByCity(String nameState, String nameCity) {
        CityDTO city = retrieveCity(nameState, nameCity);
        int qtdType1 = 0;
        int qtdType2 = 0;
        int qtdType3 = 0;
        int qtdType4 = 0;
        int qtdAmpliacao = 0;
        List<UBSDTO> listUBS = retrieveAllUBS_ByCity(nameState, nameCity);
        for (UBSDTO u : listUBS) {
            if (u.getType().equals("I")) {
                qtdType1 += 1;
            } else if (u.getType().equals("II")) {
                qtdType2 += 1;
            } else if (u.getType().equals("III")) {
                qtdType3 += 1;
            } else if (u.getType().equals("IV")) {
                qtdType4 += 1;
            } else if (u.getType().equals("Ampliação")) {
                qtdAmpliacao += 1;
            }
        }
        ArrayList<Integer> qt = new ArrayList<Integer>();
        qt.add(qtdType1);
        qt.add(qtdType2);
        qt.add(qtdType3);
        qt.add(qtdType4);
        qt.add(qtdAmpliacao);
        return qt;
    }

    public static void main(String[] args) {
        ClassePrincipalProvisoria cp = new ClassePrincipalProvisoria();
        cp.createStates();
        cp.createCity();
//        cp.countPopulation_ByState();
        cp.createUBSReady();
        /*List<UBSDTO> l = cp.retrieveAllUBS_ByCity("Paraná", "Perobal");
         for (UBSDTO u : l) {
         System.out.println(u.getCity().getName() + ":" + u.getCity().getId() + ":" + u.getState().getUf());
         System.out.println(u.getStatus());
         }*/
    }
}
