
package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import logica.DTO.DTOColaboracion;
import logica.DTO.DTOProponente;
import logica.DTO.DTOPropuesta;
import logica.Fabrica;
import logica.IController;
import logica._enum.Estado;

public class ConsultaProponente extends javax.swing.JInternalFrame {
     private IController controller = Fabrica.getInstance();
  
    public ConsultaProponente() {
        initComponents();
        
        Proponentes.removeAllItems(); 
        
        Proponentes.addItem("SeleccionarUsuario"); 
        for (String u : controller.ListaProponentes()) {
            Proponentes.addItem(u); 
        }
        
        
        Proponentes.addActionListener(e -> {
            limpiador();
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

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta Proponente");
        setPreferredSize(new java.awt.Dimension(490, 555));

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
                "Nombre", "Recaudacion", "Usuarios", "Estado"
            }
        ));
        Propuestas.setShowHorizontalLines(false);
        Propuestas.setShowVerticalLines(false);
        Propuestas.setIntercellSpacing(new java.awt.Dimension(0, 0));

        // Variable para guardar la fila sobre la que está el mouse
        final int[] hoveredRow = {-1};

        Propuestas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                hoveredRow[0] = Propuestas.rowAtPoint(e.getPoint());
                Propuestas.repaint();
            }
        });

        Propuestas.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (row == hoveredRow[0] && !isSelected) {
                    c.setBackground(new java.awt.Color(220, 220, 220)); // gris clarito al pasar el mouse
                } else if (isSelected) {
                    c.setBackground(new java.awt.Color(180, 200, 240)); // color para la fila seleccionada
                } else {
                    c.setBackground(java.awt.Color.WHITE); // fondo normal
                }
                //COLOR GRIS new java.awt.Color(220, 220, 220)
                return c;
            }
        });

        Propuestas.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

                lbl.setBorder(BorderFactory.createEmptyBorder()); // sin líneas
                //lbl.setHorizontalAlignment(CENTER);
                lbl.setBackground(Color.BLACK);// opcional, centrar texto
                lbl.setForeground(Color.WHITE);
                lbl.setFont(new java.awt.Font("Roboto", java.awt.Font.BOLD, 12));

                return lbl;
            }
        });

        Propuestas.setFont(new java.awt.Font("Roboto", java.awt.Font.PLAIN, 10));
        //Propuestas.setRowHeight(22);

        JTableHeader header = Propuestas.getTableHeader();

        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 25));
        jScrollPane1.setViewportView(Propuestas);

        jLabel2.setText("Proponentes");

        lblPropuestas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPropuestas.setText("Propuestas Creadas");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(127, 127, 127)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Proponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(192, 192, 192))))))
            .addComponent(lblPropuestas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void limpiador(){
        lblNick.setText("");
        lblNombre.setText("");
        lblApellido.setText("");
        lblFecha.setText("");
        lblEmail.setText("");
        lblImagen.setIcon(null);
        lblDireccion.setText("");
        lblBiografia.setText("");
        lblWeb.setText("");
        //DefaultTableModel modelo = (DefaultTableModel) Propuestas.getModel();
       // modelo.setRowCount(0);
    }
    
   
   private void mostrarPropuestas(String titulo, int monto, Estado estado, List<String> usuarios, DefaultTableModel modelo) {

    modelo.addRow(new Object[]{titulo, monto, "Colaboradores", estado});

    // Columna 2Usuarios con editor tipo combo
    TableColumn columnaUsuarios = Propuestas.getColumnModel().getColumn(2);

    // solo muestra el texto seleccionado
    columnaUsuarios.setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
        JLabel label = new JLabel();
        label.setOpaque(true);
        if (isSelected) label.setBackground(Color.WHITE);
        if (value != null) label.setText(value.toString());
        return label;
    });

    // crear combo con los items correctos
    columnaUsuarios.setCellEditor(new DefaultCellEditor(new JComboBox<>()) {
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            JComboBox<String> combo = new JComboBox<>();
            for (String u : usuarios) combo.addItem(u);
            if (value != null) combo.setSelectedItem(value);
            return combo;
        }
    });
    Propuestas.setRowHeight(20);
}

    private void mostrarPerfilProponente(DTOProponente usr) {
        
         
        lblNick.setText(usr.getNickname());
        lblNombre.setText(usr.getNombre());
        lblApellido.setText(usr.getApellido());
        lblFecha.setText(usr.getFecha().getDayOfMonth()+"/"+ usr.getFecha().getMonthValue()+"/"+usr.getFecha().getYear());
        lblEmail.setText(usr.getEmail());
        lblDireccion.setText(usr.getDireccion());
        lblBiografia.setText(usr.getBiografia());
        lblWeb.setText(usr.getWebSite());

        ImageIcon icon=new ImageIcon(usr.getRutaImg());
        Image img =icon.getImage().getScaledInstance(lblImagen.getWidth(),lblImagen.getHeight(), Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
        
        String[] columnas = {"Título", "Monto Recaudado", "Usuarios", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        Propuestas.setModel(modelo);
        
       
        List<DTOPropuesta> propuestas = new ArrayList<>(controller.getPropuestasCreadasPorProponente(usr.getNickname()));
        if(!propuestas.isEmpty()){
        propuestas.sort(Comparator.comparing(p -> p.getEstadoAct()));
        System.out.println( );
        }
        for(DTOPropuesta p: propuestas){
            //System.out.println("jhola");
            List<String> colaboradores=controller.colaboradoresAPropuesta(p.getTitulo());
            int monto=controller.getMontoRecaudado(p.getTitulo());
            mostrarPropuestas(p.getTitulo(),monto,p.getEstadoAct(),colaboradores,modelo);
        }
    }


    private void ProponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProponentesActionPerformed

       
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
