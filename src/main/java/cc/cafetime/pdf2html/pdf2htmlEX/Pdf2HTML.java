package cc.cafetime.pdf2html.pdf2htmlEX;

import cc.cafetime.pdf2html.util.FileUtil;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by steven on 16/6/20.
 */
public class Pdf2HTML {


    public static int convertPDF2HTML(String sourcePath, String destPath,HttpSession session) {
        String destDir = "";
        if (destPath.lastIndexOf(".") != -1) {
            destDir = destPath.substring(0, destPath.lastIndexOf(".")) + "/";
            File f = new File(destDir);
            if (!f.exists()) {
                f.mkdirs();
            }
        } else {
            destDir = destPath;
        }
        int flag = excuteCommand(FileUtil.normalizePath(sourcePath), FileUtil.normalizePath(destDir),session);
        if (flag == 1) {
            return flag;
        }
        return flag;
    }


    public static int excuteCommand(String sourcePath, String destPath, HttpSession session) {
        File source = new File(sourcePath);
        if (!source.exists()) {
            return 1;
        }
        Pdf2htmlEX command = new Pdf2htmlEX();
        command.setEmbedFont(Pdf2htmlEX.OFF)
                .setEmbedImage(Pdf2htmlEX.OFF)
                .setEmbedJavascript(Pdf2htmlEX.OFF)
                .setDestDir(destPath)
                .setFitWidth(1024)
                .setBgFormat(Pdf2htmlEX.BG_TYPE_JPEG)
                .setProcessNontext(Pdf2htmlEX.ON)
                .setProcessOutline(Pdf2htmlEX.ON)
                .setCorrentTextVisbility(Pdf2htmlEX.ON)
                .setSplitPage(Pdf2htmlEX.ON)
                .setPdfPath(sourcePath);
        List<String> commandList = command.getList();
        try {
            ProcessBuilder pb = new ProcessBuilder(commandList);
            pb.redirectErrorStream(true);
            Process pro = pb.start();


            /*ReadInputStreamThread readInputStreamThread = new ReadInputStreamThread(pro.getInputStream());
            readInputStreamThread.start();

            ReadInputStreamThread readErrorStreamThread = new ReadInputStreamThread(pro.getErrorStream());
            readErrorStreamThread.start();
            */

            InputStreamReader ir=new InputStreamReader(pro.getInputStream());
            BufferedReader input = new BufferedReader (ir);
            String line;
            while ((line = input.readLine ()) != null) {
               session.setAttribute("process",session.getAttribute("process")+"\r\n"+line);
            }

            session.setAttribute("status","working");
            pro.waitFor();
            session.setAttribute("status","complate");
            return pro.exitValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
