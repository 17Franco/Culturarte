package ui;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import logica.DTO.DTOPropuesta;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author klaas
 */

//Esta UI solo muestra una lista de propuestas por estado al usuario, es parte de "ConsultaPropuestasPorEstado"
public class ListaPropuestasPorEstado extends javax.swing.JInternalFrame {

    Set<DTOPropuesta> lista; //lista a mostrar
    
    public ListaPropuestasPorEstado() 
    {
        initComponents();
        lista = new HashSet<>();
    }
    
    public void SetListaPropuesta(Set<DTOPropuesta> _lista)
    {
        lista = _lista;
        actualizarTabla();  //Luego de actualizar la lista se debe refrescar la tabla.
        
    }
    
    public void actualizarTabla() 
    {
        String[] c1 = {"Nombre","Descripción"};
        
        DefaultTableModel tabla = new DefaultTableModel(c1, 0); //Se inicializa directamente

        for(DTOPropuesta ct : lista) //Iteracion para ir ingresando contenido.
        {
            tabla.addRow(new Object[]{ct.getTitulo(), ct.getDescripcion()});
        }

        jTable1.setModel(tabla);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollTabla = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        subtitulo = new javax.swing.JLabel();
        botonAtras = new javax.swing.JButton();
        botonContinuar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Listado de Propuestas");

        scrollTabla.setViewportView(jTable1);

        subtitulo.setText("Seleccione una propuesta...");

        botonAtras.setText("Atrás");
        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtrasActionPerformed(evt);
            }
        });

        botonContinuar.setText("Siguiente...");
        botonContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonContinuarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(botonAtras)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonContinuar)))
                    .addComponent(subtitulo))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subtitulo)
                .addGap(11, 11, 11)
                .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAtras)
                    .addComponent(botonContinuar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtrasActionPerformed
        
        ConsultaPropuestaPorEstado setup = new ConsultaPropuestaPorEstado();            //Se inicializa ventana con el setup de Consulta.
       
        JDesktopPane fondo1 = this.getDesktopPane();
        fondo1.add(setup);
        setup.setSize(fondo1.getSize());
        setup.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_botonAtrasActionPerformed

    private void botonContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonContinuarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonContinuarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAtras;
    private javax.swing.JButton botonContinuar;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JLabel subtitulo;
    // End of variables declaration//GEN-END:variables
}
