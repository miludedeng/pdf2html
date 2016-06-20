package cc.cafetime.pdf2html.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 文件操作工具类
 *
 * @author 王育春<br>
 * @date 2006-7-12<br>
 * @email wangyc@zving.com<br>
 */
public class FileUtil {
    /**
     * 将文件路径规则化，去掉其中多余的/和\，去掉可能造成文件信息泄漏的../
     */
    public static String normalizePath(String path) {
        path = path.replace('\\', '/');
        path = replaceEx(path, "../", "/");
        path = replaceEx(path, "./", "/");
        if (path.endsWith("..")) {
            path = path.substring(0, path.length() - 2);
        }
        path = path.replaceAll("/+", "/");
        return path;
    }

    public static File normalizeFile(File f) {
        String path = f.getAbsolutePath();
        path = normalizePath(path);
        return new File(path);
    }

    private static String replaceEx(String str, String subStr, String reStr) {
        if (str == null || str.length() == 0 || reStr == null) {
            return str;
        }
        if (subStr == null || subStr.length() == 0 || subStr.length() > str.length()) {
            return str;
        }
        StringBuilder sb = null;
        int lastIndex = 0;
        while (true) {
            int index = str.indexOf(subStr, lastIndex);
            if (index < 0) {
                break;
            } else {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(str.substring(lastIndex, index));
                sb.append(reStr);
            }
            lastIndex = index + subStr.length();
        }
        if (lastIndex == 0) {
            return str;
        }
        sb.append(str.substring(lastIndex));
        return sb.toString();
    }

    /**
     * 得到文件名中的扩展名，不带圆点。
     */
    public static String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index < 0) {
            return null;
        }
        String ext = fileName.substring(index + 1);
        return ext.toLowerCase();
    }

}
