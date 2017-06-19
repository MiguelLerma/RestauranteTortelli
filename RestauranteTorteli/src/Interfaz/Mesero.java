/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import JPAControl.EmpleadoJpaController;
import JPAControl.MesaJpaController;
import JPAControl.PedidoJpaController;
import JPAControl.ProductoJpaController;
import JPAControl.ProductoPedidoJpaController;
import Log.EncriptarC;
import Log.Log;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import restaurantetorteli.Empleado;
import restaurantetorteli.Mesa;
import restaurantetorteli.Pedido;
import restaurantetorteli.Producto;
import restaurantetorteli.ProductoPedido;
import restaurantetorteli.ProductoPedidoPK;
import restaurantetorteli.RenderTabla;
import Interfaz.Reportes;

/**
 *
 * @author Alejandro
 */
public class Mesero extends javax.swing.JFrame {

                 List<Pedido> pedidosBuscados;
                 ArrayList<Pedido> pedidos = new ArrayList<>();

                 int idUsuarioI =0;
    /**
     * Creates new form Mesero
     */
    public Mesero(int idUsuario) throws IOException {
        initComponents();
        
        idUsuarioI = idUsuario;
         

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventanaModificarPedido = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        botonModificarPedidoSele = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ventanaEliminarPedido = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPedidosEliminar = new javax.swing.JTable();
        botonEliminarPedidoSele = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btonCrearPedido = new javax.swing.JButton();
        botonModificarPedido = new javax.swing.JButton();
        botonEliminarPedido = new javax.swing.JButton();
        botonModificarDatos = new javax.swing.JButton();
        botonsesionCerrar = new javax.swing.JButton();
        Reportes = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaPedidos);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 453, 168));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        jLabel3.setText("Modificar Pedido");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        botonModificarPedidoSele.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/realiza_pedido - copia 2.png"))); // NOI18N
        botonModificarPedidoSele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarPedidoSeleActionPerformed(evt);
            }
        });
        jPanel2.add(botonModificarPedidoSele, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 124, 123));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Verde-Luz-Sólido-Pintura.jpg"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 420));

        javax.swing.GroupLayout ventanaModificarPedidoLayout = new javax.swing.GroupLayout(ventanaModificarPedido.getContentPane());
        ventanaModificarPedido.getContentPane().setLayout(ventanaModificarPedidoLayout);
        ventanaModificarPedidoLayout.setHorizontalGroup(
            ventanaModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(ventanaModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ventanaModificarPedidoLayout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ventanaModificarPedidoLayout.setVerticalGroup(
            ventanaModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
            .addGroup(ventanaModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPedidosEliminar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaPedidosEliminar);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 80, 453, 168));

        botonEliminarPedidoSele.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/realiza_pedido - copia 3.png"))); // NOI18N
        botonEliminarPedidoSele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarPedidoSeleActionPerformed(evt);
            }
        });
        jPanel3.add(botonEliminarPedidoSele, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 266, 125, 121));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        jLabel5.setText("Eliminar Pedido");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 6, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Verde-Luz-Sólido-Pintura.jpg"))); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 420));

        javax.swing.GroupLayout ventanaEliminarPedidoLayout = new javax.swing.GroupLayout(ventanaEliminarPedido.getContentPane());
        ventanaEliminarPedido.getContentPane().setLayout(ventanaEliminarPedidoLayout);
        ventanaEliminarPedidoLayout.setHorizontalGroup(
            ventanaEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(ventanaEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ventanaEliminarPedidoLayout.setVerticalGroup(
            ventanaEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
            .addGroup(ventanaEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        jLabel1.setText("Mesero");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 6, -1, -1));

        btonCrearPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/realiza_pedido - copia 1.png"))); // NOI18N
        btonCrearPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonCrearPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(btonCrearPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 80, 124, 124));

        botonModificarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/realiza_pedido - copia 2.png"))); // NOI18N
        botonModificarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(botonModificarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 80, 124, 124));

        botonEliminarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/realiza_pedido - copia 3.png"))); // NOI18N
        botonEliminarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(botonEliminarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 243, 124, 126));

        botonModificarDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Información-Personal-icono - copia.png"))); // NOI18N
        botonModificarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarDatosActionPerformed(evt);
            }
        });
        jPanel1.add(botonModificarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 243, 124, 126));

        botonsesionCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CerrarSesion - copia.jpg"))); // NOI18N
        botonsesionCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonsesionCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(botonsesionCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 126, 119));

        Reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Reportes_2014.jpg"))); // NOI18N
        Reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportesActionPerformed(evt);
            }
        });
        jPanel1.add(Reportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 120, 120));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Verde-Luz-Sólido-Pintura.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 560));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btonCrearPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonCrearPedidoActionPerformed
       
        CrearPedido ventanaCrearPedido;
                     try {
                         ventanaCrearPedido = new CrearPedido(idUsuarioI);
                          ventanaCrearPedido.setEnabled(true);
                ventanaCrearPedido.setVisible(true);
                ventanaCrearPedido.pack();
                     } catch (IOException ex) {
                         Logger.getLogger(Mesero.class.getName()).log(Level.SEVERE, null, ex);
                     }
               

