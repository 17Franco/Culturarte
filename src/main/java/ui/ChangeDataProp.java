/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ui;

import java.util.Arrays;
import java.util.List;
import logica.DTO.DTOPropuesta;
import logica._enum.TipoRetorno;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logica.Fabrica;
import logica.IController;
import logica._enum.TipoRetorno;
import logica.DTO.DTFecha;
import logica._enum.Estado;

/**
 *
 * @author asus
 */
public class ChangeDataProp extends javax.swing.JInternalFrame {
    
    private IController controller = Fabrica.getInstance();
    private String rutaImagen = null;
    DTOPropuesta datos = new DTOPropuesta();
    /**
     * Creates new form ChangeDataProp
     */
    public ChangeDataProp() {
        initComponents();
        
        EstadoM.removeAllItems();
        TipoRetorno1.removeAllItems();
        CateM.removeAllItems();
        for (String c : controller.ListaCategoria()) {
            CateM.addItem(c);
        }
        for (TipoRetorno t : TipoRetorno.values()) {
            TipoRetorno1.addItem(t);
        }
        for (Estado e : Estado.values()) {
            EstadoM.addItem(e);
        }
    }
   private boolean validarCampo(List<JTextField> campos) {
        for (JTextField campo : campos) {
            String texto = campo.getText().trim();
            if (!Utilities.validarNoVacio(campo)) return false;
        }
        if (!Utilities.validarFecha(d.getText(), m.getText(), a.getText())) {
            return false;
        }

        return true; 
    }
   public void obtenerT (DTOPropuesta a){
          datos = a; 
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Encabezado = new javax.swing.JLabel();
        Descripcion = new javax.swing.JLabel();
        Tipo = new javax.swing.JLabel();
        Lugar = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        PrecioEntrada = new javax.swing.JLabel();
        Monto = new javax.swing.JLabel();
        EstadoModi = new javax.swing.JLabel();
        DescripcionField = new javax.swing.JTextField();
        TipoField = new javax.swing.JTextField();
        LugarField = new javax.swing.JTextField();
        d = new javax.swing.JTextField();
        PrecioEntradaField = new javax.swing.JTextField();
        MontoTotalField = new javax.swing.JTextField();
        EstadoM = new javax.swing.JComboBox<>();
        Dia = new javax.swing.JLabel();
        Mes = new javax.swing.JLabel();
        m = new javax.swing.JTextField();
        Anio = new javax.swing.JLabel();
        a = new javax.swing.JTextField();
        Cerrar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        img = new javax.swing.JLabel();
        Imagen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TipoRetorno1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        CateM = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Modificando Datos");

        Encabezado.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 18)); // NOI18N
        Encabezado.setText("Modificar los datos ");
        Encabezado.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                EncabezadoInputMethodTextChanged(evt);
            }
        });

        Descripcion.setText("Descripcion");

        Tipo.setText("Tipo");

        Lugar.setText("Lugar");

        Fecha.setText("Fecha");

        PrecioEntrada.setText("Precio Entrada");

        Monto.setText("Monto Total");

        EstadoModi.setText("Estado");

        DescripcionField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescripcionFieldActionPerformed(evt);
            }
        });

        TipoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoFieldActionPerformed(evt);
            }
        });

        LugarField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LugarFieldActionPerformed(evt);
            }
        });

        d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dActionPerformed(evt);
            }
        });

        PrecioEntradaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecioEntradaFieldActionPerformed(evt);
            }
        });

        MontoTotalField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MontoTotalFieldActionPerformed(evt);
            }
        });

        EstadoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadoMActionPerformed(evt);
            }
        });

        Dia.setText("Dia");

        Mes.setText("Mes");

        m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mActionPerformed(evt);
            }
        });

        Anio.setText("Año");

        a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aActionPerformed(evt);
            }
        });

        Cerrar.setText("Cerrar");

        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });

        img.setText("Imagen");

        Imagen.setText("Subir Nueva Imagen");
        Imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImagenActionPerformed(evt);
            }
        });

        jLabel1.setText("Retorno");

        jLabel2.setText("Categoria");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EstadoModi)
                            .addComponent(Monto)
                            .addComponent(PrecioEntrada)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(img))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 59, Short.MAX_VALUE)
                                .addComponent(Dia)
                                .addGap(18, 18, 18)
                                .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Mes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Anio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(CateM, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TipoRetorno1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EstadoM, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(MontoTotalField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PrecioEntradaField)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Descripcion)
                            .addComponent(Tipo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Lugar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Fecha, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DescripcionField)
                            .addComponent(TipoField)
                            .addComponent(LugarField)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cerrar)
                        .addGap(47, 47, 47)))
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Encabezado)
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Encabezado)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DescripcionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Descripcion))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tipo)
                    .addComponent(TipoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lugar)
                    .addComponent(LugarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Fecha)
                    .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Dia)
                    .addComponent(Mes)
                    .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Anio)
                    .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PrecioEntrada)
                    .addComponent(PrecioEntradaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Monto)
                    .addComponent(MontoTotalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EstadoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EstadoModi))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TipoRetorno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CateM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img)
                    .addComponent(Imagen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cerrar)
                    .addComponent(Modificar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
 
        String auxTitulo = datos.getTitulo();
        String auxUsuario = datos.getProponente().getNickname();
        String descripcion = DescripcionField.getText();
        String tipo = TipoField.getText();
        String lugar = LugarField.getText();
        String dia = d.getText();
        String montoTotal = MontoTotalField.getText();
        TipoRetorno retorno = (TipoRetorno) TipoRetorno1.getSelectedItem();
        Estado newEstado = (Estado) EstadoM.getSelectedItem();
        String precio = PrecioEntradaField.getText();
        String auxCat = (String) CateM.getSelectedItem();
        String mes =m.getText();
        String anio =a.getText();
         
        List<JTextField> campos = Arrays.asList(DescripcionField,TipoField,LugarField,PrecioEntradaField,MontoTotalField,d,m,a);

            if(validarCampo(campos)){
                Utilities.copiarImagen(rutaImagen,auxTitulo);
                DTFecha fechaEvento=new DTFecha(Integer.parseInt(dia),Integer.parseInt(mes),Integer.parseInt(anio));
                controller.modificarPropuesta(auxTitulo, descripcion, tipo, rutaImagen, lugar, fechaEvento, precio, montoTotal,retorno,auxCat, auxUsuario,newEstado);
                JOptionPane.showMessageDialog(this, "Propuesta Modificada con exito");                       

            }
    }//GEN-LAST:event_ModificarActionPerformed

    private void EncabezadoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_EncabezadoInputMethodTextChanged

    }//GEN-LAST:event_EncabezadoInputMethodTextChanged

    private void DescripcionFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescripcionFieldActionPerformed
       
    }//GEN-LAST:event_DescripcionFieldActionPerformed

    private void TipoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoFieldActionPerformed
       
    }//GEN-LAST:event_TipoFieldActionPerformed

    private void LugarFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LugarFieldActionPerformed
        
    }//GEN-LAST:event_LugarFieldActionPerformed

    private void dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dActionPerformed
       
    }//GEN-LAST:event_dActionPerformed

    private void MontoTotalFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MontoTotalFieldActionPerformed
       
    }//GEN-LAST:event_MontoTotalFieldActionPerformed

    private void EstadoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoMActionPerformed
        
    }//GEN-LAST:event_EstadoMActionPerformed

    private void ImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImagenActionPerformed
        JFileChooser fileProp = new JFileChooser();
        fileProp.setDialogTitle("Seleccionar imagen");
        fileProp.setFileSelectionMode(JFileChooser.FILES_ONLY);

        fileProp.setAcceptAllFileFilterUsed(false);
        fileProp.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imagenes", "jpg", "png"));

        int resultado = fileProp.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileProp.getSelectedFile();
            rutaImagen = archivo.getAbsolutePath();
        }        
    }//GEN-LAST:event_ImagenActionPerformed

    private void PrecioEntradaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecioEntradaFieldActionPerformed
        
    }//GEN-LAST:event_PrecioEntradaFieldActionPerformed

    private void mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mActionPerformed
        
    }//GEN-LAST:event_mActionPerformed

    private void aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aActionPerformed
        
    }//GEN-LAST:event_aActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Anio;
    private javax.swing.JComboBox<String> CateM;
    private javax.swing.JButton Cerrar;
    private javax.swing.JLabel Descripcion;
    private javax.swing.JTextField DescripcionField;
    private javax.swing.JLabel Dia;
    private javax.swing.JLabel Encabezado;
    private javax.swing.JComboBox<Estado> EstadoM;
    private javax.swing.JLabel EstadoModi;
    private javax.swing.JLabel Fecha;
    private javax.swing.JButton Imagen;
    private javax.swing.JLabel Lugar;
    private javax.swing.JTextField LugarField;
    private javax.swing.JLabel Mes;
    private javax.swing.JButton Modificar;
    private javax.swing.JLabel Monto;
    private javax.swing.JTextField MontoTotalField;
    private javax.swing.JLabel PrecioEntrada;
    private javax.swing.JTextField PrecioEntradaField;
    private javax.swing.JLabel Tipo;
    private javax.swing.JTextField TipoField;
    private javax.swing.JComboBox<TipoRetorno> TipoRetorno1;
    private javax.swing.JTextField a;
    private javax.swing.JTextField d;
    private javax.swing.JLabel img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField m;
    // End of variables declaration//GEN-END:variables
}
