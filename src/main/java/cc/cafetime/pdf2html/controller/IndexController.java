package cc.cafetime.pdf2html.controller;

import cc.cafetime.pdf2html.pdf2htmlEX.Pdf2HTML;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by liujing on 16/6/19.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("uploadfile") MultipartFile uploadfile, final HttpSession session) {

        try {
            // Get the filename and build the local file path (be sure that the
            // application have write permissions on such directory)
            final String oldFilename = uploadfile.getOriginalFilename();
            String fileName = String.valueOf(new Date().getTime());
            String fileFullName = fileName + ".pdf";
            String directory = "/web/pdf/uploads";
            final String filepath = Paths.get(directory, fileFullName).toString();
            final String destpath = Paths.get(directory, fileName).toString();
            new File(destpath).mkdirs();
            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(uploadfile.getBytes());
            stream.close();
            Thread t = new Thread() {
                public void run() {
                    Pdf2HTML.convertPDF2HTML(filepath, destpath, session);
                }
            };
            t.start();
            session.setAttribute("fileName", oldFilename);
            session.setAttribute("link",  fileName + "/" + fileName + ".html");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    } // method uploadFile


    @RequestMapping(value = "/process", method = RequestMethod.GET)
    @ResponseBody
    public String process(final HttpSession session) {
        String process = (String) session.getAttribute("process");
        session.setAttribute("process", "");
        return process;
    }

    @RequestMapping(value = "/getlink", method = RequestMethod.GET)
    @ResponseBody
    public String getlink(final HttpSession session) {
        String link = (String) session.getAttribute("link");
        return link;
    }
}