// TODO add your handling code here:
    }//GEN-LAST:event_btonCrearPedidoActionPerformed

    private void botonModificarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarPedidoActionPerformed
        // TODO add your handling code here:
        
        cargarPedidos();
        
         ventanaModificarPedido.setEnabled(true);
        ventanaModificarPedido.setVisible(true);
        ventanaModificarPedido.pack();
    }//GEN-LAST:event_botonModificarPedidoActionPerformed

    private void botonModificarPedidoSeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarPedidoSeleActionPerformed
if(tablaPedidos.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un pedido");
            return;
            
        }
        
        
        ventanaModificarPedido.setVisible(false);
                     try {
                         ModificarPedido modificar = new ModificarPedido(idUsuarioI, pedidos.get(tablaPedidos.getSelectedRow()).getIdpedido());
                         modificar.setVisible(true);
// TODO add your handling code here:
                     } catch (IOException ex) {
                         Logger.getLogger(Mesero.class.getName()).log(Level.SEVERE, null, ex);
                     }
    }//GEN-LAST:event_botonModificarPedidoSeleActionPerformed

    private void botonEliminarPedidoSeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarPedidoSeleActionPerformed
      
        if(tablaPedidosEliminar.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un pedido");
            return;
            
        }
        
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestauranteTorteliPU");//PruebaJPAPU es el nombre de nuestra unidad de persistencia
            
            PedidoJpaController dao = new PedidoJpaController(emf);//Creamos un controlador de personal
            
            EmpleadoJpaController controEmple = new EmpleadoJpaController(emf);
            ProductoJpaController controProdu = new ProductoJpaController(emf);//Creamos un controlador de personal
            MesaJpaController controMesa = new MesaJpaController(emf);
            ProductoPedidoJpaController produtoPedidoJPA = new ProductoPedidoJpaController(emf);
            ProductoPedido productoPedido = new  ProductoPedido();
            ProductoPedidoPK productoPedidoPK = new ProductoPedidoPK();
            
            Pedido pedido = new Pedido();
            Mesa mesa = new Mesa();
            Producto producto = new Producto();
            Empleado empleado = new Empleado();
           
            
            try{
                
             
                List<ProductoPedido> listaEliminar = produtoPedidoJPA.findProductoPedidoEntities();
                
                for (int i = 0; i < listaEliminar.size(); i++) {
                    
                    if(listaEliminar.get(i).getPedido().getIdpedido() == pedidos.get(tablaPedidosEliminar.getSelectedRow()).getIdpedido()){
                        
                            productoPedidoPK.setPedidoIdpedido(pedidos.get(tablaPedidosEliminar.getSelectedRow()).getIdpedido());
                            productoPedidoPK.setProductoProductoid(listaEliminar.get(i).getProducto().getProductoid());
                        produtoPedidoJPA.destroy(productoPedidoPK);
                            
                    }
                    
                }
                
                
                
             dao.destroy(pedidos.get(tablaPedidosEliminar.getSelectedRow()).getIdpedido());
                JOptionPane.showMessageDialog(null, "Pedido eliminado con exito");
                
                
                
                
                
                

            }catch(Exception e){
                e.printStackTrace();
                
            }
            
            
            
            pedidos.remove(tablaPedidosEliminar.getSelectedRow());
                     try {
                llenarTablaPedidos(tablaPedidosEliminar);
                         
                     } catch (IOException ex) {
                         Logger.getLogger(Mesero.class.getName()).log(Level.SEVERE, null, ex);
                     }
    }//GEN-LAST:event_botonEliminarPedidoSeleActionPerformed

    private void botonEliminarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarPedidoActionPerformed

        cargarPedidos();
