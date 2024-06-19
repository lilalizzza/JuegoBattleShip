
package clases;

public class partidas {
    private String nombre;
    private int numPartida;
    private int gano;
    private int perdio;
    private int barcosHundido;

    public partidas(String nombre, int numPartida, int gano, int perdio, int barcosHundido) {
        this.nombre = nombre;
        this.numPartida = numPartida;
        this.gano = gano;
        this.perdio = perdio;
        this.barcosHundido = barcosHundido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumPartida() {
        return numPartida;
    }

    public void setNumPartida(int numPartida) {
        this.numPartida = numPartida;
    }

    public int getGano() {
        return gano;
    }

    public void setGano(int gano) {
        this.gano = gano;
    }

    public int getPerdio() {
        return perdio;
    }

    public void setPerdio(int perdio) {
        this.perdio = perdio;
    }

    public int getBarcosHundido() {
        return barcosHundido;
    }

    public void setBarcosHundido(int barcosHundido) {
        this.barcosHundido = barcosHundido;
    }
    
    
    
    
}
