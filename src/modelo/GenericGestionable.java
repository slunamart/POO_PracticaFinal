package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GenericGestionable<T> implements Serializable {

    private final List<T> elementos;

    /**
     * Constructor que inicializa la lista de elementos.
     */
    public GenericGestionable() {
        this.elementos = new ArrayList<>();
    }

    /**
     * Añade un elemento a la lista.
     *
     * @param elemento Elemento a añadir.
     * @return {@code true} si el elemento fue añadido correctamente, {@code false} si ya existía.
     */
    public boolean alta(T elemento) {
        if (elementos.contains(elemento)) {
            System.out.println("El elemento ya existe.");
            return false;
        }
        elementos.add(elemento);
        System.out.println("Elemento añadido: " + elemento);
        return true;
    }

    /**
     * Elimina un elemento de la lista.
     *
     * @param elemento Elemento a eliminar.
     * @return {@code true} si el elemento fue eliminado, {@code false} si no existía.
     */
    public boolean baja(T elemento) {
        if (!elementos.contains(elemento)) {
            System.out.println("El elemento no existe.");
            return false;
        }
        elementos.remove(elemento);
        System.out.println("Elemento eliminado: " + elemento);
        return true;
    }

    /**
     * Busca elementos que cumplan con un criterio específico.
     *
     * @param criterio Una condición definida como {@code Predicate<T>}.
     * @return Lista de elementos que cumplen el criterio.
     */
    public List<T> buscar(Predicate<T> criterio) {
        List<T> resultado = new ArrayList<>();
        for (T elemento : elementos) {
            if (criterio.test(elemento)) {
                resultado.add(elemento);
            }
        }
        return resultado;
    }

    /**
     * Verifica si un elemento existe en la lista.
     *
     * @param criterio Una condición definida como {@code Predicate<T>}.
     * @return {@code true} si existe al menos un elemento que cumple con el criterio, {@code false} en caso contrario.
     */
    public boolean contiene(Predicate<T> criterio) {
        for (T elemento : elementos) {
            if (criterio.test(elemento)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve el número de elementos almacenados.
     *
     * @return Número de elementos en la lista.
     */
    public int size() {
        return elementos.size();
    }

    public List<T> getArray(){
        return elementos;
    }
}