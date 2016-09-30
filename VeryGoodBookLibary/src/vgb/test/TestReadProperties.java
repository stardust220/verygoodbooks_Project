/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestReadProperties {

    public static void main(String[] args) {
        System.out.println(System.getProperty("file.encoding")); //UTF-8
        Properties props = new Properties();
        //FileReader reader = new FileReader("test.properties") //無法指定編碼
        File file = new File("test.properties");
        if (file.exists() && file.isFile()) {
            try (FileInputStream in = new FileInputStream(file);
                    BufferedInputStream bin = new BufferedInputStream(in);
                    InputStreamReader reader = new InputStreamReader(bin, "UTF-8")) {
                props.load(reader);

                Enumeration<String> names = (Enumeration<String>) props.propertyNames();
                while (names.hasMoreElements()) {
                    String name = names.nextElement();
                    String value = props.getProperty(name);
                    System.out.println(name + ":" + value);
                }
            } catch (IOException ex) {
                Logger.getLogger(TestReadProperties.class.getName()).log(Level.SEVERE,
                        "找不到檔案:" + file.getAbsolutePath(), ex);
            }
        }
    }
}
