package Pokemon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 *
 * @author Yoel Cano
 */
public class VentanaPokedex extends javax.swing.JFrame {

    
    private BufferedImage buffer;
    private Image imagenPokemons;
    private Image imagenFondo;
    private int contador = 0;
    private int ancho = 200, alto = 200;
    
    // conectamos a la base de datos

    private Statement estado;
    private ResultSet resultadoConsulta;
    private Connection conexion;
    
    
    ////////////////////////////////////////
    
    //hashmap para almacenar el resultado de la consulta
    HashMap <String,Pokemon> listaPokemons = new HashMap();
    
    /**
     * Creates new form VentanaPokedex
     */
    private void dibujaElPokemonQueEstaEnLaPosicion (int posicion){
        int fila = posicion / 31;
        int columna = posicion % 31;
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        //borro lo que hubiera
        g2.setColor(Color.black);
        g2.fillRect(0, 0, alto, ancho);
        g2.drawImage(imagenPokemons,
                0,
                0,
                ancho,
                alto,
                96*columna,
                96*fila,
                96*columna + 96,
                96*fila + 96,
                null);
        repaint();
        escribeDatos();
    }
    
    
    
    
    private void escribeDatos(){
        Pokemon p = listaPokemons.get(String.valueOf(contador+1));
        if (p != null){
            jLabel1.setText(p.nombre);
            jLabel3.setText(p.species);
            jLabel6.setText(p.weight);
            jLabel8.setText(p.height);
            jLabel10.setText(p.id);
            jLabel16.setText("000"+p.idNo);
            jLabel17.setText(p.ExpPoints);
            jLabel18.setText(p.ExpNextLvl);
       }
        else {
            jLabel1.setText("???");
            jLabel3.setText("???");
            jLabel6.setText("???");
            jLabel8.setText("???");
            jLabel10.setText("???");
            jLabel16.setText("???");
            jLabel17.setText("???");
            jLabel18.setText("???");
        }
    }
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer, 0, 0,alto,ancho, null);
    }
    
   
    
    public VentanaPokedex() {
        initComponents();
        try {
            imagenPokemons = ImageIO.read(getClass().getResource("black-white.png"));
            imagenFondo = ImageIO.read(getClass().getResource("pokedex1.png"));
        } catch (IOException ex) {
            Logger.getLogger(VentanaPokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        buffer =(BufferedImage) jPanel1.createImage(ancho,alto);
        Graphics2D g2 = buffer.createGraphics();
        
        
        
        
        //conexion a la base de datos//////////////////
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","root","");
            estado = conexion.createStatement();
            resultadoConsulta = estado.executeQuery("Select * from pokemon");
            //cargo el resultado de la query en mi hashmap
            while (resultadoConsulta.next()){
                Pokemon p = new Pokemon();
                p.nombre = resultadoConsulta.getString(2);
                p.generation_id = resultadoConsulta.getInt(5);
                p.evolution_chain_id = resultadoConsulta.getInt(6);
                p.species = resultadoConsulta.getString(12);
                p.weight = resultadoConsulta.getString(11);
                p.height = resultadoConsulta.getString(10);
                p.id = resultadoConsulta.getString(1);
                p.idNo = resultadoConsulta.getString(20);
                p.ExpPoints = resultadoConsulta.getString(18);
                p.ExpNextLvl = resultadoConsulta.getString(17);
                
                listaPokemons.put(resultadoConsulta.getString(1), p);
            }
        }
        catch (Exception e){
        }
        //////////////////////////////////////////////
        dibujaElPokemonQueEstaEnLaPosicion(0);
        
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 0, 0));
        getContentPane().setLayout(null);

        jPanel1.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 201, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 120, 201, 200);

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 60, 160, 29);

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dex No.");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(240, 70, 110, 40);
        jLabel2.getAccessibleContext().setAccessibleName("jLabel");

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Agency FB", 0, 28)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(370, 110, 130, 29);

        jLabel4.setFont(new java.awt.Font("Agency FB", 1, 28)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Specie");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(240, 106, 120, 34);

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Weight");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(240, 140, 110, 34);

        jLabel6.setFont(new java.awt.Font("Agency FB", 0, 28)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(370, 140, 130, 30);

        jLabel7.setFont(new java.awt.Font("Agency FB", 1, 28)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Height");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(240, 170, 90, 34);
        jLabel7.getAccessibleContext().setAccessibleName("Height:");

        jLabel8.setFont(new java.awt.Font("Agency FB", 0, 28)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel8);
        jLabel8.setBounds(370, 170, 130, 30);

        jLabel10.setFont(new java.awt.Font("Agency FB", 0, 28)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel10);
        jLabel10.setBounds(370, 80, 130, 30);
        jLabel10.getAccessibleContext().setAccessibleName("id");

        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 28)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("PokeDam");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(90, 10, 130, 40);

        jLabel12.setFont(new java.awt.Font("Agency FB", 1, 28)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("To Next Lvl.");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(240, 290, 120, 50);

        jLabel13.setFont(new java.awt.Font("Agency FB", 1, 28)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID No.");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(240, 200, 70, 40);

        jLabel14.setFont(new java.awt.Font("Agency FB", 1, 28)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Exp. Points");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(240, 230, 120, 40);

        jLabel17.setFont(new java.awt.Font("Agency FB", 0, 28)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel17);
        jLabel17.setBounds(360, 270, 130, 30);

        jLabel16.setFont(new java.awt.Font("Agency FB", 0, 28)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel16);
        jLabel16.setBounds(370, 210, 130, 30);

        jLabel18.setFont(new java.awt.Font("Agency FB", 0, 28)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel18);
        jLabel18.setBounds(360, 330, 130, 40);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 153));
        jButton1.setText("izquierda");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(-1, 380, 220, 30);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 153, 153));
        jButton2.setText("derecha");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(220, 380, 290, 31);

        jLabel9.setIcon(new javax.swing.ImageIcon("D:\\Yoel Cano Martínez\\Programación\\ImgPokedexFinal.png")); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(0, 0, 510, 380);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        contador++;
        if (contador > 507) {contador = 0;}
        dibujaElPokemonQueEstaEnLaPosicion(contador);
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        contador--;
        if (contador < 0) {contador = 0;}
        dibujaElPokemonQueEstaEnLaPosicion(contador);

    }//GEN-LAST:event_jButton1MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPokedex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
