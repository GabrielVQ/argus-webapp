package cl.argus.controllers;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.argus.models.Empresa;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerHelper;


import com.itextpdf.tool.xml.XMLWorkerHelper;


import javax.swing.*;

/*
 * Clase para la creacion de documentos PDF que consta de distintos metodos para formato y posibilidades
 * de a&ntilde;adir componentes como titulos, subtitulos o tablas.
 */
public class PDFController {

    private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    /**
     *
     * @param file
     * @param nombre_usuario
     * @return
     */
    public Document createPDF(String file, String nombre_usuario) throws IOException {

        Document document = null;

        try {


            document = new Document(PageSize.LETTER,1, 1, 2, 2);

            PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            addMetaData(document);

            addTitlePage(document,nombre_usuario);
            //TABLA DE DATOS
            PdfPTable tablaGeneral = new PdfPTable(2);
            tablaGeneral.setPaddingTop(0);
            tablaGeneral.setWidthPercentage(95);


            PdfPTable tablaLeft = new PdfPTable(1);
            PdfPTable tablaRight= new PdfPTable(1);
            PdfPTable details= new PdfPTable(5);
            details.setWidths(new float[] { 1, 0.5f,3.5f,0.7f,0.6f});
            details.setWidthPercentage(95);


            createShipper(tablaLeft);
            createConsignee(tablaLeft);
            createNotifyParty(tablaLeft);
            createPrecarriagePlace(tablaLeft);
            createOceanPort(tablaLeft);
            createDischargeDelivery(tablaLeft);

            createDocBlNumber(tablaRight);
            createExportReferences(tablaRight);
            createForwardingAgent(tablaRight);
            createPointAndCountryOfOrigin(tablaRight);
            createForRelease(tablaRight);
            createTypeContainerized(tablaRight);

            PdfPCell left= new PdfPCell(tablaLeft);
            PdfPCell right=new PdfPCell(tablaRight);

            left.setPadding(0);
            right.setPadding(0);
            left.setBorder(Rectangle.NO_BORDER);
            right.setBorder(Rectangle.NO_BORDER);
            tablaGeneral.addCell(left);
            tablaGeneral.addCell(right);
            document.add(tablaGeneral);

            document.add(new Paragraph("\n",new Font(Font.FontFamily.HELVETICA, 6)));
            createEncabezadosDetalles(details);
            createMarkAndNumbers(details);
            createNumberOfPackages(details);
            createDescriptionOfGods(details);
            createGrossWeight(details);
            createMeasurement(details);
            document.add(details);
            /////


            document.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;

    }


    private void addMetaData(Document document) {
        document.addTitle("Generate PDF report");
        document.addSubject("Generate PDF report");
        document.addAuthor("Java Honk");
        document.addCreator("Java Honk");
    }

    /**
     *
     * @param document
     * @param nombre_usuario
     * @throws DocumentException
     */

    private void addTitlePage(Document document, String nombre_usuario)
            throws DocumentException {

        Paragraph preface = new Paragraph();
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font subBoldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font subFont = new Font(Font.FontFamily.HELVETICA, 8,Font.BOLD);

        Paragraph title = new Paragraph("ARGUS LOGISTICA LTDA.",boldFont);
        title.setAlignment(Element.ALIGN_CENTER);

        preface.add(title);

        Paragraph subtitle= new Paragraph();

        subtitle.add(new Phrase("BILL OF LADING              ",subBoldFont));
        subtitle.add(new Phrase("FOR COMBINED TRANSPORT SHIPMENT OR PORT TO PORT SHIPMENT",subFont));
        subtitle.setAlignment(Element.ALIGN_CENTER);

        preface.add(subtitle);
        preface.add(" ");
        document.add(preface);
    }

    /**
     *
     * @param document
     * @param nombre_usuario
     * @throws DocumentException
     */




    /**
     *
     * @param paragraph
     * @param number
     */
    private void creteEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }



    /**
     *
     * @param table
     * @throws DocumentException
     */


