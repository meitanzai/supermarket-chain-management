package com.cqupt.th.supermarket.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 *
 *
 * @author TianHong
 * @date 2023/03/18
 */
public class ExceptionUtil {
    public static String getMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }

        }
        return sw.toString();
    }
}
