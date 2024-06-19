package clases;

public class jugador {
    //Atributos
    private String nombre;
    private int edad;
    private int jGanados;
    private int jPerdidos;
    private int tJuegos;    

    public jugador(String nombre, int edad, int jGanados, int jPerdidos, int tJuegos) {
        this.nombre = nombre;
        this.edad = edad;
        this.jGanados = jGanados;
        this.jPerdidos = jPerdidos;
        this.tJuegos = tJuegos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getjGanados() {
        return jGanados;
    }

    public void setjGanados(int jGanados) {
        this.jGanados = jGanados;
    }

    public int getjPerdidos() {
        return jPerdidos;
    }

    public void setjPerdidos(int jPerdidos) {
        this.jPerdidos = jPerdidos;
    }

    public int gettJuegos() {
        return tJuegos;
    }

    public void settJuegos(int tJuegos) {
        this.tJuegos = tJuegos;
    }
    
    

}
