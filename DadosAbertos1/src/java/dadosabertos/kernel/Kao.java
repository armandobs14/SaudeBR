package dadosabertos.kernel;

import br.ufal.ic.joint.module.kao.AbstractKAO;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author williams
 */
public class Kao extends AbstractKAO{

    public <T> Kao(Class<T> classe, String ontologyURI) {
        super(classe, ontologyURI);
    }
    public <T> Kao(Class<T> classe, String ontologyURI, String repository_URL){
        super(classe, ontologyURI, repository_URL);
    }
    
    
}
