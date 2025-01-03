package modelo;

//import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestionClientesTest {

    private Concesionario c;

    @Test
    public void anadirCliente(){
        Cliente cl = new Cliente();
        this.c.addCliente(cl);
        assertTrue(c.sizeVehiculo() > 0 );
    }

}