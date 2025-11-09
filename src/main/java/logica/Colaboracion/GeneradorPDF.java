package logica.Colaboracion;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import logica.DTO.DTOColaboracion;

public class GeneradorPDF {

    public static File generar(DTOColaboracion dto) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        File archivo = null;

        try {
            // 📄 Nombre temporal del archivo
            String nombreArchivo = "Constancia_Colaboracion_" + dto.getId() + ".pdf";
            archivo = new File(System.getProperty("java.io.tmpdir"), nombreArchivo);
            PdfWriter.getInstance(document, new FileOutputStream(archivo));
            document.open();

            // 🕒 Fecha actual
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaEmision = LocalDate.now().format(formatoFecha);

            // 🏛️ Encabezado
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font subtituloFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font textoFont = new Font(Font.FontFamily.HELVETICA, 11);

            Paragraph titulo = new Paragraph("CULTURARTE - CONSTANCIA DE COLABORACIÓN", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Fecha de emisión: " + fechaEmision, textoFont));
            document.add(new Paragraph("-----------------------------------------------------------------------------------------"));
            document.add(new Paragraph(" "));

            // 👤 Datos del colaborador
            Paragraph datosColaborador = new Paragraph("DATOS DEL COLABORADOR", subtituloFont);
            datosColaborador.setSpacingAfter(5);
            document.add(datosColaborador);

            document.add(new Paragraph("Nombre de usuario: " + dto.getColaborador(), textoFont));
            document.add(new Paragraph("ID de colaboración: " + dto.getId(), textoFont));
            document.add(new Paragraph(" "));

            // 🎨 Datos de la colaboración
            Paragraph datosColaboracion = new Paragraph("DETALLES DE LA COLABORACIÓN", subtituloFont);
            datosColaboracion.setSpacingAfter(5);
            document.add(datosColaboracion);

            document.add(new Paragraph("Propuesta: " + dto.getPropuesta(), textoFont));
            document.add(new Paragraph("Tipo de retorno: " + dto.getTipoRetorno(), textoFont));
            document.add(new Paragraph("Fecha de creación: " + dto.getCreado(), textoFont));
            document.add(new Paragraph(" "));

            // 💰 Datos del pago
            Paragraph datosPago = new Paragraph("DATOS DEL PAGO", subtituloFont);
            datosPago.setSpacingAfter(5);
            document.add(datosPago);

            document.add(new Paragraph("Monto aportado: $" + dto.getMonto(), textoFont));
            document.add(new Paragraph("Medio de pago: Plataforma Culturarte", textoFont)); // ejemplo, podés cambiarlo
            document.add(new Paragraph(" "));

            document.add(new Paragraph("-----------------------------------------------------------------------------------------"));
            document.add(new Paragraph(
                "Gracias por apoyar el arte y la cultura a través de la plataforma Culturarte.",
                textoFont
            ));
            document.add(new Paragraph(
                "Este documento certifica la colaboración realizada de forma digital.",
                textoFont
            ));

            document.close();
            System.out.println("✅ PDF generado correctamente en: " + archivo.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return archivo;
    }
}
