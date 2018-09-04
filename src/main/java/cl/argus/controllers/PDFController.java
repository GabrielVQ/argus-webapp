package cl.argus.controllers;


import cl.argus.argusApplication;
import cl.argus.models.*;
import cl.argus.repositories.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
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
    //BLMasterRepository blMasterRepository;
    BLHouseRepository blHouseRepository;
    //CiudadRepository ciudadRepository;
    BLMaster blMaster;
    BLHouse blHouse;
    List<Cargament>  cargamento;
    List<Ingreso>  ingreso;
    /**
     *
     * @param file
     * @param idBl
     * @return
     */
    public Document createPDF(String file, int numeroOperacion, int numeroBLHouse, BLHouseRepository blHouseRepository, BLMasterRepository blMasterRepository, CargamentRepository cargamentRepository, IngresoRepository ingresoRepository) throws IOException {

        Document document = null;


        try {
            String auxNumeroOperacion = Integer.toString(numeroOperacion);
            String auxNumeroBLHouse = Integer.toString(numeroBLHouse);
            System.out.println("numeroOperacion:"+ numeroOperacion);
            System.out.println("numeroHouse:"+ numeroBLHouse);
            //buscqueda de datos por número de operacion
            Iterable <BLHouse> blHouses = blHouseRepository.getByNumeroOperacion(auxNumeroOperacion);
            blMaster = blMasterRepository.getByNumeroOperacion(numeroOperacion).get(0);
            Iterable <Cargament> cargamentos = cargamentRepository.getByNumeroOperacion(auxNumeroOperacion);
            Iterable <Ingreso> ingresos = ingresoRepository.getByNumeroOperacion(auxNumeroOperacion);
            //System.out.println( "BLMASTER:" + blMasterRepository.getByNumeroOperacion(3).get(0).getBLMasterNumero());
            //System.out.println( "CARGAMENTO:" + cargamentRepository.getByNumeroOperacion("3").get(0).getDescriptionGoods());
            //System.out.println("tengo bl master:"+(((List<BLHouse>) blHouses).get(0).getBlMaster().getBLMasterNumero()));

            blHouse = buscarBLHouse((List<BLHouse>)blHouses,auxNumeroBLHouse);
            cargamento = buscarCargamento((List<Cargament>)cargamentos , auxNumeroBLHouse);
            ingreso = buscarIngresos((List<Ingreso>)ingresos , auxNumeroBLHouse);


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


    public BLHouse buscarBLHouse (List<BLHouse> BLHouses, String numeroBLHouse ){

       int i = 0;

       while(i<BLHouses.size()){
           //System.out.println("tengo numero:" + BLHouses.get(i).getNumeroBLHouse());
           //System.out.println("busncado numero:"+numeroBLHouse);
           if ( BLHouses.get(i).getNumeroBLHouse().equals(numeroBLHouse)){

               System.out.println("encontre el blhouse");
               return BLHouses.get(i);
           }
           i++;
       }
        System.out.println("no encontre nada");
        return null;

    }

    public List<Cargament> buscarCargamento (List<Cargament> cargamento, String numeroBLHouse ){

        int i = 0;
        List<Cargament> cargamentoBLHouse = new ArrayList<Cargament>() {
        };
        while(i<cargamento.size()){
            //System.out.println("tengo numero:" + cargamento.get(i).getNumeroBLHouse());
            //System.out.println("busncado numero:"+numeroBLHouse);
            if ( cargamento.get(i).getNumeroBLHouse().equals(numeroBLHouse)){

                //System.out.println("encontre el blhouse");
                cargamentoBLHouse.add(cargamento.get(i));
            }
            i++;
        }
        //System.out.println("no encontre nada");
        return cargamentoBLHouse;

    }

    public List<Ingreso> buscarIngresos (List<Ingreso> ingresos, String numeroBLHouse ){

        int i = 0;
        List<Ingreso> ingresosBLHouse = new ArrayList<Ingreso>() {
        };
        while(i<ingresos.size()){
            //System.out.println("tengo numero:" + cargamento.get(i).getNumeroBLHouse());
            //System.out.println("busncado numero:"+numeroBLHouse);
            if ( ingresos.get(i).getNumeroBLHouse().equals(numeroBLHouse)){

                //System.out.println("encontre el blhouse");
                ingresosBLHouse.add(ingresos.get(i));
            }
            i++;
        }
        //System.out.println("no encontre nada");
        return ingresosBLHouse;

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

        String name=blHouse.getShipper().getRazon_social();


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

        String name=blHouse.getNotify().getRazon_social();
        String direccion=blHouse.getNotify().getDireccion();
        String[] direcciones= direccion.split("\\n");

        String rut=blHouse.getNotify().getRut();
        String fono=blHouse.getNotify().getFonoContacto();
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

        String name=blHouse.getNotify().getRazon_social();
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
        celda.add(new Paragraph("PLACE OF RECEIPT"+blHouse.getLugarRecepcion(),title));
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

        String vessel= blMaster.getNave().getNombre();
        String loading=blMaster.getPuertoOrigen().getNombre();


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

        String descarga=blMaster.getPuertoDescarga().getNombre();
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
        //provisoriamente se completa con agente aduana
        Paragraph celda= new Paragraph();
        celda.add(new Paragraph("FORWARDING AGENT",title));
        celda.add("\n");
        celda.add(new Paragraph(blHouse.getClienteExtranjero().getNombreAbrev(),content));
        celda.add("\n");
        celda.add(new Paragraph(blHouse.getClienteExtranjero().getRazon_social(),content));
        celda.add("\n");
        celda.add(new Paragraph(blHouse.getClienteExtranjero().getDireccion(),content));
        celda.add("\n");
       // celda.add(new Paragraph("JUAREZ CIUDAD DE MEXICO C.P. 06600 ",content));
        //celda.add("\n");
        //celda.add(new Paragraph(blHouse.getClienteExtranjero().get"\n\n",content));
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
        Font content = new Font(Font.FontFamily.HELVETICA, 8);

        //Cargament carga= cargamento.iterator().next();

        Paragraph celda= new Paragraph();

        celda.add(new Paragraph("CONTAINER\n"+cargamento.get(0).getContenedor().getSigla()+" "+ cargamento.get(0).getContenedor().getNumeroContenedor()+"-"+cargamento.get(0).getContenedor().getDigito()+"\n"+cargamento.get(0).getMarkNumbers(),content));
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createNumberOfPackages(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 8);


        //Cargament carga= cargamento.iterator().next();


        Paragraph celda= new Paragraph();
        celda.add((new Paragraph(cargamento.get(0).getNumerPackages(),content)));
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(c1);

    }
    private void createDescriptionOfGods(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 8);


        //Cargament carga= cargamento.iterator().next();


        Paragraph celda= new Paragraph();
        celda.add(new Paragraph(cargamento.get(0).getDescriptionGoods(),content));
        celda.add(" ");

        PdfPCell c1 = new PdfPCell(celda);

        table.addCell(c1);

    }

    private void createGrossWeight(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 8);


        //Cargament carga= cargamento.iterator().next();


        Paragraph celda= new Paragraph();
        celda.add(new Paragraph(cargamento.get(0).getGroosWeight(),content));
        celda.add(" ");


        PdfPCell cl = new PdfPCell(celda);
        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cl);

    }

    private void createMeasurement(PdfPTable table) throws DocumentException {
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 8);




            Paragraph celda = new Paragraph();
            celda.add(new Paragraph(cargamento.get(0).getMeasurement(), content));
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
        for (int i = 0; i<ingreso.size(); i++) {
            celda.add(new Paragraph(ingreso.get(i).getCobro().getNombreCobro()+" "+blHouse.getMoneda()+"\n", content));
        }

        PdfPCell cl =new PdfPCell(celda);

        table.addCell(cl);
    }

    private void createPrepaid(PdfPTable table) throws DocumentException{
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        Paragraph celda = new Paragraph();
        celda = new Paragraph();
        for (int i = 0; i<ingreso.size(); i++) {
            celda.add(new Paragraph(ingreso.get(i).getPrepaid()+"\n", content));
        }

        PdfPCell cl =new PdfPCell(celda);

        table.addCell(cl);
    }

    private void createCollect(PdfPTable table) throws DocumentException{
        Font title = new Font(Font.FontFamily.HELVETICA, 6);
        Font contentBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font content = new Font(Font.FontFamily.HELVETICA, 9);


        Paragraph celda = new Paragraph();
        celda = new Paragraph();
        for (int i = 0; i<ingreso.size(); i++) {
            celda.add(new Paragraph(ingreso.get(i).getCollect()+"\n", content));
        }

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
        celda.add(new Paragraph("    ARGUS LOGISTICA LIMITADA\n",contentBold));


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

        float totalPrepaid = 0;
        for (int i = 0; i<ingreso.size(); i++) {
            totalPrepaid = Float.parseFloat(ingreso.get(i).getPrepaid()) + totalPrepaid;
        }
        celda = new Paragraph();
        celda.add(new Paragraph(String.format("%.2f", totalPrepaid)+" "+blHouse.getMoneda(),contentBold));
        cl =new PdfPCell(celda);
        cl.setBackgroundColor(new BaseColor(0,255,255));

        table.addCell(cl);

        float totalCollect = 0;
        for (int i = 0; i<ingreso.size(); i++) {
            totalCollect = Float.parseFloat(ingreso.get(i).getCollect()) + totalCollect;
        }
        celda = new Paragraph();
        celda.add(new Paragraph(String.format("%.2f", totalCollect)+" "+blHouse.getMoneda(),contentBold));
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
