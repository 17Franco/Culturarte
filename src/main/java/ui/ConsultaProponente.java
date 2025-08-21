
package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import logica.DTO.DTOProponente;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTORegistro_Aporte;
import logica.DTO.DTORegistro_Estado;
import logica.Fabrica;
import logica.IController;

public class ConsultaProponente extends javax.swing.JInternalFrame {
     private IController controller = Fabrica.getInstance();
  
    public ConsultaProponente() {
        initComponents();
        
       // PerfilProponente.setVisible(false);
        lblPropuestas.setVisible(false);
       // Propuestas.setVisible(false);
        jScrollPane1.setVisible(false);
       // jScrollPane2.setVisible(false);
        Proponentes.removeAllItems(); 
        Proponentes.addItem("SeleccionarUsuario"); 
        for (String u : controller.ListaProponentes()) {
            Proponentes.addItem(u); 
        }
        
        
        Proponentes.addActionListener(e -> {
            // filtrar cambios válidos
            String item = (String) Proponentes.getSelectedItem();
            if(item != null && controller.existe(item)){
                DTOProponente usr = controller.getDTOProponente(item);
                mostrarPerfilProponente(usr);
                // jScrollPane2.setVisible(true);
            }
        });
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Proponentes = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Propuestas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblPropuestas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblNick = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblBiografia = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblWeb = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(542, 622));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setText("Consulta Perfil Proponente");

        Proponentes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Proponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProponentesActionPerformed(evt);
            }
        });

        Propuestas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", null, null},
                {null, "", null, null},
                {null, "", null, null},
                {null, "", null, null},
                {null, "", null, null},
                {null, "", null, null},
                {null, "", null, null},
                {null, "", null, null}
            },
            new String [] {
                "Nombre", "MontoRecaudado", "Usuarios", "Estado"
            }
        ));
        jScrollPane1.setViewportView(Propuestas);

        jLabel2.setText("Proponentes");

        lblPropuestas.setText("Propuestas");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.GridLayout(0, 2));

        jLabel4.setText("NickName");
        jPanel1.add(jLabel4);
        jPanel1.add(lblNick);

        jLabel6.setText("Nombre");
        jPanel1.add(jLabel6);
        jPanel1.add(lblNombre);

        jLabel8.setText("Apellido");
        jPanel1.add(jLabel8);
        jPanel1.add(lblApellido);

        jLabel10.setText("Fecha");
        jPanel1.add(jLabel10);
        jPanel1.add(lblFecha);

        jLabel13.setText("Email");
        jPanel1.add(jLabel13);
        jPanel1.add(lblEmail);

        jLabel11.setText("Direccion");
        jPanel1.add(jLabel11);
        jPanel1.add(lblDireccion);

        jLabel16.setText("Biografia");
        jPanel1.add(jLabel16);
        jPanel1.add(lblBiografia);

        jLabel15.setText("Web");
        jPanel1.add(jLabel15);
        jPanel1.add(lblWeb);

        lblImagen.setText("No file");
        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblImagen.setMaximumSize(new java.awt.Dimension(135, 155));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblPropuestas)
                .addGap(193, 193, 193))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Proponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(184, 184, 184))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Proponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(lblPropuestas)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private int montoRecaudado(List<DTORegistro_Aporte> aportes){
        int aux=0;
        if(aportes.isEmpty()){
           for(DTORegistro_Aporte a: aportes){
               aux=aux+a.getMonto();
           }
        }
        return aux;
    }
    
    private List<String> colaboracionPropuesta(List<DTORegistro_Aporte> aportes){
        
        if(aportes.isEmpty()){
            List<String> aux=new ArrayList<>();
           for(DTORegistro_Aporte a: aportes){
               aux.add(a.getColaborador());
           }
           return aux;
        }
        return null;
    }
    private void mostrarPropuestas(String titulo, int monto, DTORegistro_Estado estado, List<String> usuarios){
    
    }
    private void mostrarPerfilProponente(DTOProponente usr) {

        lblNick.setText(usr.getNickname());
        lblNombre.setText(usr.getNombre());
        lblApellido.setText(usr.getApellido());
        lblFecha.setText(Integer.toString(usr.getFecha().getDay())+"/"+ Integer.toString(usr.getFecha().getMonth())+"/"+Integer.toString(usr.getFecha().getYear()));
        lblEmail.setText(usr.getEmail());
        lblDireccion.setText(usr.getDireccion());
        lblBiografia.setText(usr.getBiografia());
        lblWeb.setText(usr.getWebSite());

        ImageIcon icon=new ImageIcon(usr.getRutaImg());
        Image img =icon.getImage().getScaledInstance(lblImagen.getWidth(),lblImagen.getHeight(), Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));


        for(DTOPropuesta p: usr.getPropCreadas().values()){
            //System.out.println("jhola");
            List<String> colaboradores=colaboracionPropuesta(p.getAporte());
            int monto=montoRecaudado(p.getAporte());
            mostrarPropuestas(p.getTitulo(),monto,p.obtenerPrimero(),colaboradores);
        }
    }


    private void ProponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProponentesActionPerformed
         /* if(controller.existe((String) Proponentes.getSelectedItem())){
               DTOProponente usr=controller.getDTOProponente((String) Proponentes.getSelectedItem());
              mostrarPerfilProponente(usr);
          }*/
       
    }//GEN-LAST:event_ProponentesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Proponentes;
    private javax.swing.JTable Propuestas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblBiografia;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNick;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPropuestas;
    private javax.swing.JLabel lblWeb;
    // End of variables declaration//GEN-END:variables
}
