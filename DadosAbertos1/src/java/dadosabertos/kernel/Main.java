package dadosabertos.kernel;


import br.ufal.ic.joint.RepositoryFacade;
import br.ufal.ic.joint.module.ontology.operations.OntologyCompiler;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author williams
 */
public class Main {
    
    public static void main(String[] args){
        String destino = "./lib/ontoOpenData.jar";
        String origem1 = "./ontologia/foaf.rdf";
        String origem2 = "./ontologia/ontoOpenData.rdf";
        List<String> lista = new ArrayList<String>();
        lista.add(origem1);
        lista.add(origem2);
        
        RepositoryFacade facade = new RepositoryFacade();
        OntologyCompiler compiler = facade.getOntologyCompiler(destino, lista);
        compiler.compile();
    }
    
}
