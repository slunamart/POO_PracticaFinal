package modelo;

import java.util.ArrayList;
import java.util.List;

public class Gestionador {

    private List<ObjetoGestionable> lista;

    public Gestionador(){
        this.lista = new ArrayList<ObjetoGestionable>();
    }

    public void add( ObjetoGestionable elem ){
        lista.add( elem );
    }

    public boolean yaRegistrado( ObjetoGestionable elem ){
        for( ObjetoGestionable elementoLista : lista ){
            if( elementoLista.esIgual( elem ) )
                return true;
        }
        return false;
    }

    public void rm( ObjetoGestionable elem ){
        for( ObjetoGestionable elementoLista : lista ){
            if( elementoLista.esIgual( elem ) ){
                lista.remove( elementoLista );
                return;
            }
        }
    }

    public void mod( ObjetoGestionable elem){
        // ???
    }

    public int size(){
        return lista.size();
    }

    public boolean isEmpty(){
        return lista.isEmpty();
    }

}
