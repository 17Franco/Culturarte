package ui;
import java.util.ArrayList;
import java.util.List;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTOCategoria;
import javax.swing.JMenuItem;
import java.util.Map;
import ui.AltaPropuesta;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

import javax.swing.JMenuItem;

public class Main extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

   
    public Main() {
        initComponents();
        jMenu2.setVisible(false);
        setLocationRelativeTo(null);
    }
           
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        fondo = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 585, Short.MAX_VALUE)
        );

        jMenu1.setText("Sistema");

        jMenuItem1.setText("Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Propuesta");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Categoria");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Colaboracion");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        
        jMenu2.setText("Categoria");
        jMenu2.removeAll();
        JMenuItem altaCategoriaItem = new JMenuItem("Alta Categoria");

        altaCategoriaItem.addActionListener(e ->{
            AltaDeCategoria altaCat = new AltaDeCategoria();
            fondo.add(altaCat);
            altaCat.setSize(fondo.getSize());
            altaCat.setVisible(true);
        });

        jMenu2.add(altaCategoriaItem);
        jMenu2.setVisible(true);
    
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //AltaUsuario Alta=new AltaUsuario();
        jMenu2.setText("Usuario");
        jMenu2.removeAll();


        String[] opcionesUsuario = { "Alta Usuario", "Consulta Proponente", "Consulta Colaborador","Seguir Usuario","Dejar de seguir usuario"};

        for (String op : opcionesUsuario) {
            JMenuItem item = new JMenuItem(op);

             item.addActionListener(e -> {

                switch (op) {
                    case "Alta Usuario" -> 
                    {
                        AltaUsuario Alta=new AltaUsuario();
                        fondo.add(Alta);
                        Alta.setSize(fondo.getSize());
                        Alta.setVisible(true);
                    }
                    case "Consulta Proponente" ->
                    {
                        ConsultaProponente ConsultaP=new ConsultaProponente();
                        fondo.add(ConsultaP);
                        ConsultaP.setSize(fondo.getSize());
                        ConsultaP.setVisible(true);
                    }
                    case "Consulta Colaborador" ->
                    {
                        ConsultaColaborador ConsultaC=new ConsultaColaborador();
                        fondo.add(ConsultaC);
                        ConsultaC.setSize(fondo.getSize());
                        ConsultaC.setVisible(true);
                    }  
                    case "Seguir Usuario" ->
                    {
                        SeguirUsuario SeguirU=new SeguirUsuario();
                        fondo.add(SeguirU);
                        SeguirU.setSize(fondo.getSize());
                        SeguirU.setVisible(true);
                    }
                    case "Dejar de seguir usuario" ->
                    {
                        DejarDeSeguirUsuario unfollow = new DejarDeSeguirUsuario();
                        fondo.add(unfollow);
                        unfollow.setSize(fondo.getSize());
                        unfollow.setVisible(true);
                    }
                }
            });
             jMenu2.add(item);
    }

          
        jMenu2.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
            
        jMenu2.setText("Propuesta");
        jMenu2.removeAll();
            
            String[] opcionesPropuesta = { "Alta Propuesta", "Modificar Propuesta","Listar Propuesta", "Consultar Propuestas Por Estado" };
            
            for (String sel : opcionesPropuesta){
                JMenuItem menuPropuesta = new JMenuItem(sel);
                
                menuPropuesta.addActionListener (e -> 
                    {
                        switch (sel) 
                        {
                            case "Alta Propuesta" -> 
                            {
                                AltaPropuesta PropNew = new AltaPropuesta(); 
                                fondo.add(PropNew);
                                PropNew.setSize(fondo.getSize());
                                PropNew.setVisible(true);
                            }
                            case "Modificar Propuesta" -> 
                            {
                                ModificarDatosPropuesta MProp = new ModificarDatosPropuesta(); 
                                fondo.add(MProp);
                                MProp.setSize(fondo.getSize());
                                MProp.setVisible(true);
                            }
                            case "Listar Propuesta" -> 
                            {
                                ListaPropuesta PropNew = new ListaPropuesta(); 
                                fondo.add(PropNew);
                                PropNew.setSize(fondo.getSize());
                                PropNew.setVisible(true);
                            }
                            case "Consultar Propuestas Por Estado" -> 
                            {                           
                                ConsultaPropuestaPorEstado consultaPorEstado = new ConsultaPropuestaPorEstado();
                                fondo.add(consultaPorEstado);
                                consultaPorEstado.setSize(fondo.getSize());
                                consultaPorEstado.setVisible(true);
                            }
                        }
                    }
                );
                
                jMenu2.add(menuPropuesta);
            }
            
            jMenu2.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    
    jMenu2.setText("Colaboracion");
    jMenu2.removeAll();
        String[] opcionesColaboracion = { "Colaborar a Propuesta", "Consultar Propuesta", "Cancelar Colaboracion" };


  for (String op : opcionesColaboracion) {
        JMenuItem menuItem = new JMenuItem(op);

        menuItem.addActionListener(e -> {
            switch (op) {
                case "Colaborar a Propuesta" -> {
                    RegistrarColaboracionAPropuesta frame = new RegistrarColaboracionAPropuesta();
                    fondo.add(frame);
                    frame.setSize(fondo.getSize());
                    frame.setVisible(true);
                    break;
                }
                case "Consultar Propuesta" -> {
                    
                    // ConsultarPropuesta frame = new ConsultarPropuesta();
                    // fondo.add(frame); frame.setSize(fondo.getSize()); frame.setVisible(true);
                     break;
                }
                case "Cancelar Colaboracion" -> {
                    // Aquí iría tu lógica para cancelar colaboración
                    JOptionPane.showMessageDialog(this, "Función Cancelar Colaboración aún no implementada");
                     break;
                }
            }
        });

        jMenu2.add(menuItem);
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    jMenu2.setVisible(true);
    
    /**
     *
     * @param args
     */
    }
    public static void main(String args[]) {
    
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane fondo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables
}
