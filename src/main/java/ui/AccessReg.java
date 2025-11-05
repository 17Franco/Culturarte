/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import logica.IController;
import logica.Fabrica;
import logica.DTO.DTORegistrosAccesoWeb;

/**
 *
 * @author klaas
 */
public class AccessReg extends javax.swing.JInternalFrame {

    private final IController controller = Fabrica.getInstance().getController();
    private Timer t;
    int pass = 0;   //Esto es para que la primera vez averigue si hay elementos o si no, si hay, se recargará automáticamente.
            
    public AccessReg() 
    {
        initComponents();
        vertScrollBarManager();
        descargarInfo();
        
    }
    
    
    private void vertScrollBarManager()
    {
        jScrollPane2.getVerticalScrollBar().addAdjustmentListener(e -> 
        {
          
            if (!e.getValueIsAdjusting()) 
            {
                //Obtengo parámetros del scroll para comparar
                int max = jScrollPane2.getVerticalScrollBar().getMaximum();
                int visible = jScrollPane2.getVerticalScrollBar().getVisibleAmount();
                int value = e.getValue();

                //Si no está abajo y está siendo usado...
                if (value + visible < max && jToggleButton1.isSelected()) 
                {
                    jToggleButton1.setSelected(false);  //Lo apago
                    jToggleButton1.repaint();
                    jToggleButton1.revalidate();
                }
            }
        });
    }
    
    
    
    @Override
    public void dispose() 
    {   //Se sobrecarga dispose para que tambíen pare el timer cuando se cierra ventana de cualquer manera
        if (t != null)
        {   
            t.stop();
        }
        super.dispose();
    }
    
    private void updater() //Con esto consigo que el sistema actualice solo y no sobrecargue tanto, cada 2 seg
    {
        if(t == null && pass == 1)    //Solo se usa si hay elementos
        {
            t = new Timer(2000, e -> descargarInfo());
            t.start();
        }
    }
    
    public void descargarInfo()
    {     
        SwingWorker<Void, Void> worker; 
        
        worker = new SwingWorker<>() 
        {
            private final DefaultTableModel modelo = new DefaultTableModel(new String[]{"#", "IP", "URL", "Navegador", "SO"}, 0) 
            {
                @Override
                public boolean isCellEditable(int row, int column) 
                {
                    return false; 
                }
            };
               
            //Proceso segundo plano obteniendo datos de db
            @Override
            protected Void doInBackground() 
            {
                List<DTORegistrosAccesoWeb> accesos = controller.obtenerRegistrosAccesoWeb();
                
                if (accesos != null && !accesos.isEmpty()) 
                {
                    modelo.setRowCount(0);
                    
                    for (DTORegistrosAccesoWeb reg : accesos) 
                    {
                        if (reg != null && reg.getAcceso() != null)
                        {
                            modelo.addRow(new Object[]{reg.getId(),reg.getIp(),reg.getUrl(),reg.getNavegadorWeb(),reg.getSO() });
                        }
                    }
                    
                    pass = 1;   //Se puede repetir.
                }
                else
                {
                    pass = 0;
                    
                    if (t != null)
                    {
                        t.stop();   
                    }
                }
                
                return null;
            }
           
            @Override //Luego dependiendo de como finalice arriba se hace esto...
            protected void done() 
            {
                if (pass == 0) //Saco para que no quede una pantalla vacía
                {
                    JOptionPane.showMessageDialog(AccessReg.this,"No hay registros actualmente","Culturarte Workstation",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                else    //Si hay algo, setea los datos en tabla
                {
                    tablaDatos.setModel(modelo);
                    
                    tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    
                    tablaDatos.getColumnModel().getColumn(0).setPreferredWidth(60); 
                    tablaDatos.getColumnModel().getColumn(1).setPreferredWidth(150);
                    tablaDatos.getColumnModel().getColumn(2).setPreferredWidth(500); 
                    tablaDatos.getColumnModel().getColumn(3).setPreferredWidth(80); 
                    tablaDatos.getColumnModel().getColumn(4).setPreferredWidth(80); 
                    
                    tablaDatos.revalidate();
                    tablaDatos.repaint();
                    
                    //Esto para que la scrollbar vertical quede al final
                    if (jToggleButton1.isSelected()) 
                    {
                        JScrollBar barra = jScrollPane2.getVerticalScrollBar();
                        barra.setValue(barra.getMaximum());             //La manda al fndo
                    }

                    
                    if(pass == 1)
                    {
                       updater();
                    }
                }
            }
        }; 
        
        worker.execute(); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        BotonSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        jToggleButton1 = new javax.swing.JToggleButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registros de Acceso Web");
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));

        jLabel1.setText("Registros de los últimos 30 días:");

        BotonSalir.setText("Salir");
        BotonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSalirActionPerformed(evt);
            }
        });

        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaDatos.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jScrollPane2.setViewportView(tablaDatos);

        jToggleButton1.setText("Mantener ScrllBar abajo");
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseClicked(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotonSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonSalir)
                    .addComponent(jToggleButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSalirActionPerformed
        
        if (t != null)  //Detengo el timer
        {
            t.stop();
        }
        
        this.dispose();
    }//GEN-LAST:event_BotonSalirActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseClicked
       
        if(jToggleButton1.isSelected())
       {
           jToggleButton1.setSelected(false);
           jToggleButton1.repaint();
       }
        else
        {
            jToggleButton1.setSelected(true);
            jToggleButton1.repaint();
        }
    }//GEN-LAST:event_jToggleButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
