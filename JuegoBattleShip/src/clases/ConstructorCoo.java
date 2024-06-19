package clases;

public class ConstructorCoo {
    //Atributos
    //Primer Coordenada del barco
    private int x1;
    private int y1;
    //Segunda Coordenada del barco
    private int x2;
    private int y2;
    //Tercera Coordenada del barco
    private int x3;
    private int y3; 
    //Banderas que indicanq si alguna cooordenada o el barco completo fue encontrado por el jugador
    private boolean xy1;
    private boolean xy2;
    private boolean xy3;
    private boolean encontrado;
    
    //Constructor
    public ConstructorCoo(int x1,int y1,int x2, int y2,int x3,int y3){
       this.x1 = x1;
       this.y1 = y1;
       this.x2 = x2;
       this.y2 = y2;
       this.x3 = x3;
       this.y3 = y3;
       this.xy1 = false;
       this.xy2 = false;
       this.xy3 = false;
       this.encontrado = false;
    }
    
    //Modifican las coordenadas
    public void setX1(int x){this.x1 = x;}
    public void setY1(int y){this.y1 = y;}
    public void setX2(int x){this.x2 = x;}
    public void setY2(int y){this.y2 = y;}
    public void setX3(int x){this.x3 = x;}
    public void setY3(int y){this.y3 = y;}
    
    //Retornan las coordenasa
    public int getX1(){return x1;}
    public int getY1(){return y1;}
    public int getX2(){return x2;}
    public int getY2(){return y2;}
    public int getX3(){return x3;}
    public int getY3(){return y3;}
    
    //Actualizan las banderas
    public void xy1Encontrado(){this.xy1 = true;}
    public void xy2Encontrado(){this.xy2 = true;}
    public void xy3Encontrado(){this.xy3 = true;}
    public void barcoEncontrado(){this.encontrado = true;}
    
    //Retorna el estado e las banderas
    public boolean xy1Estado(){return xy1;}
    public boolean xy2Estado(){return xy2;}
    public boolean xy3Estado(){return xy3;}
    public boolean getEstado(){return encontrado;}
    
}
