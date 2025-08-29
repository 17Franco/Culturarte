package ui;


import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import logica.DTO.DTOColaboracion;
import logica.DTO.DTOColaborador;
import logica.Fabrica;
import logica.IController;


public class ConsultaColaborador extends javax.swing.JInternalFrame {
     private IController controller = Fabrica.getInstance();
    
    public ConsultaColaborador() {
        initComponents();
        
        Proponentes.removeAllItems(); 
        
        Proponentes.addItem("SeleccionarUsuario"); 
        for (String u : controller.ListaColaborador()) {
            Proponentes.addItem(u); 
        }
        
        
        Proponentes.addActionListener(e -> {
            // filtrar cambios válidos
            limpiador();
            String item = (String) Proponentes.getSelectedItem();
            if(item != null && controller.existe(item)){
                DTOColaborador usr = controller.getDTOColaborador(item);
                mostrarPerfilColaborador(usr);
                // jScrollPane2.setVisible(true);
            }
        });
    }
    private void limpiador(){
        lblNick.setText("");
        lblNombre.setText("");
        lblApellido.setText("");
        lblFecha.setText("");
        lblEmail.setText("");
        lblImagen.setIcon(null);
    }
    
    private void mostrarColaboraciones(String tituloP,String NickProponente,int monto, String estado,DefaultTableModel modelo){
        
    modelo.addRow(new Object[]{tituloP, NickProponente,monto, estado});
    }
    private void mostrarPerfilColaborador(DTOColaborador usr) {
        
        lblNick.setText(usr.getNickname());
        lblNombre.setText(usr.getNombre());
        lblApellido.setText(usr.getApellido());
        lblFecha.setText(Integer.toString(usr.getFecha().getDay())+"/"+ Integer.toString(usr.getFecha().getMonth())+"/"+Integer.toString(usr.getFecha().getYear()));
        lblEmail.setText(usr.getEmail());
       
        ImageIcon icon=new ImageIcon(usr.getRutaImg());
        Image img =icon.getImage().getScaledInstance(lblImagen.getWidth(),lblImagen.getHeight(), Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
        
        String[] columnas = {"TítuloPropuesta", "Creador", "Recaudacion", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        Colaboraciones.setModel(modelo);
        
        List<DTOColaboracion> registros=controller.colaboraciones(usr.getNickname()); //me traigo las colaboraciones echas por el user seleccionado
        
        
        for(DTOColaboracion r:registros){
            //titulo propuesta r.getPropuestaFinanciada();  aca lo tengo
            String titulo= r.getPropuesta();
            //necesito nombre proponente que la creo
            
            String nombreProponente=controller.creadorPropuesta(titulo);
            //necesito monto recuadado por la propuesta 
            int monto = controller.getMontoRecaudado(titulo);
               
            //y nesesito el estado de la propuesta 
            String estado =controller.estadoPropuestas(titulo);
            
            mostrarColaboraciones(titulo,nombreProponente,monto,estado,modelo);
            
            
        }
        //String nombrePropuesta=usr.
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
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
        jLabel1 = new javax.swing.JLabel();
        Proponentes = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Colaboraciones = new javax.swing.JTable();
        lblImagen = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblPropuestas = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta Colaborador");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.GridLayout(0, 2));

        jLabel4.setText("NickName");
        jPanel2.add(jLabel4);
        jPanel2.add(lblNick);

        jLabel6.setText("Nombre");
        jPanel2.add(jLabel6);
        jPanel2.add(lblNombre);

        jLabel8.setText("Apellido");
        jPanel2.add(jLabel8);
        jPanel2.add(lblApellido);

        jLabel10.setText("Fecha");
        jPanel2.add(jLabel10);
        jPanel2.add(lblFecha);

        jLabel13.setText("Email");
        jPanel2.add(jLabel13);
        jPanel2.add(lblEmail);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setText("Consulta Perfil Colaboradores");

        Proponentes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Colaboraciones.setModel(new javax.swing.table.DefaultTableModel(
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
                "TituloPropuesta", "Creador", "Recaudacion", "Estado"
            }
        ));
        jScrollPane1.setViewportView(Colaboraciones);

        lblImagen.setText("No File");
        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblImagen.setMaximumSize(new java.awt.Dimension(135, 155));

        jLabel2.setText("Colaboradores");

        lblPropuestas.setText("Colaboraciones");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(Proponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPropuestas)
                        .addGap(193, 193, 193))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Proponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblPropuestas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Colaboraciones;
    private javax.swing.JComboBox<String> Proponentes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNick;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPropuestas;
    // End of variables declaration//GEN-END:variables
}
