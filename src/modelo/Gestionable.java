package modelo;

/**
 * Interfaz que define las operaciones básicas de gestión.
 * Es utilizada para estandarizar las acciones comunes en módulos de gestión como
 * clientes, vehículos, secciones, y ventas.
 *
 * @param <T> Tipo de objeto que será gestionado.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public interface Gestionable<T> {
    /**
     * Método para dar de alta un nuevo elemento en el sistema.
     * Debe implementarse para agregar un nuevo objeto de tipo {@code T}.
     */
    void alta();

    /**
     * Método para dar de baja un elemento existente del sistema.
     * Debe implementarse para eliminar un objeto de tipo {@code T}.
     */
    void baja();

    /**
     * Muestra el menú de opciones relacionadas con el módulo de gestión.
     * Debe implementarse para proporcionar una interfaz interactiva con el usuario.
     */
    void showMenu();
}
