package cc.cafetime.pdf2html.pdf2htmlEX;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven on 16/6/20.
 */
public class Pdf2htmlEX {
    public static final int ON = 1;
    public static final int OFF =0;

    public static final String BG_TYPE_JPEG= "jpg";
    public static final String BG_TYPE_PNG= "png";

    private List<String> list = null;

    public Pdf2htmlEX(){
        list = new ArrayList<String>();
//		getList().add("/bin/sh");
//		getList().add("-c");
        getList().add("pdf2htmlEX");
    }

    public Pdf2htmlEX setFrom(int from) {
        this.getList().add("-f");
        this.getList().add(from+"");
        return this;
    }
    public Pdf2htmlEX setLength(int length) {
        this.getList().add("-l");
        this.getList().add(length+"");
        return this;
    }
    public Pdf2htmlEX setEmbedFont(int embedFont) {
        this.getList().add("--embed-font");
        this.getList().add(embedFont+"");
        return this;
    }
    public Pdf2htmlEX setEmbedCSS(int embedCSS) {
        this.getList().add("--embed-css");
        this.getList().add(embedCSS+"");
        return this;
    }
    public Pdf2htmlEX setEmbedImage(int embedImage) {
        this.getList().add("--embed-image");
        this.getList().add(embedImage+"");
        return this;
    }
    public Pdf2htmlEX setEmbedJavascript(int embedJavascript) {
        this.getList().add("--embed-javascript");
        this.getList().add(embedJavascript+"");
        return this;
    }
    public Pdf2htmlEX setDestDir(String destDir) {
        this.getList().add("--dest-dir");
        this.getList().add(destDir);
        return this;
    }
    public Pdf2htmlEX setFitWidth(int fitWidth) {
        this.getList().add("--fit-width");
        this.getList().add(fitWidth+"");
        return this;
    }
    public Pdf2htmlEX setBgFormat(String bgFormat) {
        this.getList().add("--bg-format");
        this.getList().add(bgFormat);
        return this;
    }
    public Pdf2htmlEX setProcessNontext(int processNontext) {
        this.getList().add("--process-nontext");
        this.getList().add(processNontext+"");
        return this;
    }
    public Pdf2htmlEX setProcessOutline(int processOutline) {
        this.getList().add("--process-outline");
        this.getList().add(processOutline+"");
        return this;
    }
    public Pdf2htmlEX setCorrentTextVisbility(int correntTextVisbility) {
        this.getList().add("--correct-text-visibility");
        this.getList().add(correntTextVisbility+"");
        return this;
    }
    public Pdf2htmlEX setSplitPage(int splitPage) {
        this.getList().add("--split-pages");
        this.getList().add(splitPage+"");
        return this;
    }
    public Pdf2htmlEX setPdfPath(String pdfPath) {
        this.getList().add(pdfPath);
        return this;
    }

    public List<String> getList() {
        return list;
    }
}
