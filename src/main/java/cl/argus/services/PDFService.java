package cl.argus.services;

import cl.argus.controllers.PDFController;
import cl.argus.models.BLMaster;
import cl.argus.repositories.BLHouseRepository;
import cl.argus.repositories.BLMasterRepository;
import cl.argus.repositories.CargamentRepository;
import cl.argus.repositories.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/pdf")
public class PDFService {
    @Autowired
    BLHouseRepository blHouseRepository;
    @Autowired
    BLMasterRepository blMasterRepository;
    @Autowired
    CargamentRepository cargamentRepository;
    @Autowired
    IngresoRepository ingresoRepository;
    /*
     *
     * Descripci&oacute;n: Servicio que permite descargar pdf
     */
    @RequestMapping(value = "/download/{numeroOperacion}/{numeroBLHouse}",method = RequestMethod.GET)
    public void downloadPDF(HttpServletRequest request, HttpServletResponse response, @PathVariable("numeroOperacion") int numeroOperacion, @PathVariable("numeroBLHouse") int numeroBLHouse) throws IOException {

        final ServletContext servletContext = request.getSession().getServletContext();
        final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        final String temperotyFilePath = tempDirectory.getAbsolutePath();

        String fileName = numeroOperacion+".pdf";
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename="+ fileName);

        try {
            PDFController createpdf = new PDFController();
            createpdf.createPDF(temperotyFilePath+"\\"+fileName, numeroOperacion, numeroBLHouse,blHouseRepository, blMasterRepository, cargamentRepository, ingresoRepository);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos = convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+fileName);
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    /*
     * Descripcion: Metodo complementario para descarga de pdf que permite convertir el archivo en un ByteArray
     * para permitir su salida y descarga.
     */
    private ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {

        InputStream inputStream = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            inputStream = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos;
    }

}