// TODO add your handling code here:
        ventanaEliminarPedido.setEnabled(true);
        ventanaEliminarPedido.setVisible(true);
        ventanaEliminarPedido.pack();
    }//GEN-LAST:event_botonEliminarPedidoActionPerformed

    private void botonModificarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarDatosActionPerformed
     
        
                Gerente gerenteUI = new Gerente(0,idUsuarioI);
                
                gerenteUI.editarOtroEmpleado(idUsuarioI);
               /* gerenteUI.setEnabled(true);
                gerenteUI.setVisible(true);
                gerenteUI.pack();
                */

// TODO add your handling code here:
    }//GEN-LAST:event_botonModificarDatosActionPerformed

    private void botonsesionCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonsesionCerrarActionPerformed
                Log login = new Log();
                login.setEnabled(true);
                login.setVisible(true);
                login.pack();
                
                this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_botonsesionCerrarActionPerformed

    private void ReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportesActionPerformed
        Reportes reporte = new Reportes();
        reporte.setEnabled(true);
        reporte.setVisible(true);
        reporte.Reporte1.setEnabled(true);
        reporte.Reporte2.setEnabled(true);
        reporte.Reporte3.setEnabled(true);
        reporte.Reporte4.setEnabled(false);
        reporte.Reporte5.setEnabled(true);
        reporte.Reporte6.setEnabled(false);
    }//GEN-LAST:event_ReportesActionPerformed

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
            java.util.logging.Logger.getLogger(Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Mesero(12).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Mesero.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    
    
public void llenarTablaPedidos(JTable tabla) throws IOException{
    
         
          Object listaItems[][]  = new Object[pedidos.size()][5];
          
          
      
          
          
          
        for (int i = 0; i < pedidos.size(); i++) {
            
             
            listaItems[i][0] = pedidos.get(i).getIdpedido();
            listaItems[i][1] = pedidos.get(i).getEmpleadoIdempleado().getIdempleado();
            listaItems[i][2] = pedidos.get(i).getMesaNumeromesa().getNumeromesa();
            listaItems[i][3] = pedidos.get(i).getHorapedido().getHours()+":"+pedidos.get(i).getHorapedido().getMinutes();

           
            
            
        }
        String columna[] = new String[] {"ID Pedido", "Empleado", "Mesa", "Hora Pedido"};
        
        tabla.setDefaultRenderer(Object.class,new RenderTabla());
        
        
        
        DefaultTableModel modelo = new DefaultTableModel(listaItems, columna);
        
       
        tabla.setModel(modelo);


    
}


public void cargarPedidos(){
    
    pedidos.clear();
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestauranteTorteliPU");
        PedidoJpaController dao = new PedidoJpaController(emf);
        
        
       pedidosBuscados = dao.findPedidoEntities();
       
       
        for (int i = 0; i < pedidosBuscados.size(); i++) {
            
            if(pedidosBuscados.get(i).getEmpleadoIdempleado().getIdempleado() == idUsuarioI){
                
                
                pedidos.add(pedidosBuscados.get(i));
            }
            
        }
       
       
       
       
       
          emf.close();

        
                     try {
                         llenarTablaPedidos(tablaPedidos);
                                         llenarTablaPedidos(tablaPedidosEliminar);

                     } catch (IOException ex) {
                         Logger.getLogger(Mesero.class.getName()).log(Level.SEVERE, null, ex);
                     }
}



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Reportes;
    private javax.swing.JButton botonEliminarPedido;
    private javax.swing.JButton botonEliminarPedidoSele;
    private javax.swing.JButton botonModificarDatos;
    private javax.swing.JButton botonModificarPedido;
    private javax.swing.JButton botonModificarPedidoSele;
    private javax.swing.JButton botonsesionCerrar;
    private javax.swing.JButton btonCrearPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JTable tablaPedidosEliminar;
    private javax.swing.JFrame ventanaEliminarPedido;
    private javax.swing.JFrame ventanaModificarPedido;
    // End of variables declaration//GEN-END:variables
}
