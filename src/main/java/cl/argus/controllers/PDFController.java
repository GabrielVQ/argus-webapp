package cl.argus.controllers;


import cl.argus.models.Cargament;
import cl.argus.repositories.BLHouseRepository;
import cl.argus.repositories.BLMasterRepository;
import cl.argus.models.BLMaster;
import cl.argus.models.BLHouse;
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
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.tool.xml.XMLWorkerHelper;


import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import javax.print.Doc;
import javax.swing.*;

/*
 * Clase para la creacion de documentos PDF que consta de distintos metodos para formato y posibilidades
 * de a&ntilde;adir componentes como titulos, subtitulos o tablas.
 */
@CrossOrigin
@RestController
public class PDFController {


    private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    @Autowired
    BLMasterRepository blMasterRepository;

    BLHouse blHouse;
    BLMaster blMaster;

    public Document createReserva(String file,Long id,BLHouseRepository blHouseRepository) throws IOException{
        Document document = null;
        try{
            blHouse=blHouseRepository.findOne(id);
            document = new Document(PageSize.LETTER,40, 40, 5, 2);
            PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addMetaData(document);


            Font title= new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font font= new Font(Font.FontFamily.COURIER, 12);
            Font subBoldFont = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
            Font subFont = new Font(Font.FontFamily.COURIER, 8,Font.BOLD);

            Image image = Image.getInstance("src/main/java/cl/argus/controllers/argus.jpg");
            image.scalePercent(50,50);
            image.setAlignment(Element.ALIGN_CENTER);



            Paragraph titulo= new Paragraph("CONFIRMACION DE RESERVA FCL\n\n",title);
            titulo.setAlignment(Element.ALIGN_CENTER);

            Paragraph destinatario= new Paragraph(   "SEÑORES  : \n" +
                                                            "ATENCION : \n" +
                                                            "FECHA    : \n\n",font
            );

            Paragraph saludos= new Paragraph("ESTIMADOS SEÑORES,\n" +
                                                    "LES DETALLAMOS INFORMACION DE ESPACIO PARA SU EXPORTACION.\n\n",font
            );
                                                /// en vez de poner el dato en sí poner  "texto" + dato +"\n" 
            Paragraph cuerpo= new Paragraph( "NUMERO DE BOOKING  :SAI0012667\n" +
                                                    "NAVIERA            :WAN HAI\n" +
                                                    "NAVE               :XIN YA ZHOU  V.132\n" +
                                                    "PUERTO DE EMBARQUE :SAN ANTONIO\n" +
                                                    "EQUIPO             :1X20\n" +
                                                    "PUERTO DESTINO     :MANZANILLO \n" +
                                                    "FECHA ZARPE        :26/05/2018\n" +
                                                    "FECHA DE STACKING  :20 AL 24  DE MAYO HASTA LAS 21:00 HRS\n" +
                                                    "CIERRE DOCUMENTAL  :23 DE MAYO  2018 HASTA LAS 12:00 HRS \n" +
                                                    "REFERENCIA         :DELIMEX\n" +
                                                    "RETIRO DE UNIDAD   :SITRANS SANTIAGO\n" +
                                                    "CONDICION DE FLETE :COLLECT\n" +
                                                    "VºBº D.U.S         :SAN ANTONIO\n\n"
                    ,font);

            Paragraph despedida= new Paragraph("OBS: ENVIAR MATRIZ DEFINITIVA ANTES DEL  CIERRE  DEL CUT OFF DOCUMENTAL  \n" +
                    "A:  ana.alvarez@arguscr.cl; eavendano@arguscr.cl de no ser enviada en el plazo se cobrara por Corrección y Matriz Fuera de Plazo\n\n\n\n",font);

            LineSeparator separator = new LineSeparator();

            separator.setLineWidth(3);
            separator.setPercentage(100);

            Paragraph direccion = new Paragraph("ESTADO 33 OF 73 \n" +
                    "Fonos 2 2638 0023 / 2 2633 9770 / 2 2638 4976\n" +
                    "\tSANTIAGO DE CHILE\n", font);
            direccion.setAlignment(Element.ALIGN_CENTER);


            document.add(image);
            document.add(titulo);
            document.add(destinatario);
            document.add(saludos);
            document.add(cuerpo);
            document.add(despedida);
            document.add(separator);
            document.add(direccion);


            document.close();







        }catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     *

     * @return
     */
    public Document createPDF(String file, Long id,BLHouseRepository blHouseRepository) throws IOException {

        Document document = null;


        try {
            blHouse=blHouseRepository.findOne(id);
            System.out.println(blHouse.getShipper());
            document = new Document(PageSize.LETTER,1, 1, 2, 2);

            PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            addMetaData(document);
            addTitlePage(document);
            //TABLA DE DATOS
            PdfPTable tablaGeneral = new PdfPTable(2);
            tablaGeneral.setPaddingTop(0);
            tablaGeneral.setWidthPercentage(95);


            PdfPTable tablaLeft = new PdfPTable(1);
            PdfPTable tablaRight= new PdfPTable(1);
            PdfPTable details= new PdfPTable(5);
            PdfPTable pagos= new PdfPTable(5);
            details.setWidths(new float[] { 1, 0.5f,3.5f,0.7f,0.6f});
            details.setWidthPercentage(95);

            pagos.setWidths(new float[] {1.5f,1.75f/2,1.75f/2,0.3f,-0.3f+3.5f/2+0.7f+0.6f});
            pagos.setWidthPercentage(95);


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
            document.add(new Paragraph("\n",new Font(Font.FontFamily.HELVETICA, 6)));
            createTitleCostos(pagos);
            createFreightAndCharges(pagos);
            createPrepaid(pagos);
            createCollect(pagos);
            createVoid(pagos);
            createAnnounce(pagos);
            createTotalCollectPrepaid(pagos);
            document.add(pagos);

            document.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;

    }


    private void addMetaData(Document document) {
        document.addTitle("Confirmacion de reserva");
        document.addSubject("Confirmacion de reserva");
        document.addAuthor("Argus");
        document.addCreator("Argus");
    }

    /**
     *
     * @param document
     * @param
     * @throws DocumentException
     */

    private void addTitlePage(Document document)
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



    private void creteEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }


    private void createShipper(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        String name=blHouse.getShipper().getNombreAbrev();


        String direccion=blHouse.getShipper().getDireccion();
        String[] direcciones= direccion.split("\\n");

        String rut=blHouse.getShipper().getRut();
        String fono=blHouse.getShipper().getFonoContacto();



        ///SHIPPER
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("SHIPPER",title));
        celda.add("\n");
        celda.add(new Paragraph( name,contentBold));
        celda.add("\n");
        for (String cosa:direcciones) {
            System.out.println(cosa);
            celda.add(new Paragraph(cosa,contentBold));
            celda.add("\n");
        }
        celda.add(new Paragraph("RUT:"+rut,contentBold));
        celda.add(new Paragraph("    FONO:"+fono,contentBold));
        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

        }

