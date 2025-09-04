/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ui;

import jakarta.persistence.EntityManager;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import logica.Categoria.Categoria;
import logica.DTO.DTOCategoria;
import logica.Fabrica;
import logica.IController;
import persistencia.PersistenciaManager;

/**
 *
 * @author asus
 */
public class SelectCategoria extends javax.swing.JInternalFrame {
    
    private IController controller = Fabrica.getInstance();
    private EntityManager dbManager;
    private AltaPropuesta altaP;
    private ChangeDataProp ModP;
    public SelectCategoria(AltaPropuesta altaP) {
        initComponents();
        this.altaP = altaP;
        refrescarJtree();
    }
    public SelectCategoria(ChangeDataProp ModP) {
        initComponents();
        this.ModP = ModP;
        refrescarJtree();
    }
    private void refrescarJtree() {
        arbol = cargarJtree(controller.getCategorias());
        arbol.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    TreePath path = arbol.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        String valor = node.getUserObject().toString();
                        if (altaP != null) {
                            altaP.AsignarCategoria(valor);
                        }
                        if (ModP != null) {
                            ModP.AsignarCategoria(valor);
                        }
                        dispose();
                    }
                }
            }
        });
        panelDelArbol.setViewportView(arbol);
        panelDelArbol.revalidate();
        panelDelArbol.repaint();
    }
    private DefaultMutableTreeNode Cat_a_Jt(DTOCategoria cat) //Con esto hago un nodo del arbol compatible con la Jtree
    {
        DefaultMutableTreeNode temp = new DefaultMutableTreeNode(cat);

        for (DTOCategoria ct : cat.getSubcategorias()) //Se recorre cadasubcat de la raíz envidada
        {
            temp.add(Cat_a_Jt(ct));   //Paso recursivo...
        }

        return temp;
    }

    public JTree cargarJtree(Map<String, DTOCategoria> input_AlmacenCategorias) {

        DefaultMutableTreeNode almacen = new DefaultMutableTreeNode("Categorías");

        dbManager = PersistenciaManager.getEntityManager(); //Se asigna base de datos

        try {
            String resultadoConsulta = "select catImport from Categoria catImport where catImport.catPadre is NULL";
            List<Categoria> datosImportadosDb = dbManager.createQuery(resultadoConsulta, Categoria.class).getResultList();

            for (Categoria ct : datosImportadosDb) {
                DTOCategoria temp = ct.Cat_a_DTO(); //Se pasa a DTO.
                almacen.add(Cat_a_Jt(temp));        //Se pasa a Jtree.
            }

        } finally {
            dbManager.close();
        }

        JTree temp = new JTree(almacen);    //JTree final creado

        return temp;

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        panelDelArbol = new javax.swing.JScrollPane();
        arbol = new javax.swing.JTree();
        subTitulo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        panelDelArbol.setViewportView(arbol);

        subTitulo.setText("Categorías disponibles actualmente");

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(panelDelArbol, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(122, 122, 122)
                            .addComponent(subTitulo))))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 19, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 19, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(subTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDelArbol, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(15, 15, 15))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 144, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 145, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbol;
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JScrollPane panelDelArbol;
    private javax.swing.JLabel subTitulo;
    // End of variables declaration//GEN-END:variables
}
