
package Formularios;

import clases.ConstructorCoo;
import clases.jugador;
import clases.manejoArchivos;
import clases.partidas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class jugar extends javax.swing.JFrame {
    private final String jugadorActual;
    private final int edadActual;
    private final int Filas = 10; //Filas de la matriz de botones
    private final int Columnas = 10; //Columnas de la matriz de botones
    private int intentos; //Numero de intentos iniciales (contador)
    private int barcosRes; //Numero de barcos enemigos iniciales (contador)
    private boolean coorEncontrada; //Bandera
    private boolean bEncontrado; //Bandera
    private javax.swing.JButton[][] Cuadro; // se declara matriz de botones
    
    
    ArrayList<jugador> listaJugador = new ArrayList<>();
    ArrayList<partidas> historialPartidas = new ArrayList<>();
    ArrayList<ConstructorCoo> Coo = new ArrayList<>(); //ArrayList con las 50 coordenadas
    ArrayList<ConstructorCoo> ListaDeJuego = new ArrayList(); //ArrayList con solo 10 coordenadas para usar en el juego
    manejoArchivos ArchivoES = new manejoArchivos();
    
    
    
    public jugar(ArrayList<jugador> listaJugador, ArrayList<ConstructorCoo> cord,  ArrayList<partidas> historialPartidas,String nombre,int edad) {
        initComponents();
        //Se incian las variables
        this.setLocationRelativeTo(null);
        this.jugadorActual = nombre;
        this.edadActual = edad;
        this.intentos = 10;
        this.barcosRes = 10;
        this.coorEncontrada = false;
        this.bEncontrado = false;
        //Se inicializan los ArrayList
        this.listaJugador = listaJugador;
        this.historialPartidas = historialPartidas;
        this.Coo = cord;
        //Se inician los label en su valor inicial
        numIntentos.setText(String.valueOf(intentos));
        numBarcosEnemigos.setText(String.valueOf(barcosRes));
        
        setMatrix(); //Funcion para generar la funcion en el constructor
        setListaDeJuego();
    }
    //Funcion que selecciona un grupo de 10 coordenadas de forma aleatoria para usar en el juego
    public void setListaDeJuego(){
        int min = 1, max = 5; //Limites superiores e inferiores de los numeros a randomizar
        Random tlr = new Random();
        int randomNum = tlr.nextInt(max) + min;
        //Copia las posiciones de Coo a ListaDeJuego segun el numero obtenido
        switch(randomNum){
            case 1:
                for(int i = 0; i <= 9; i++){
                    ListaDeJuego.add(Coo.get(i));
                }
                break;
            case 2:
                for(int i = 10; i <= 19; i++){
                    ListaDeJuego.add(Coo.get(i));
                }
                break;
            case 3:
                for(int i = 20; i <= 29; i++){
                    ListaDeJuego.add(Coo.get(i));
                }
                break;
            case 4:
                for(int i = 30; i <= 49; i++){
                    ListaDeJuego.add(Coo.get(i));
                }
                break;
            case 5:
                for(int i = 40; i <= 49; i++){
                    ListaDeJuego.add(Coo.get(i));
                }
                break;
        }
        //Muestra en consola las coordenadas elegidas
        for(int i = 0; i <= 9; i++){
                    System.out.println(ListaDeJuego.get(i).getX1()+"/"+ListaDeJuego.get(i).getY1()+"/"+ListaDeJuego.get(i).getX2()+"/"+ListaDeJuego.get(i).getY2()+"/"+ListaDeJuego.get(i).getX3()+"/"+ListaDeJuego.get(i).getY3());
                }
    }
    
    //Crea, dibuja y controla la matriz de botones
    public void setMatrix(){
        //variables auxiliares
        int x = 10;
        int y = 10;
        Cuadro = new javax.swing.JButton[Filas][Columnas]; //define tamanio
        //Pinta los botones de la matriz en el panel
        for(int i = 0; i < Filas; i++){
            for(int j = 0; j < Columnas; j++){
                Cuadro[i][j] = new javax.swing.JButton();
                Cuadro[i][j].setBounds(x, y, 50, 50);
                Cuadro[i][j].setToolTipText(String.valueOf(i)+","+String.valueOf(j)); 
                
                EventoClick ev = new EventoClick();
                
                Cuadro[i][j].addActionListener(ev);
                panButton.add(Cuadro[i][j]);
                x += 50;
              
            }
            x = 10;
            y += 50;
        }
    }
    
    public void guardarScore(int pos){
        
        for (int i = 0; i < listaJugador.size(); i++) {
             
              String nombreI = listaJugador.get(i).getNombre();
              
              for (int j = 0; j < historialPartidas.size(); j++){
                  
                  if(historialPartidas.get(j).getNombre().equals(nombreI) && historialPartidas.get(j).getNumPartida() == pos){
                    
                    int edadI = listaJugador.get(i).getEdad();
                    int jGanadosI = listaJugador.get(i).getjGanados() + historialPartidas.get(j).getGano();
                    int jPerdidosI = listaJugador.get(i).getjPerdidos() + historialPartidas.get(j).getPerdio();
                    int tJuegosI = jGanadosI + jPerdidosI;         
                    jugador insertar = new jugador(nombreI, edadI, jGanadosI, jPerdidosI, tJuegosI);
                    listaJugador.set(i,insertar);
                    ArchivoES.escribirJugador(listaJugador);
                  }
                  
                }
              
            }
    }
    
    
    //Contiene la acciones que realiza el programa al presionar el boton
    private class EventoClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int nump = 0;
            for(int k = 0; k < historialPartidas.size(); k ++ ){
                if(jugadorActual.equals(historialPartidas.get(k).getNombre())){
                    nump++;
                }
            }
            for(int i = 0; i < Filas; i++){
                for(int j = 0; j < Columnas; j++){
                    if(e.getSource().equals(Cuadro[i][j])){
                        BuscaCoordenada(i,j);
                        if(coorEncontrada == true){
                            Cuadro[i][j].removeActionListener(this); //quita el ActionListener, volver a presionar sobre el boton ya no tiene efecto
                            Cuadro[i][j].setBackground(Color.red);
                            if(bEncontrado == true){
                                numBarcosEnemigos.setText(String.valueOf(--barcosRes));
                                numIntentos.setText(String.valueOf(++intentos));
                            }
                            if(barcosRes < 1){//Si es TRUE, el juego a terminado, gano el jugador
                                partidas insertar3 = new partidas(jugadorActual, nump+1, 1, 0, 0);
                                historialPartidas.add(insertar3);
                                ArchivoES.escribirPartida(historialPartidas);
                                guardarScore(nump+1);
                                int desicionG = JOptionPane.showConfirmDialog(null, "JUGADOR: "+jugadorActual+"\nEDAD: "+edadActual+"\n\t\tFELICIDADES ¡HAS GANADO LA BATALLA!"+"\n¿Desea iniciar un nuevo juego? Si no, regresará al menú","Fin del juego", JOptionPane.YES_NO_OPTION);
                                switch(desicionG){
                                    case 0:  
                                        jugar nj = new jugar(listaJugador,Coo,historialPartidas,jugadorActual,edadActual);
                                        dispose();
                                        nj.setVisible(true);
                                        break;
                                    case 1: 
                                        sistemaDeJuego s = new sistemaDeJuego(listaJugador);
                                        dispose();
                                        s.setVisible(true);
                                        break;
                                    case -1:
                                        for(int k = 0; k < Filas; k++){
                                            for(int h = 0; h < Columnas; h++){
                                                Cuadro[k][h].setEnabled(false);
                                            }
                                        }
                                }
                            }
                        }else{
                            //Cuadro[i][j].setBackground(Color.black);
                            numIntentos.setText(String.valueOf(--intentos));
                            if(intentos <= 0){//Si es TRUE, el juego a terminado, perdio el jugador
                                
                                partidas insertar3 = new partidas(jugadorActual, nump+1, 0, 1, 10-barcosRes);
                                historialPartidas.add(insertar3);
                                ArchivoES.escribirPartida(historialPartidas);
                                guardarScore(nump+1);
                                int desicionP = JOptionPane.showConfirmDialog(null,"JUGADOR: "+jugadorActual+"\nEDAD: "+edadActual+"\n\t\tSENTIMOS INFORMARTE QUE HAS PERDIDO LA BATALLA"+"\n\t\tHas derribado "+(10-barcosRes)+" Barcos"+"\n\t\t¿Desea iniciar un nuevo juego?","Fin del Juego",JOptionPane.YES_NO_OPTION);
                                switch(desicionP){
                                    case 0:
                                        jugar nj = new jugar(listaJugador,Coo,historialPartidas,jugadorActual,edadActual);
                                        dispose();
                                        nj.setVisible(true);
                                        break;
                                    case 1:                             
                                        sistemaDeJuego s = new sistemaDeJuego(listaJugador);
                                        dispose();
                                        s.setVisible(true);
                                        break;
                                    case -1:
                                        for(int k = 0; k < Filas; k++){
                                            for(int h = 0; h < Columnas; h++){
                                                Cuadro[k][h].setEnabled(false);
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        }
        //Funcion que busca las coordenadas correspondientes al boton presionadas y actualiza las banderas
        public void BuscaCoordenada(int i, int j){
            String[] c = Cuadro[i][j].getToolTipText().split(",");
            int x = Integer.parseInt(c[0]);
            int y = Integer.parseInt(c[1]);
            
            bEncontrado = false;
            coorEncontrada = false;
            
            for(int k = 0; k < 10; k++){
                if((ListaDeJuego.get(k).getX1() == x && ListaDeJuego.get(k).getY1() == y) || (ListaDeJuego.get(k).getX2() == x && ListaDeJuego.get(k).getY2() == y) || (ListaDeJuego.get(k).getX3() == x && ListaDeJuego.get(k).getY3() == y)){
                    if(ListaDeJuego.get(k).getX1() == x && ListaDeJuego.get(k).getY1() == y){
                        ListaDeJuego.get(k).xy1Encontrado();
                        coorEncontrada = true;
                        //System.out.println("aver1: "+ListaDeJuego.get(k).xy1Estado()); //Muestra en consola el estado de la bandera
                    } 
                    if(ListaDeJuego.get(k).getX2() == x && ListaDeJuego.get(k).getY2() == y){
                        ListaDeJuego.get(k).xy2Encontrado();
                        coorEncontrada = true;
                        //System.out.println("aver2: "+ListaDeJuego.get(k).xy2Estado()); //Muestra en consola el estado de la bandera
                    } 
                    if(ListaDeJuego.get(k).getX3() == x && ListaDeJuego.get(k).getY3() == y){
                        ListaDeJuego.get(k).xy3Encontrado();
                        coorEncontrada = true;    
                        //System.out.println("aver3: "+ListaDeJuego.get(k).xy3Estado());
                    }
                    if((ListaDeJuego.get(k).xy1Estado() == true) && (ListaDeJuego.get(k).xy2Estado() == true) && (ListaDeJuego.get(k).xy3Estado() == true)){
                        ListaDeJuego.get(k).barcoEncontrado();
                        //System.out.println("averESTADO: "+ListaDeJuego.get(k).getEstado()); //Muestra en consola el estado de la bandera
                        bEncontrado = true;
                    }
                }
            }
       }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panButton = new javax.swing.JPanel();
        salir_jugar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        numBarcosEnemigos = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        numIntentos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(600, 600));

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BATTLESHIP FCC");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        panButton.setBackground(new java.awt.Color(0, 0, 204));
        panButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panButton.setPreferredSize(new java.awt.Dimension(520, 500));

        javax.swing.GroupLayout panButtonLayout = new javax.swing.GroupLayout(panButton);
        panButton.setLayout(panButtonLayout);
        panButtonLayout.setHorizontalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );
        panButtonLayout.setVerticalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        salir_jugar.setText("Regresar al menú");
        salir_jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salir_jugarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Barcos Enemigos \nRestantes:");

        numBarcosEnemigos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        numBarcosEnemigos.setForeground(new java.awt.Color(0, 204, 0));
        numBarcosEnemigos.setText("--");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(63, 63, 63))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(numBarcosEnemigos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(numBarcosEnemigos)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Intentos:");

        numIntentos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        numIntentos.setForeground(new java.awt.Color(255, 0, 0));
        numIntentos.setText("--");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(numIntentos)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numIntentos)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(salir_jugar)))
                .addGap(18, 23, Short.MAX_VALUE)
                .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salir_jugar)
                        .addGap(71, 71, 71))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salir_jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir_jugarActionPerformed
        // TODO add your handling code here:
        if(barcosRes < 1 || intentos <= 0){
            sistemaDeJuego s = new sistemaDeJuego(listaJugador);
            dispose();
            s.setVisible(true);
        }else{
            int dp = JOptionPane.showConfirmDialog(null,"¿Seguro que quieres regresar al menu?"+"\nTu progreso no se guardará","Fin del Juego",JOptionPane.YES_NO_OPTION);
            if(dp == 0){
                sistemaDeJuego s = new sistemaDeJuego(listaJugador);
                dispose();
                s.setVisible(true);
            }
        }
    }//GEN-LAST:event_salir_jugarActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new jugar().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel numBarcosEnemigos;
    private javax.swing.JLabel numIntentos;
    private javax.swing.JPanel panButton;
    private javax.swing.JButton salir_jugar;
    // End of variables declaration//GEN-END:variables
}
