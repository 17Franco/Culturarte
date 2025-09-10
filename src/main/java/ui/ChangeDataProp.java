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
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logica.DTO.DTOCategoria;
import logica.Fabrica;
import logica.IController;
import logica._enum.TipoRetorno;
import logica.DTO.DTORegistro_Estado;
import logica._enum.Estado;

/**
 *
 * @author asus
 */
public class ChangeDataProp extends javax.swing.JInternalFrame {
    
    private IController controller = Fabrica.getInstance();
    private String rutaImagen = null;
    String categoria;
    DTOPropuesta datos = new DTOPropuesta();
    /**
     * Creates new form ChangeDataProp
     */
    public ChangeDataProp() {
        initComponents();
        EstadoM.removeAllItems(); 
        for (Estado e : Estado.values()) {
            EstadoM.addItem(e);
        }
    }
    public void AsignarCategoria(String cat){
        this.categoria=cat;
        ListCat.setText(categoria);
    }
    private boolean validarCampo(List<JTextField> campos) {
        for (JTextField campo : campos) {
            if (campo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,"Faltan datos por completar","Error", JOptionPane.ERROR_MESSAGE);
                campo.requestFocus();
                return false;
            }
        }
        try {
            Integer.parseInt(PrecioEntradaField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo Precio debe ser un numero entero", "Error", JOptionPane.ERROR_MESSAGE);
            PrecioEntradaField.requestFocus();
            return false;
        }
        try {
            Integer.parseInt(MontoTotalField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo Monto debe ser un numero entero", "Error", JOptionPane.ERROR_MESSAGE);
            MontoTotalField.requestFocus();
            return false;
        }
        if (!Utilities.validarFecha(d.getText(), m.getText(), a.getText())) {
            return false;
        }
        return true;
    }
    public static boolean validarRetorno(JCheckBox t1, JCheckBox t2) {
        if (!t1.isSelected() && !t2.isSelected()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un tipo de retorno",
                    "Error", JOptionPane.ERROR_MESSAGE);
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
        Lugar = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        PrecioEntrada = new javax.swing.JLabel();
        Monto = new javax.swing.JLabel();
        EstadoModi = new javax.swing.JLabel();
        DescripcionField = new javax.swing.JTextField();
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
        jLabel2 = new javax.swing.JLabel();
        tt1 = new javax.swing.JCheckBox();
        tt2 = new javax.swing.JCheckBox();
        ListCat = new javax.swing.JButton();

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
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });

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

        tt1.setText("Entrada Gratis");

        tt2.setText("Procentaje de Ganancia");

        ListCat.setText("Seleccione Categoria");
        ListCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListCatActionPerformed(evt);
            }
        });

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
                                .addGap(0, 0, Short.MAX_VALUE)
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
                                    .addComponent(EstadoM, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(MontoTotalField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PrecioEntradaField)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tt1)
                                                .addGap(18, 18, 18)
                                                .addComponent(tt2))
                                            .addComponent(ListCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Descripcion)
                            .addComponent(Lugar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Fecha, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DescripcionField)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tt1)
                        .addComponent(tt2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(ListCat))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img)
                    .addComponent(Imagen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cerrar)
                    .addComponent(Modificar))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed

        List<JTextField> campos = Arrays.asList(DescripcionField,LugarField,PrecioEntradaField,MontoTotalField,d,m,a);
        String auxTitulo = datos.getTitulo();
        String auxUsuario = datos.getProponente().getNickname();
        
        Estado newEstado = (Estado) EstadoM.getSelectedItem();
        if (newEstado != datos.getUltimoEstado().getEstado() || DescripcionField.getText().isEmpty()){
            String auxD = datos.getDescripcion();
            String auxL = datos.getLugar();
            String auxR = datos.getImagen();
            LocalDate auxF = datos.getFecha();
            int auxP = datos.getPrecio();
            int auxM = datos.getMontoTotal();
            List<TipoRetorno> auxRet = datos.getRetorno();
            String auxC = datos.getCategoria().getNombreCategoria();
            controller.modificarPropuesta(auxTitulo,auxD,auxR, auxL, auxF, auxP, auxM,auxRet,auxC, auxUsuario,newEstado);
            JOptionPane.showMessageDialog(this, "Estado Modificado con exito");
            return;
        }
        String descripcion = DescripcionField.getText();
        String lugar = LugarField.getText();
        String dia = d.getText();
        int precio = Integer.parseInt(PrecioEntradaField.getText());
        int montoTotal = Integer.parseInt(MontoTotalField.getText());
        List<TipoRetorno> retorno = new ArrayList<>();
        if (tt1.isSelected()) {
            retorno.add(TipoRetorno.EntradaGratis);
        }
        if (tt2.isSelected()) {
            retorno.add(TipoRetorno.PorcentajeGanancia);
        }
        String mes =m.getText();
        String anio =a.getText();
            if(validarCampo(campos) && validarRetorno(tt1,tt2)){
                Utilities.copiarImagen(rutaImagen,auxTitulo);
                LocalDate fechaEvento=LocalDate.of(Integer.parseInt(anio),Integer.parseInt(mes),Integer.parseInt(dia));
                controller.modificarPropuesta(auxTitulo, descripcion,rutaImagen, lugar, fechaEvento, precio, montoTotal,retorno,categoria, auxUsuario,newEstado);
                JOptionPane.showMessageDialog(this, "Propuesta Modificada con exito");
            }
    }//GEN-LAST:event_ModificarActionPerformed

    private void EncabezadoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_EncabezadoInputMethodTextChanged

    }//GEN-LAST:event_EncabezadoInputMethodTextChanged

    private void DescripcionFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescripcionFieldActionPerformed
       
    }//GEN-LAST:event_DescripcionFieldActionPerformed

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

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_CerrarActionPerformed

    private void ListCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListCatActionPerformed
        SelectCategoria mostrar = new SelectCategoria(this);
        JDesktopPane fondo3Final = this.getDesktopPane();
        fondo3Final.add(mostrar);
        mostrar.setSize(fondo3Final.getSize());
        mostrar.setVisible(true);
    }//GEN-LAST:event_ListCatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Anio;
    private javax.swing.JButton Cerrar;
    private javax.swing.JLabel Descripcion;
    private javax.swing.JTextField DescripcionField;
    private javax.swing.JLabel Dia;
    private javax.swing.JLabel Encabezado;
    private javax.swing.JComboBox<Estado> EstadoM;
    private javax.swing.JLabel EstadoModi;
    private javax.swing.JLabel Fecha;
    private javax.swing.JButton Imagen;
    private javax.swing.JButton ListCat;
    private javax.swing.JLabel Lugar;
    private javax.swing.JTextField LugarField;
    private javax.swing.JLabel Mes;
    private javax.swing.JButton Modificar;
    private javax.swing.JLabel Monto;
    private javax.swing.JTextField MontoTotalField;
    private javax.swing.JLabel PrecioEntrada;
    private javax.swing.JTextField PrecioEntradaField;
    private javax.swing.JTextField a;
    private javax.swing.JTextField d;
    private javax.swing.JLabel img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField m;
    private javax.swing.JCheckBox tt1;
    private javax.swing.JCheckBox tt2;
    // End of variables declaration//GEN-END:variables
}
