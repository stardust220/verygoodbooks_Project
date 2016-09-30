/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestProperties {
    public static void main(String[] args) {
        Properties props = new Properties();        
        System.out.println(props.getProperty("mydata1", "456"));
        
        System.out.println(System.getProperty("user.dir")); //working path
        System.out.println(System.getProperty("user.name")); //user name
        System.out.println(System.getProperty("file.encoding")); //UTF-8
        
        props = System.getProperties();
        props.setProperty("mydata", "一佴参");        
        
        System.out.println(props.getProperty("mydata"));
        
        System.out.println(props.getProperty("user.dir")); //working path
        System.out.println(props.getProperty("user.name")); //user name
        System.out.println(props.getProperty("file.encoding")); //UTF-8
        
        //FileWriter writer = new FileWriter("test.properties"); //無法指定編碼
        File dir = new File("conf");
        if(!dir.exists()){
            dir.mkdir();
        }
        File file = new File(dir, "test.properties");
        try (FileOutputStream out = new FileOutputStream(file);
                BufferedOutputStream bout = new BufferedOutputStream(out);
                OutputStreamWriter writer = new OutputStreamWriter(bout, "UTF-8");){            
            props.store(writer, "測試如何寫入Properties");
            System.out.println(file.getAbsoluteFile());
        } catch (IOException ex) {
            Logger.getLogger(TestProperties.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