    private void createConsignee(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        String name=blHouse.getConsignee().getNombreAbrev();
        String direccion=blHouse.getConsignee().getDireccion();
        String[] direcciones= direccion.split("\\n");

        String rut=blHouse.getConsignee().getRut();
        String fono=blHouse.getConsignee().getFonoContacto();
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("CONSIGNEE",title));
        celda.add("\n");
        celda.add(new Paragraph( name,contentBold));
        celda.add("\n");
        for (String cosa:direcciones) {
            System.out.println(cosa);
            celda.add(new Paragraph(cosa,contentBold));
            celda.add("\n");
        }
        celda.add(new Paragraph("RUT:"+rut,contentBold));
        celda.add(new Paragraph("    FONO:"+fono,contentBold));
        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }
    private void createNotifyParty(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        String name=blHouse.getNotify().getNombreAbrev();
        String direccion=blHouse.getNotify().getDireccion();
        String[] direcciones= direccion.split("\\n");

        String rut=blHouse.getShipper().getRut();
        String fono=blHouse.getShipper().getFonoContacto();
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("NOTIFY PARTY",title));
        celda.add("\n");
        celda.add(new Paragraph( name,contentBold));
        celda.add("\n");
        for (String cosa:direcciones) {
            System.out.println(cosa);
            celda.add(new Paragraph(cosa,contentBold));
            celda.add("\n");
        }
        celda.add(new Paragraph("RUT:"+rut,contentBold));
        celda.add(new Paragraph("    FONO:"+fono,contentBold));
        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createPrecarriagePlace(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        String precarriage= blHouse.getPreCarriage();
        String place= blHouse.getLugarRecepcion();

        PdfPTable tablita = new PdfPTable(2);

        //PRECARRIAGE
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("PRECARRIAGE",title));
        celda.add("\n");
        celda.add(new Paragraph(precarriage,content));
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

        String vessel= blHouse.getBlMaster().getNave().getNombre();
        String loading=blHouse.getBlMaster().getPuertoOrigen().getNombre();


        PdfPTable tablita = new PdfPTable(2);

        //Ocean vessel
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("OCEAN VESSEL",title));
        celda.add("\n");
        celda.add(new Paragraph(vessel,content));
        PdfPCell cl= new PdfPCell(celda);

        tablita.addCell(cl);

        //Port of loading
        celda= new Paragraph();
        celda.add(new Paragraph("PORT OF LOADING",title));
        celda.add("\n");
        celda.add(new Paragraph(loading,content));
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

        String descarga=blHouse.getBlMaster().getPuertoDescarga().getNombre();
        String place=blHouse.getCiudadLlegada().getNombre();

        PdfPTable tablita = new PdfPTable(2);

        //Port of dicharge
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("PORT OF DISCHARGE",title));
        celda.add("\n");
        celda.add(new Paragraph(descarga,content));
        PdfPCell cl= new PdfPCell(celda);

        tablita.addCell(cl);

        //Port of loading
        celda= new Paragraph();
        celda.add(new Paragraph("PLACE OF DELIVERY",title));
        celda.add("\n");
        celda.add(new Paragraph(place,content));
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

        String tipo=blHouse.getTipoMovimiento() ;
        PdfPTable tablita = new PdfPTable(2);

        //TYPE OF MOVE
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("TYPE OF MOVE",title));
        celda.add("\n");
        celda.add(new Paragraph(tipo,content));
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

        String  docNumber = blHouse.getNumeroOperacion();
        String blNumber= blHouse.getNumeroBLHouse();
        PdfPTable tablita = new PdfPTable(2);

        //docNumber
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("DOCUMENT NUMBER",title));
        celda.add("\n");
        celda.add(new Paragraph(docNumber,content));
        PdfPCell cl= new PdfPCell(celda);

        tablita.addCell(cl);

        //BLnumber
        celda= new Paragraph();
        celda.add(new Paragraph("B/L Number",title));
        celda.add("\n");
        celda.add(new Paragraph(blNumber,content));
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
        celda.add(new Paragraph("VALPARAISO/CHILE\n\n",content));
        PdfPCell c1 = new PdfPCell(celda);


        table.addCell(c1);

    }

    private void createForwardingAgent(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        String forwarding;

        /////////////////////////////////////////////////////////////////////
        //FALTA ESTO ESTO ESTO ESTO
        /////////////////////////////////////////////////////////////////////

        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("FORWARDING AGENT",title));
        celda.add("\n");
        celda.add(new Paragraph("RH SHIPPING & CHARTERING S. DE R.L. DE C.V. ",content));
        celda.add("\n");
        celda.add(new Paragraph("AVDA PASEO DE LA REFORMA N° 222 PISO 15 COL. ",content));
        celda.add("\n");
        celda.add(new Paragraph("JUAREZ CIUDAD DE MEXICO C.P. 06600 ",content));
        celda.add("\n");
        celda.add(new Paragraph("CIUDAD DE MEXICO/MEXICO\n\n",content));
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
        celda.add("\n\n\n\n\n");
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
        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("NUMBERS OF PACKAGES",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("DESCRIPTION OF GODS",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);


        celda = new Paragraph();
        celda.add(new Paragraph("GROSS WEIGHT",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);


        celda = new Paragraph();
        celda.add(new Paragraph("MEASUREMENT",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);
    }

    private void createMarkAndNumbers(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Cargament carga= blHouse.getCargaments().iterator().next();

        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("CONTAINTER\n"+carga.getMarkNumbers(),content));
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createNumberOfPackages(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        Cargament carga= blHouse.getCargaments().iterator().next();


        Paragraph celda= new Paragraph();
        celda.add((new Paragraph(carga.getNumerPackages(),content)));
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(c1);

    }
    private void createDescriptionOfGods(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        Cargament carga= blHouse.getCargaments().iterator().next();


        Paragraph celda= new Paragraph();
        celda.add(new Paragraph(carga.getDescriptionGoods(),content));


        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createGrossWeight(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        Cargament carga= blHouse.getCargaments().iterator().next();


        Paragraph celda= new Paragraph();
        celda.add(new Paragraph(carga.getGroosWeight(),content));
        celda.add(" ");


        PdfPCell cl = new PdfPCell(celda);
        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);

    }

    private void createMeasurement(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        Cargament carga= blHouse.getCargaments().iterator().next();


        Paragraph celda= new Paragraph();
        celda.add(new Paragraph(carga.getMeasurement(),content));
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createTitleCostos(PdfPTable table) throws DocumentException{
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda = new Paragraph();
        celda.add(new Paragraph("FREIGHT AND CHARGES",contentBold));
        PdfPCell cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("PREAPAID",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("COLLECT",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));
        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("",contentBold));
        cl =new PdfPCell(celda);

        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));

        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);

    }

    private void createFreightAndCharges(PdfPTable table) throws DocumentException{
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        Paragraph celda = new Paragraph();
        celda = new Paragraph();
        celda.add(new Paragraph(" \n\n\n\n",contentBold));
        PdfPCell cl =new PdfPCell(celda);

        table.addCell(cl);
    }

    private void createPrepaid(PdfPTable table) throws DocumentException{
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        Paragraph celda = new Paragraph();
        celda = new Paragraph();
        celda.add(new Paragraph("\n\n\n\n",contentBold));
        PdfPCell cl =new PdfPCell(celda);

        table.addCell(cl);
    }

    private void createCollect(PdfPTable table) throws DocumentException{
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda = new Paragraph();
        celda = new Paragraph();
        celda.add(new Paragraph(" \n\n\n\n",contentBold));
        PdfPCell cl =new PdfPCell(celda);

        table.addCell(cl);
    }
    private void createVoid(PdfPTable table) throws DocumentException{
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda = new Paragraph();
        celda = new Paragraph();
        celda.add(new Paragraph(" \n\n\n\n",contentBold));
        PdfPCell cl =new PdfPCell(celda);

        table.addCell(cl);
    }
    private void createAnnounce(PdfPTable table) throws DocumentException{
        Font title = new Font(Font.FontFamily.HELVETICA, 7);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 6);

        Paragraph celda = new Paragraph();
        celda.add(new Paragraph("" +
                        "Received by Carrier for shipment by ocean vesel between port\n" +
                        "of loading and port of discharge, and for arrangement or\n"+
                        "procurement of pre-carriage from please of receip and on-carriage\n"+
                        "to please of delivery, where stated above, the goods as specified\n" +
                        "above in apparent good order and condition unless otherwise\n" +
                        "stated. The goods to be delivered at the above mentioned port\n" +
                        "of discharge or please of delivery, whichever is applicable, subject\n" +
                        "always to the exceptions, limitations, conditions and liberties set\n" +
                        "out the reverse side hereof, to whitch the Shipper and/or\n" +
                        "Consignee agree to accepting this Bill of Lading.\n" +
                        "In witness where of the original Bill of Lading all of this tenor\n" +
                        "and date have been signed in then number stated above, one of\n" +
                        " whitch being accomplished the other(s) to be void.\n\n" ,content));
        celda.add(new Paragraph("DATED AT:\t",title));
        celda.add(new Paragraph("  SAN ANTONIO 27 DE MAYO 2018\n\n",contentBold));
        celda.add(new Paragraph("BY       \t:",title));
        celda.add(new Paragraph("    BY ARGUS LOGISTICA LIMITADA\n",contentBold));


        PdfPCell cl =new PdfPCell(celda);
        cl.setPadding(20);
        table.addCell(cl);
    }


    private void createTotalCollectPrepaid(PdfPTable table) throws DocumentException{
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);

        Paragraph celda = new Paragraph();
        celda.add(new Paragraph("TOTAL PREPAID / COLLECT ",contentBold));
        PdfPCell cl =new PdfPCell(celda);
        cl.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));

        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("",contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));

        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("",contentBold));
        cl =new PdfPCell(celda);

        table.addCell(cl);

        celda = new Paragraph();
        celda.add(new Paragraph("",contentBold));
        cl =new PdfPCell(celda);


        table.addCell(cl);
    }




}
