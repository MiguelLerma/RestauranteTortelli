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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import restaurantetorteli.Empleado;
import restaurantetorteli.Mesa;
import restaurantetorteli.Pedido;
import restaurantetorteli.Producto;
import restaurantetorteli.ProductoPedido;
import restaurantetorteli.ProductoPedidoPK;
import restaurantetorteli.RenderTabla;

/**
 *
 * @author Alejandro
 */
public class ModificarPedido extends javax.swing.JFrame {

             List<Producto> productos;

             String mesa = "";
             
             ArrayList<Producto> listaProductosSeleccionados = new ArrayList<>();
             ArrayList<Integer> listaCantidades = new ArrayList<>();
             ArrayList<Producto> listaViejaProductos = new ArrayList<>();
            ArrayList<Integer> listaCantidadesViejas = new ArrayList<>();

             

             int idEmpleadoGlobal = 0;
             int idPedidoModificar = 0;

            
             
             
    /**
     * Creates new form CrearPedido
     */
    public ModificarPedido(int idEmpleado, int idPedidoModificar) throws IOException {
        initComponents();
       llenarTablaProductos(tablaProductos);
       this.idEmpleadoGlobal = idEmpleado;
       this.idPedidoModificar = idPedidoModificar;

       
       
       idPedidoTF.setText(Integer.toString(idPedidoModificar));
       
 
        listaMesas.setModel(new javax.swing.DefaultComboBoxModel(llenarMesas()));
        
         
            

            
             
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestauranteTorteliPU");//PruebaJPAPU es el nombre de nuestra unidad de persistencia
            
            PedidoJpaController dao = new PedidoJpaController(emf);//Creamos un controlador de personal
            ProductoJpaController controProdu = new ProductoJpaController(emf);//Creamos un controlador de personal
            MesaJpaController controMesa = new MesaJpaController(emf);
            ProductoPedidoJpaController produtoPedidoJPA = new ProductoPedidoJpaController(emf);
            ProductoPedido productoPedido = new  ProductoPedido();
            ProductoPedidoPK productoPedidoPK = new ProductoPedidoPK();
            
            Pedido pedido = new Pedido();
            Producto producto = new Producto();
            Empleado empleado = new Empleado();
           
            
            
           pedido = dao.findPedido(idPedidoModificar);
           
           Date date = pedido.getHorapedido();
                  nombreEmpleado.setText(pedido.getEmpleadoIdempleado().getNombre());

           TVHora.setText(date.getHours()+":"+date.getMinutes());

            String[] arreglo = llenarMesas();
              for (int i = 0; i < arreglo.length; i++) {
            if(pedido.getMesaNumeromesa().getNumeromesa()== Integer.parseInt(arreglo[i])){
                listaMesas.setSelectedIndex(i);
            }
        }
              
           
           
           
           List<ProductoPedido> listaProductos = produtoPedidoJPA.findProductoPedidoEntities();
           
            
           for (int i = 0; i < listaProductos.size(); i++) {
               
             if (listaProductos.get(i).getPedido().getIdpedido() == idPedidoModificar){
                 
                 listaProductosSeleccionados.add(controProdu.findProducto(listaProductos.get(i).getProducto().getProductoid()));
                 listaCantidades.add(listaProductos.get(i).getCantidad());
                 
             }
            
        }
           
           for (int i = 0; i < listaProductos.size(); i++) {
               
             if (listaProductos.get(i).getPedido().getIdpedido() == idPedidoModificar){
                 
                 listaViejaProductos.add(controProdu.findProducto(listaProductos.get(i).getProducto().getProductoid()));
                 
             }
            
        }
           
         
           
           
            
          Object listaItems[][]  = new Object[listaProductosSeleccionados.size()][5];
        for (int i = 0; i < listaProductosSeleccionados.size(); i++) {
            
             
            listaItems[i][0] = listaProductosSeleccionados.get(i).getProductoid();
            listaItems[i][1] = listaProductosSeleccionados.get(i).getNombreproducto();
            listaItems[i][2] = listaProductosSeleccionados.get(i).getPrecio();
            listaItems[i][3] = listaProductosSeleccionados.get(i).getDescripcion();
            listaItems[i][4] = listaCantidades.get(i);

        }
        String columna[] = new String[] {"ID", "Nombre", "Precio", "Descripcion","Cantidad"};
        emf.close();
        
        DefaultTableModel modelo = new DefaultTableModel(listaItems, columna);
        
        tablaProductosSeleccionados.setModel(modelo);

    
            
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        botonAnadir = new javax.swing.JButton();
        nombreEmpleado = new javax.swing.JLabel();
        listaMesas = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductosSeleccionados = new javax.swing.JTable();
        eliminarPlato = new javax.swing.JButton();
        cantidadProducto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        botonTerminarPedido = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        idPedidoTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TVHora = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btonUltimoPedido = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 86, 443, 316));

        botonAnadir.setText("Añadir Plato");
        botonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirActionPerformed(evt);
            }
        });
        jPanel1.add(botonAnadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 420, -1, -1));

        nombreEmpleado.setText("Nombre Empleado");
        jPanel1.add(nombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 26, -1, -1));

        listaMesas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        listaMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaMesasActionPerformed(evt);
            }
        });
        jPanel1.add(listaMesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 54, -1, -1));

        tablaProductosSeleccionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Precio", "Descripcion", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaProductosSeleccionados);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 466, -1, 108));

        eliminarPlato.setText("Eliminar Plato");
        eliminarPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPlatoActionPerformed(evt);
            }
        });
        jPanel1.add(eliminarPlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 586, -1, -1));
        jPanel1.add(cantidadProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 420, 66, -1));

        jLabel1.setText("Cantidad ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 426, -1, -1));

        botonTerminarPedido.setText("Terminar Pedido");
        botonTerminarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTerminarPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(botonTerminarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 497, -1, -1));

        jLabel2.setText("ID Pedido");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 26, -1, -1));

        idPedidoTF.setEditable(false);
        jPanel1.add(idPedidoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 20, 82, -1));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 54, 35, 6));

        jLabel4.setText("Hora");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 54, -1, -1));

        TVHora.setText("jLabel5");
        jPanel1.add(TVHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 54, -1, -1));

        jLabel5.setText("Mesa");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 59, -1, -1));

        jLabel6.setText("Empleado");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 26, -1, -1));

        btonUltimoPedido.setText("Entregar Ultimo Plato");
        btonUltimoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonUltimoPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(btonUltimoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 420, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Verde-Luz-Sólido-Pintura.jpg"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirActionPerformed

        
        
    if(tablaProductos.getSelectedRow() == -1){
               
                
                   JOptionPane.showMessageDialog(null, "Seleccione un producto");

                   return;
            }


        if(cantidadProducto.getText().toString().equalsIgnoreCase("")){
               
                
                   JOptionPane.showMessageDialog(null, "Ingrese una cantidad");

                   return;
            }
        
        
         
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestauranteTorteliPU");
        ProductoJpaController dao = new ProductoJpaController(emf);
        
        
        
        for (int i = 0; i < listaProductosSeleccionados.size(); i++) {
            
            
            if (listaProductosSeleccionados.get(i).getProductoid() == productos.get(tablaProductos.getSelectedRow()).getProductoid() ){
                
                JOptionPane.showMessageDialog(null, "Este producto ya existe en la lista");
                return;
            }
        }
        
        
                listaProductosSeleccionados.add(dao.findProducto(productos.get(tablaProductos.getSelectedRow()).getProductoid()));

                listaCantidades.add(Integer.parseInt(cantidadProducto.getText()));
        
          Object listaItems[][]  = new Object[listaProductosSeleccionados.size()][5];
        for (int i = 0; i < listaProductosSeleccionados.size(); i++) {
            
             
            listaItems[i][0] = listaProductosSeleccionados.get(i).getProductoid();
            listaItems[i][1] = listaProductosSeleccionados.get(i).getNombreproducto();
            listaItems[i][2] = listaProductosSeleccionados.get(i).getPrecio();
            listaItems[i][3] = listaProductosSeleccionados.get(i).getDescripcion();
            listaItems[i][4] = listaCantidades.get(i);

        }
        String columna[] = new String[] {"ID", "Nombre", "Precio", "Descripcion","Cantidad"};
        emf.close();
        
        DefaultTableModel modelo = new DefaultTableModel(listaItems, columna);
        
        tablaProductosSeleccionados.setModel(modelo);

    
        
        
        
        
    }//GEN-LAST:event_botonAnadirActionPerformed

    private void listaMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listaMesasActionPerformed

    private void eliminarPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPlatoActionPerformed
        // TODO add your handling code here:
        
        
            if(tablaProductosSeleccionados.getSelectedRow() == -1){
               
                
                   JOptionPane.showMessageDialog(null, "Seleccione un producto para eliminar");

                   return;
            }


         
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestauranteTorteliPU");
        ProductoJpaController dao = new ProductoJpaController(emf);
        
        
              //  listaProductosSeleccionados.add(dao.findProducto(listaProductosSeleccionados.get(tablaProductos.getSelectedRow()).getProductoid()));

        listaProductosSeleccionados.remove(tablaProductosSeleccionados.getSelectedRow());
        listaCantidades.remove(tablaProductosSeleccionados.getSelectedRow());

          Object listaItems[][]  = new Object[listaProductosSeleccionados.size()][5];
        for (int i = 0; i < listaProductosSeleccionados.size(); i++) {
            
             
            listaItems[i][0] = listaProductosSeleccionados.get(i).getProductoid();
            listaItems[i][1] = listaProductosSeleccionados.get(i).getNombreproducto();
            listaItems[i][2] = listaProductosSeleccionados.get(i).getPrecio();
            listaItems[i][3] = listaProductosSeleccionados.get(i).getDescripcion();
            listaItems[i][4] = listaCantidades.get(i);

            
        }
        String columna[] = new String[] {"ID", "Nombre", "Precio", "Descripcion","Cantidad"};
        emf.close();
        
        DefaultTableModel modelo = new DefaultTableModel(listaItems, columna);
        
        tablaProductosSeleccionados.setModel(modelo);

    
        
        
    }//GEN-LAST:event_eliminarPlatoActionPerformed

    private void botonTerminarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTerminarPedidoActionPerformed
        // TODO add your handling code here:
        
        
        
        
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
            
            
            pedido = dao.findPedido(Integer.parseInt(idPedidoTF.getText()));
            
            
            try{
                
                
            empleado = controEmple.findEmpleado(idEmpleadoGlobal);
            mesa = controMesa.findMesa(Integer.parseInt(listaMesas.getSelectedItem().toString()));

            
            
            
              
            
            

            pedido.setMesaNumeromesa(mesa);
            pedido.setEmpleadoIdempleado(empleado);
            
            pedido.setIdpedido(Integer.parseInt(idPedidoTF.getText().toString()));
            
            pedido.setTipopedido("Normal");
            
            
            Date date = new Date();
            pedido.setHoraentrega(date);
           // pedido.setHorapedido(date);
            
            
            }catch(NumberFormatException e){
                
               JOptionPane.showMessageDialog(null, "Los campos ID esperan un numero");

            }catch(Exception e){
                
              JOptionPane.showMessageDialog(null, "Algun elemento tratado de ingresar no existe");

            }
             
            
            
            try {
                
                dao.edit(pedido);

                for (int i = 0; i < listaViejaProductos.size(); i++) {
                    
                     ProductoPedidoPK productoPedidoPKViejo = new ProductoPedidoPK();
            
                     productoPedidoPKViejo.setPedidoIdpedido(idPedidoModificar);
                     productoPedidoPKViejo.setProductoProductoid(listaViejaProductos.get(i).getProductoid());
                    produtoPedidoJPA.destroy(productoPedidoPKViejo);
                     
                }
                
               
              
            }catch(Exception u){
                
                u.printStackTrace();
            }
                try{
                    
                   
             for (int i = 0; i < listaProductosSeleccionados.size(); i++) {
                    
                    productoPedidoPK.setPedidoIdpedido(Integer.parseInt(idPedidoTF.getText().toString()));
                    productoPedidoPK.setProductoProductoid(listaProductosSeleccionados.get(i).getProductoid());
                    productoPedido.setCantidad(listaCantidades.get(i));
                    productoPedido.setPedido(pedido);
                    productoPedido.setProductoPedidoPK(productoPedidoPK);
                    productoPedido.setProducto(listaProductosSeleccionados.get(i));
                    
                    
                    produtoPedidoJPA.create(productoPedido);
                    
                   
                     
                     
                }
            
            
             JOptionPane.showMessageDialog(null, "Pedido modificado con exito");

             this.setVisible(false);
             
             
                } catch (EntityNotFoundException ex) {
                 JOptionPane.showMessageDialog(null, "Error al actualizar");
        
            }catch(NumberFormatException e){
                
               JOptionPane.showMessageDialog(null, "Error al actualizar");

            }
            
             catch (Exception ex) {
                //Logger.getLogger(Tortteli.class.getName()).log(Level.SEVERE, null, ex);
                //System.out.println(ex);
                ex.printStackTrace();
            }
            //Al ejecutar el método puede que salte una excepcion por lo que es importante lanzarla desde el main con throws Exception
            emf.close();
            
    }//GEN-LAST:event_botonTerminarPedidoActionPerformed

    private void btonUltimoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonUltimoPedidoActionPerformed
        // TODO add your handling code here:
        
        
        
        
        
        
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
            
            
            pedido = dao.findPedido(Integer.parseInt(idPedidoTF.getText()));
            
            
            try{
                
                
            empleado = controEmple.findEmpleado(idEmpleadoGlobal);
            mesa = controMesa.findMesa(Integer.parseInt(listaMesas.getSelectedItem().toString()));

            
            
            
              
            
            

            pedido.setMesaNumeromesa(mesa);
            pedido.setEmpleadoIdempleado(empleado);
            
            pedido.setIdpedido(Integer.parseInt(idPedidoTF.getText().toString()));
            
            pedido.setTipopedido("Normal");
            
            
            Date date = new Date();
            pedido.setHoraentrega(date);
           // pedido.setHorapedido(date);
            
            
            }catch(NumberFormatException e){
                
               JOptionPane.showMessageDialog(null, "Los campos ID esperan un numero");

            }catch(Exception e){
                
              JOptionPane.showMessageDialog(null, "Algun elemento tratado de ingresar no existe");

            }
             try {  
            
                                 dao.edit(pedido);

            JOptionPane.showMessageDialog(null, "Pedido modificado con exito");

            
             }catch(EntityNotFoundException t){
                 
                 JOptionPane.showMessageDialog(null, "Algun elemento tratado de ingresar no existe");

            } catch (Exception ex) {
                ex.printStackTrace();
              JOptionPane.showMessageDialog(null, "Error al crear el pedido, puede que ya exista o el empleado,mesa o producto no existan");
        }
            
            
    }//GEN-LAST:event_btonUltimoPedidoActionPerformed

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
            java.util.logging.Logger.getLogger(ModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ModificarPedido(12,3).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ModificarPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    
public void llenarTablaProductos(JTable tabla) throws IOException{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestauranteTorteliPU");
        ProductoJpaController dao = new ProductoJpaController(emf);
        
       productos = dao.findProductoEntities();
        
          Object listaItems[][]  = new Object[productos.size()][5];
          
          
      
          
          
          
        for (int i = 0; i < productos.size(); i++) {
            
             
            listaItems[i][0] = productos.get(i).getProductoid();
            listaItems[i][1] = productos.get(i).getNombreproducto();
            listaItems[i][2] = productos.get(i).getPrecio();
            listaItems[i][3] = productos.get(i).getDescripcion();
            
            String imagenGlobal = productos.get(i).getFoto();
      byte[] bytes= new sun.misc.BASE64Decoder().decodeBuffer(imagenGlobal); 
      
       
        byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(imagenGlobal);
BufferedImage image = ImageIO.read(new ByteArrayInputStream(btDataFile));
EncriptarC objeto = new EncriptarC();
objeto.getScaledImage(image,100,100);
ImageIcon icono = new ImageIcon(objeto.getScaledImage(image,100,100)); //Ojo aquí hago referencia a una imagen que guarde dentro de un paquete en mi proyecto.


            
            
            listaItems[i][4] = new JLabel(icono);

            
            
        }
        String columna[] = new String[] {"ID", "Nombre", "Precio", "Descripcion","Imagen"};
        emf.close();
        
        tabla.setDefaultRenderer(Object.class,new RenderTabla());
        
        
        
        DefaultTableModel modelo = new DefaultTableModel(listaItems, columna);
        
       
        tabla.setModel(modelo);

        tabla.setRowHeight(100); 

    
}



public String[] llenarMesas(){
    
    
            
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestauranteTorteliPU");
        MesaJpaController dao = new MesaJpaController(emf);
        
           List<Mesa> lista = dao.findMesaEntities();
           
           
           String[] listaMesas = new String[lista.size()];
           
           for (int i = 0; i < lista.size(); i++) {
               
               listaMesas[i] = Integer.toString(lista.get(i).getNumeromesa());
        
    }
           
           
    
           return listaMesas;
    
    
}



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TVHora;
    private javax.swing.JButton botonAnadir;
    private javax.swing.JButton botonTerminarPedido;
    private javax.swing.JButton btonUltimoPedido;
    private javax.swing.JTextField cantidadProducto;
    private javax.swing.JButton eliminarPlato;
    private javax.swing.JTextField idPedidoTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox listaMesas;
    private javax.swing.JLabel nombreEmpleado;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTable tablaProductosSeleccionados;
    // End of variables declaration//GEN-END:variables
}
