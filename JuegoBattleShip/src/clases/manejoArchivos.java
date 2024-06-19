
package clases;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;


public class manejoArchivos {

//ESCRIBE A ARCHIVO JUGADOR
public void escribirJugador(ArrayList<jugador> listaJugadores){
  
    try ( PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter("Archivo_ListaJugadores.txt")))) { //Creamos con PrintWriter un objeto llamado salida con parametros de BufferedWritter y FileWriter con el ruta/nombre del archivo
            for (int i = 0; i < listaJugadores.size(); i++) { //Con este for escribiremos linea por linea lo que tengamos en el vector invLibro
                salida.println(listaJugadores.get(i).getNombre() + "/" + listaJugadores.get(i).getEdad() + "/" + listaJugadores.get(i).getjGanados() + "/" + listaJugadores.get(i).getjPerdidos() + "/" + listaJugadores.get(i).gettJuegos());
            }
            salida.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en escritura!", "Alerta!", JOptionPane.ERROR_MESSAGE); //Si hay un dato inválido nos saldrá error al escribir los datos
        }
    
}

//LEE DESDE ARCHIVO JUGADOR
public ArrayList<jugador> leerJugador() {
   ArrayList<jugador> listaJugadores = new ArrayList<> ();
   
   try {
            BufferedReader entrada = new BufferedReader(new FileReader("Archivo_ListaJugadores.txt")); //Leemos el archivo creando un objeto BufferedReader llamado entrada.
            String s, s2 = new String(); //creamos dos cadenas para hacer la leida de datos y creamos las variables que necesitaremos
            int edad;
            int jGanados;
            int jPerdidos;
            int tJuegos;  
            String nombre;

            while ((s = entrada.readLine()) != null) { //Si toda una linea en el txt es valida se sigue
                s2 += s + "\n"; //en s guardamos la linea que leimos y en s2 la almacenamos con el salto de linea
                String[] arreglo = s.split("/"); //Los elementos del arreglo tipo string serán separados por el caracter "/"
                nombre = arreglo[0];
                edad = Integer.parseInt(arreglo[1]);
                jGanados = Integer.parseInt(arreglo[2]);
                jPerdidos = Integer.parseInt(arreglo[3]);
                tJuegos = Integer.parseInt(arreglo[4]);
                
                //Se toman los valores del arreglo tipo string y se meten al constructor de libro , creando un objeto llamado insertar de tipo Libro
                jugador insertar = new jugador(nombre, edad, jGanados, jPerdidos, tJuegos);
                //y con la instruccion add agregamos un dato nuevo al arraylist invLibro con el atributo insertar
                listaJugadores.add(insertar);
            }
            entrada.close();
        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(null, "Error de E/S!", "Alerta!", JOptionPane.ERROR_MESSAGE); //Si hubo errores nos salta una ventana con este error
        }
        return listaJugadores;
    
}

////Escribimos las CORDENADAS
//public void EscribirCC (ArrayList<ConstructorCoo> invCC){
//    try (PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter("Archivo_cc.txt")))){
//            for(int i = 0; i < invCC.size(); i++){
//                salida.println(invCC.get(i).getCx() + "/" + invCC.get(i).getCy());
//            }
//            salida.close();
//        }catch (IOException ex){
//            JOptionPane.showMessageDialog(null, "Error en escritura!", "Alerta", JOptionPane.ERROR_MESSAGE);
//        }
//}

//LEEMOS CORDENADAS
public ArrayList<ConstructorCoo> leerCC(){
        ArrayList<ConstructorCoo> invCC = new ArrayList<>();
        try{
            BufferedReader entrada = new BufferedReader(new FileReader("Archivo_cc.txt")); //Leemos el archivo creando un objeto BufferedReader llamado entrada.
            String s,s2 = new String();
            int cx1,cx2,cx3;
            int cy1,cy2,cy3;
            
            while ((s = entrada.readLine()) != null){
                s2 += s + "\n";
                String[] arreglo = s.split("/");
                cx1 = Integer.parseInt(arreglo[0]);
                cy1 = Integer.parseInt(arreglo[1]);
                cx2 = Integer.parseInt(arreglo[2]);
                cy2 = Integer.parseInt(arreglo[3]);
                cx3 = Integer.parseInt(arreglo[4]);
                cy3 = Integer.parseInt(arreglo[5]);
                ConstructorCoo insertar = new ConstructorCoo (cx1,cy1,cx2,cy2,cx3,cy3);
                invCC.add(insertar);
                
            }
            entrada.close();
        }catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(null, "Error de E/S!", "Alerta!", JOptionPane.ERROR_MESSAGE); //Si hubo errores nos salta una ventana con este error
        }
        return (invCC);
        
    }


//ESCRIBE A ARCHIVO PARTIDAS
public void escribirPartida(ArrayList<partidas> listaJugadores){
  
    try ( PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter("Archivo_HistorialPartidas.txt")))) { //Creamos con PrintWriter un objeto llamado salida con parametros de BufferedWritter y FileWriter con el ruta/nombre del archivo
            for (int i = 0; i < listaJugadores.size(); i++) { //Con este for escribiremos linea por linea lo que tengamos en el vector invLibro
                salida.println(listaJugadores.get(i).getNombre() + "/" + listaJugadores.get(i).getNumPartida() + "/" + listaJugadores.get(i).getGano() + "/" + listaJugadores.get(i).getPerdio() + "/" + listaJugadores.get(i).getBarcosHundido());
            }
            salida.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en escritura!", "Alerta!", JOptionPane.ERROR_MESSAGE); //Si hay un dato inválido nos saldrá error al escribir los datos
        }
    
}

//LEE DESDE ARCHIVO PARTIDAS
public ArrayList<partidas> leerPartida() {
   ArrayList<partidas> listaJugadores = new ArrayList<> ();
   
   try {
            BufferedReader entrada = new BufferedReader(new FileReader("Archivo_HistorialPartidas.txt")); //Leemos el archivo creando un objeto BufferedReader llamado entrada.
            String s, s2 = new String(); //creamos dos cadenas para hacer la leida de datos y creamos las variables que necesitaremos
             String nombre;
            int numPartida;
            int gano;
            int perdio;
            int barcosHundidos;

            while ((s = entrada.readLine()) != null) { //Si toda una linea en el txt es valida se sigue
                s2 += s + "\n"; //en s guardamos la linea que leimos y en s2 la almacenamos con el salto de linea
                String[] arreglo = s.split("/"); //Los elementos del arreglo tipo string serán separados por el caracter "/"
                nombre = arreglo[0];
                numPartida = Integer.parseInt(arreglo[1]);
                gano = Integer.parseInt(arreglo[2]);
                perdio = Integer.parseInt(arreglo[3]);
                barcosHundidos = Integer.parseInt(arreglo[4]);
                
                //Se toman los valores del arreglo tipo string y se meten al constructor de libro , creando un objeto llamado insertar de tipo Libro
                partidas insertar = new partidas(nombre, numPartida, gano, perdio, barcosHundidos);
                //y con la instruccion add agregamos un dato nuevo al arraylist invLibro con el atributo insertar
                listaJugadores.add(insertar);
            }
            entrada.close();
        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(null, "Error de E/S!", "Alerta!", JOptionPane.ERROR_MESSAGE); //Si hubo errores nos salta una ventana con este error
        }
        return listaJugadores;
    
}
    
}