    private void createShipper(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        ///SHIPPER
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("SHIPPER",title));
        celda.add("\n");
        celda.add(new Paragraph("ALUSA CHILE S.A.",contentBold));
        celda.add("\n");
        celda.add(new Paragraph("AVDA. PDTE. EDUARDO FREI MONTALVA N°9160",contentBold));
        celda.add("\n");
        celda.add(new Paragraph("SANTIAGO CHILE",contentBold));
        celda.add("\n");
        celda.add(new Paragraph("RUT : 89.010.400-2  F:226793200\n",contentBold));
        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createConsignee(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);



        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("CONSIGNEE",title));
        celda.add("\n");
        celda.add(new Paragraph("DELIMEX DE MEXICO SA DE CV ",contentBold));
        celda.add("\n");
        celda.add(new Paragraph("AVDA PERIFERICO SUR 7980  EDIFICIO 3D SANTA MARIA ",contentBold));
        celda.add("\n");
        celda.add(new Paragraph("TEQUEPEXPAN, 45601 MEXICO RFC:DME920609BE5 ",contentBold));
        celda.add("\n");
        celda.add(new Paragraph("CIUDAD DE MEXICO/MEXICO\n",contentBold));
        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }
    private void createNotifyParty(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);



        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("NOTIFY PARTY",title));
        celda.add("\n");
        celda.add(new Paragraph("DELIMEX DE MEXICO SA DE CV",contentBold));
        celda.add("\n");
        celda.add(new Paragraph("AVDA PERIFERICO SUR 7980  EDIFICIO 3D SANTA MARIA ",contentBold));
        celda.add("\n");
        celda.add(new Paragraph("TEQUEPEXPAN, 45601 MEXICO RFC:DME920609BE5 ",contentBold));
        celda.add("\n");
        celda.add(new Paragraph("CIUDAD DE MEXICO/MEXICO",contentBold));
        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createPrecarriagePlace(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        PdfPTable tablita = new PdfPTable(2);

        //PRECARRIAGE
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("PRECARRIAGE",title));
        celda.add("\n");
        celda.add(new Paragraph(" ",content));
        PdfPCell cl= new PdfPCell(celda);

        tablita.addCell(cl);

        //PLACE
        celda= new Paragraph();
        celda.add(new Paragraph("PLACE OF RECEIPT",title));
        celda.add("\n");
        celda.add(new Paragraph(" ",content));
        cl= new PdfPCell(celda);
        tablita.addCell(cl);

        PdfPCell todo= new PdfPCell(tablita);
        todo.setPadding(0);
        todo.setBorder(0);

        table.addCell(todo);

    }

    private void createOceanPort(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        PdfPTable tablita = new PdfPTable(2);

        //Ocean vessel
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("OCEAN VESSEL",title));
        celda.add("\n");
        celda.add(new Paragraph("XIN YA ZHOU V.132",content));
        PdfPCell cl= new PdfPCell(celda);

        tablita.addCell(cl);

        //Port of loading
        celda= new Paragraph();
        celda.add(new Paragraph("PORT OF LOADING",title));
        celda.add("\n");
        celda.add(new Paragraph("SAN ANTONIO",content));
        cl= new PdfPCell(celda);

        tablita.addCell(cl);
        PdfPCell todo= new PdfPCell(tablita);
        todo.setPadding(0);
        todo.setBorder(0);

        table.addCell(todo);

    }

    private void createDischargeDelivery(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        PdfPTable tablita = new PdfPTable(2);

        //Port of dicharge
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("PORT OF DISCHARGE",title));
        celda.add("\n");
        celda.add(new Paragraph("MANZANILLO",content));
        PdfPCell cl= new PdfPCell(celda);

        tablita.addCell(cl);

        //Port of loading
        celda= new Paragraph();
        celda.add(new Paragraph("PLACE OF DELIVERY",title));
        celda.add("\n");
        celda.add(new Paragraph("MANZANILLO",content));
        cl= new PdfPCell(celda);

        tablita.addCell(cl);
        PdfPCell todo= new PdfPCell(tablita);
        todo.setPadding(0);
        todo.setBorder(0);

        table.addCell(todo);

    }

    private void createTypeContainerized(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        PdfPTable tablita = new PdfPTable(2);

        //TYPE OF MOVE
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("TYPE OF MOVE",title));
        celda.add("\n");
        celda.add(new Paragraph("FCL/FCL",content));
        PdfPCell cl= new PdfPCell(celda);

        tablita.addCell(cl);

        //Port of loading
        celda= new Paragraph();
        celda.add(new Paragraph("CONTAINERIZED (vessel only)",title));
        celda.add("\n");
        celda.add(new Paragraph("YES _X_   NO ___",content));
        cl= new PdfPCell(celda);

        tablita.addCell(cl);
        PdfPCell todo= new PdfPCell(tablita);
        todo.setPadding(0);
        todo.setBorder(0);

        table.addCell(todo);

    }

    private void createDocBlNumber(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        PdfPTable tablita = new PdfPTable(2);

        //docNumber
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("DOCUMENT NUMBER",title));
        celda.add("\n");
        celda.add(new Paragraph("ARG 753",content));
        PdfPCell cl= new PdfPCell(celda);

        tablita.addCell(cl);

        //BLnumber
        celda= new Paragraph();
        celda.add(new Paragraph("DocumentNumber",title));
        celda.add("\n");
        celda.add(new Paragraph("ARGEM20170548",content));
        cl= new PdfPCell(celda);

        tablita.addCell(cl);
        PdfPCell todo= new PdfPCell(tablita);
        todo.setPadding(0);
        todo.setBorder(0);

        table.addCell(todo);

    }

    private void createExportReferences(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("EXPORT REFERENCES",title));
        celda.add("\n");
        celda.add(new Paragraph("ARGUS LOGISTICA LIMITADA",content));
        celda.add("\n");
        celda.add(new Paragraph("ALMIRANTE SEÑORET 151 OF 113 ",content));
        celda.add("\n");
        celda.add(new Paragraph("VALPARAISO/CHILE\n",content));
        PdfPCell c1 = new PdfPCell(celda);


        table.addCell(c1);

    }

    private void createForwardingAgent(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("FORWARDING AGENT",title));
        celda.add("\n");
        celda.add(new Paragraph("RH SHIPPING & CHARTERING S. DE R.L. DE C.V. ",content));
        celda.add("\n");
        celda.add(new Paragraph("AVDA PASEO DE LA REFORMA N° 222 PISO 15 COL. ",content));
        celda.add("\n");
        celda.add(new Paragraph("JUAREZ CIUDDA DE MEXICO C.P. 06600 ",content));
        celda.add("\n");
        celda.add(new Paragraph("CIUDAD DE MEXICO/MEXICO",content));
        PdfPCell c1 = new PdfPCell(celda);


        table.addCell(c1);

    }

    private void createPointAndCountryOfOrigin(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("POINT AND COUNTRY OF ORIGIN",title));
        celda.add("\n");
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);


        table.addCell(c1);

    }

    private void createForRelease(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("FOR RELEASE OF >GOODS APPLY TO",title));
        celda.add("\n");
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createEncabezadosDetalles(PdfPTable table)throws DocumentException{
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda = new Paragraph();
        celda.add(new Paragraph("MARK AND NUMBERS",contentBold));
        PdfPCell cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("NUMBERS OF PACKAGES",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("DESCRIPTION OF GODS",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        table.addCell(cl);


        celda = new Paragraph();
        celda.add(new Paragraph("GROSS WEIGHT",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        table.addCell(cl);


        celda = new Paragraph();
        celda.add(new Paragraph("MEASUREMENT",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        table.addCell(cl);
    }

    private void createMarkAndNumbers(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("CONTAINER\nXINU 11911-5\nS/ WHL1664697",content));
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createNumberOfPackages(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda= new Paragraph();
        celda.add((new Paragraph("1",content)));
        celda.add(" ");
        celda.setAlignment(Element.ALIGN_RIGHT);
        PdfPCell c1 = new PdfPCell(celda);


        table.addCell(c1);

    }
    private void createDescriptionOfGods(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("x20 STD CONTAINER STC\n" +
                "5 PALLETS CON 24 BOBINAS DE: \n" +
                "COMPLEJO DE ALUMINIO 7 MIC. LAMINADO CON FILM DE\n" +
                "POLYESTER 12 MIC. Y POLIETILENO 100 MIC. EN BOBINAS \n" +
                "IMPRESAS\n" +
                "6.374,00 MTS. 21066 PELICULA LAMINADA KETCHUP 90G\n" +
                "CODIGO ARANCEL: 7607.2090 \n" +
                "FILM DE POLIETILENO 90 MIC. LAMINADO CON FILM DE\n" +
                "POLYESTER 12 MIC. EN BOBINAS IMPRESAS\n" +
                "25.360,00 MTS. 21283 PAPILLA PLATANO 113G\n" +
                "CODIGO ARANCEL: 3920.1010\n\n" +
                "PAIS DE ORIGEN : CHILE \n" +
                "CLEAN ON BOARD \n" +
                "FREIGHT COLLECT\n",content));
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createGrossWeight(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("1.487,46",content));
        celda.add(" ");
        celda.setAlignment(Element.ALIGN_RIGHT);

        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createMeasurement(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda= new Paragraph();
        celda.add(new Paragraph(" ",content));
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

}
