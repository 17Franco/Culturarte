package ui;

import logica.DTO.DTOPropuesta;
import logica.Fabrica;
import logica.IController;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class ListaPropuesta extends javax.swing.JInternalFrame {

    private IController controller = Fabrica.getInstance();
   
   public ListaPropuesta() {
        initComponents();

        //Control de vacio para categoria sino no muestra la lista y no deja hacer nada
        Set<DTOPropuesta> propuestas = controller.obtenerPropuestas("");
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (DTOPropuesta p : propuestas) {
            modeloLista.addElement(p.getTitulo());
        }
        ListaPropuesta.setModel(modeloLista);
        ListaPropuesta.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if (e.getClickCount() == 2) {
                String tituloSeleccionado = ListaPropuesta.getSelectedValue();
                Set<DTOPropuesta> propuestas = controller.obtenerPropuestas("");
                DTOPropuesta propuestaSeleccionada = null;

                for (DTOPropuesta p : propuestas) {
                    if (p.getTitulo().equals(tituloSeleccionado)) {
                        propuestaSeleccionada = p;
                        break;
                    }
                }
                if (propuestaSeleccionada != null) {
                    MostrarDatosPropuesta mostrar = new MostrarDatosPropuesta();           
                    mostrar.SetDatosPropuesta(propuestaSeleccionada);                               
                    JDesktopPane fond = getDesktopPane();
                    if (fond != null) {
                        fond.add(mostrar);
                        mostrar.setSize(fond.getSize());
                        mostrar.setVisible(true);
                    }
                }
            }
        }
    });
    }
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaPropuesta = new javax.swing.JList<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Listado Propuestas");
        setToolTipText("Añadir");
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 14)); // NOI18N
        jLabel1.setText("Seleccione la Propuesta ");

        ListaPropuesta.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaPropuesta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaPropuestaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ListaPropuesta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        //TODO
    }//GEN-LAST:event_formFocusGained

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        //TODO
    }//GEN-LAST:event_formFocusLost

    private void ListaPropuestaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaPropuestaMouseClicked

    }//GEN-LAST:event_ListaPropuestaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListaPropuesta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
