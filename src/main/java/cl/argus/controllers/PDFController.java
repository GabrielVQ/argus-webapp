package cl.argus.controllers;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

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
    public Document createPDF(String file, String nombre_usuario) {

        Document document = null;

        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            addMetaData(document);

            addTitlePage(document,nombre_usuario);

            createTable(document);

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
        creteEmptyLine(preface, 1);
        preface.add(new Paragraph("Reporte de despachos usuario: "+nombre_usuario, TIME_ROMAN));

        creteEmptyLine(preface, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        preface.add(new Paragraph("Reporte creado en "
                + simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
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


    private void createALL(Document document){

        String ONE= "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!DOCTYPE pdf2xml SYSTEM \"pdf2xml.dtd\">\n <pdf2xml producer=\"poppler\" version=\"0.48.0\"> <page number=\"1\" position=\"absolute\" top=\"0\" left=\"0\" height=\"1188\" width=\"918\"> <fontspec id=\"0\" size=\"6\" family=\"Times\" color=\"#000000\"/> <fontspec id=\"1\" size=\"10\" family=\"Times\" color=\"#000000\"/> <fontspec id=\"2\" size=\"12\" family=\"Times\" color=\"#000000\"/> <fontspec id=\"3\" size=\"12\" family=\"Times\" color=\"#000000\"/> <fontspec id=\"4\" size=\"11\" family=\"Times\" color=\"#000000\"/> <fontspec id=\"5\" size=\"19\" family=\"Times\" color=\"#000000\"/> <fontspec id=\"6\" size=\"15\" family=\"Times\" color=\"#000000\"/> <fontspec id=\"7\" size=\"11\" family=\"Times\" color=\"#000000\"/> <fontspec id=\"8\" size=\"8\" family=\"Times\" color=\"#000000\"/> <fontspec id=\"9\" size=\"21\" family=\"Times\" color=\"#6bbbf4\"/> <text top=\"454\" left=\"696\" width=\"139\" height=\"10\" font=\"0\">CONTAINERIZED      (vessel only)</text> <text top=\"470\" left=\"745\" width=\"7\" height=\"11\" font=\"1\"><b>x</b></text> <text top=\"469\" left=\"795\" width=\"13\" height=\"10\" font=\"0\">NO</text> <text top=\"468\" left=\"711\" width=\"18\" height=\"10\" font=\"0\">YES</text> <text top=\"467\" left=\"444\" width=\"155\" height=\"17\" font=\"2\">FCL/FCL                </text> ";
        String TWO ="<text top=\"454\" left=\"444\" width=\"67\" height=\"10\" font=\"0\">TYPE OF MOVE</text> <text top=\"274\" left=\"13\" width=\"64\" height=\"10\" font=\"0\">NOTIFY PARTY</text> <text top=\"288\" left=\"13\" width=\"229\" height=\"14\" font=\"3\"><b>DELIMEX DE MEXICO SA DE CV</b></text> <text top=\"306\" left=\"13\" width=\"413\" height=\"14\" font=\"3\"><b>AVDA PERIFERICO SUR 7980  EDIFICIO 3D SANTA MARIA</b></text> <text top=\"324\" left=\"13\" width=\"377\" height=\"14\" font=\"3\"><b>TEQUEPEXPAN, 45601 MEXICO RFC:DME920609BE5</b></text> <text top=\"342\" left=\"13\" width=\"209\" height=\"14\" font=\"3\"><b>CIUDAD DE MEXICO/MEXICO</b></text> <text top=\"382\" left=\"13\" width=\"83\" height=\"10\" font=\"0\">PRE-CARRIAGE BY</text> <text top=\"418\" left=\"13\" width=\"69\" height=\"10\" font=\"0\">OCEAN VESSEL</text> <text top=\"431\" left=\"15\" width=\"122\" height=\"15\" font=\"4\">XIN YA ZHOU V.132</text> <text top=\"454\" left=\"12\" width=\"96\" height=\"10\" font=\"0\">PORT OF DISCHARGE</text> <text top=\"467\" left=\"15\" width=\"237\" height=\"15\" font=\"4\">MANZANILLO              </text> <text top=\"8\" left=\"183\" width=\"502\" height=\"16\" font=\"5\"> ARGUS LOGISTICA LTDA.   </text> <text top=\"75\" left=\"445\" width=\"136\" height=\"17\" font=\"2\">ARG 753                 </text> <text top=\"76\" left=\"14\" width=\"132\" height=\"14\" font=\"3\"><b>ALUSA CHILE S.A.</b></text> ";
        String THREE= "<text top=\"94\" left=\"14\" width=\"353\" height=\"14\" font=\"3\"><b>AVDA. PDTE. EDUARDO FREI MONTALVA N°9160</b></text> <text top=\"112\" left=\"14\" width=\"127\" height=\"14\" font=\"3\"><b>SANTIAGO CHILE</b></text> <text top=\"129\" left=\"14\" width=\"229\" height=\"14\" font=\"3\"><b>RUT : 89.010.400-2  F:226793200</b></text> <text top=\"60\" left=\"445\" width=\"93\" height=\"10\" font=\"0\">DOCUMENT NUMBER</text> <text top=\"61\" left=\"13\" width=\"39\" height=\"10\" font=\"0\">SHIPPER</text> <text top=\"167\" left=\"12\" width=\"54\" height=\"10\" font=\"0\">CONSIGNEE</text> <text top=\"182\" left=\"13\" width=\"229\" height=\"14\" font=\"3\"><b>DELIMEX DE MEXICO SA DE CV</b></text> <text top=\"199\" left=\"13\" width=\"413\" height=\"14\" font=\"3\"><b>AVDA PERIFERICO SUR 7980  EDIFICIO 3D SANTA MARIA</b></text> <text top=\"217\" left=\"13\" width=\"377\" height=\"14\" font=\"3\"><b>TEQUEPEXPAN, 45601 MEXICO RFC:DME920609BE5</b></text>";
        String FOUR= "<text top=\"235\" left=\"13\" width=\"209\" height=\"14\" font=\"3\"><b>CIUDAD DE MEXICO/MEXICO</b></text> <text top=\"37\" left=\"7\" width=\"143\" height=\"16\" font=\"6\"><b>BILL OF LADING</b></text> <text top=\"38\" left=\"179\" width=\"469\" height=\"12\" font=\"7\"><b>FOR COMBINED TRANSPORT SHIPMENT OR PORT TO PORT SHIPMENT</b></text> <text top=\"325\" left=\"445\" width=\"135\" height=\"10\" font=\"0\">For Release Of &gt;Goods Apply To:</text> <text top=\"115\" left=\"443\" width=\"214\" height=\"17\" font=\"2\"> ARGUS LOGISTICA LIMITADA</text> <text top=\"132\" left=\"443\" width=\"245\" height=\"17\" font=\"2\">ALMIRANTE SEÑORET 151 OF 113</text> <text top=\"150\" left=\"443\" width=\"142\" height=\"17\" font=\"2\">VALPARAISO/CHILE</text> <text top=\"104\" left=\"445\" width=\"75\" height=\"10\" font=\"0\">Export References</text> <text top=\"274\" left=\"444\" width=\"110\" height=\"10\" font=\"0\">Point And Country of Origin</text> <text top=\"187\" left=\"445\" width=\"71\" height=\"10\" font=\"0\">Forwarding Agent</text> <text top=\"199\" left=\"444\" width=\"347\" height=\"17\" font=\"2\">RH SHIPPING &amp; CHARTERING S. DE R.L. DE C.V.</text> <text top=\"216\" left=\"444\" width=\"371\" height=\"17\" font=\"2\">AVDA PASEO DE LA REFORMA N° 222 PISO 15 COL.</text> <text top=\"233\" left=\"444\" width=\"286\" height=\"17\" font=\"2\">JUAREZ CIUDDA DE MEXICO C.P. 06600</text>";
        String FIVE="<text top=\"250\" left=\"444\" width=\"208\" height=\"17\" font=\"2\">CIUDAD DE MEXICO/MEXICO</text> <text top=\"60\" left=\"623\" width=\"54\" height=\"10\" font=\"0\">B/L NUMBER</text> <text top=\"76\" left=\"621\" width=\"191\" height=\"17\" font=\"2\">ARGEM20170548           </text> <text top=\"1019\" left=\"499\" width=\"231\" height=\"12\" font=\"8\">whitch being accomplished the other(s) to be void.</text> <text top=\"1004\" left=\"499\" width=\"297\" height=\"12\" font=\"8\">and date have been signed in then number stated above, one of </text> <text top=\"989\" left=\"499\" width=\"278\" height=\"12\" font=\"8\">In witness where of the original Bill of Lading all of this tenor </text> <text top=\"975\" left=\"499\" width=\"225\" height=\"12\" font=\"8\">Consignee agree to accepting this Bill of Lading. </text> <text top=\"960\" left=\"499\" width=\"261\" height=\"12\" font=\"8\">out the reverse side hereof, to whitch the Shipper and/or </text> <text top=\"946\" left=\"499\" width=\"296\" height=\"12\" font=\"8\">always to the exceptions, limitations, conditions and liberties set </text> <text top=\"931\" left=\"499\" width=\"305\" height=\"12\" font=\"8\">of discharge or please of delivery, whichever is applicable, subject </text> <text top=\"916\" left=\"499\" width=\"289\" height=\"12\" font=\"8\">stated. The goods to be delivered at the above mentioned port </text> <text top=\"902\" left=\"499\" width=\"284\" height=\"12\" font=\"8\">above in apparent good order and condition unless otherwise </text> <text top=\"887\" left=\"499\" width=\"299\" height=\"12\" font=\"8\">to please of delivery, where stated above, the goods as specified </text> <text top=\"872\" left=\"499\" width=\"307\" height=\"12\" font=\"8\">procurement of precarriage from please of receip and on-carriage </text> <text top=\"858\" left=\"499\" width=\"260\" height=\"12\" font=\"8\">of loading and port of discharge, and for arrangement or </text> <text top=\"843\" left=\"499\" width=\"289\" height=\"12\" font=\"8\">Received by Carrier for shipment by ocean vesel between port </text> <text top=\"1050\" left=\"558\" width=\"437\" height=\"11\" font=\"1\"><b>SAN ANTONIO 27 DE MAYO 2018                                                                         </b></text> <text top=\"1050\" left=\"498\" width=\"42\" height=\"10\" font=\"0\">DATED AT</text> <text top=\"1070\" left=\"498\" width=\"12\" height=\"10\" font=\"0\">BY</text>";


    }
    /**
     *
     * @param document
     * @throws DocumentException
     */
    private void createTable(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();

        creteEmptyLine(paragraph, 2);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(2);

        PdfPCell c1 = new PdfPCell(new Phrase("Tipo"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fecha despacho"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (int i = 0; i < 5; i++) {
            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell("Carta");
            table.addCell("23/03/05");
            table.addCell("23/05/06");
            table.addCell("Direccion");
            table.addCell("2000");
        }

        document.add(table);
    }

}
