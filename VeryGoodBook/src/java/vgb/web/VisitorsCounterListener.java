/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
@WebListener
public class VisitorsCounterListener implements HttpSessionListener, ServletContextListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        int visitorsCount = 0;
        ServletContext application = se.getSession().getServletContext();
        Integer count = (Integer) application.getAttribute("app.visitors.count");
        if (count != null) {
            visitorsCount = count;
        }

        application.setAttribute("app.visitors.count", ++visitorsCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("vgb context Initialized...");
        Properties props = new Properties();
        System.out.println(System.getProperty("user.dir"));

        ServletContext application = sce.getServletContext();
        System.out.println(application.getRealPath("/WEB-INF/vgb_attributes.properties"));
        String path = application.getRealPath("/WEB-INF/vgb_attributes.properties");
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            try (FileInputStream in = new FileInputStream(file);
                    BufferedInputStream bIn = new BufferedInputStream(in);
                    InputStreamReader reader = new InputStreamReader(bIn, "UTF-8");) {
                props.load(reader);
                Enumeration<String> names = (Enumeration<String>) props.propertyNames();
                while (names.hasMoreElements()) {
                    String name = names.nextElement();
                    String value = props.getProperty(name);
                    System.out.println(name + ":" + value);
                    if (value != null && value.matches("\\d+")) {
                        application.setAttribute(name, Integer.parseInt(value));
                    } else {
                        application.setAttribute(name, value);
                    }
                }
                System.out.println("contextInitialized-讀取檔案成功!");
                application.log("contextInitialized-讀取檔案成功!");
            } catch (IOException ex) {
                System.out.println("contextInitialized-讀取檔案失敗:" + file.getAbsolutePath());
                application.log("contextInitialized-讀取檔案失敗!", ex);
            }
        } else {
            System.out.println("contextInitialized-檔案不存在:" + file.getAbsolutePath());
            application.log("contextInitialized-檔案不存在:" + file.getAbsolutePath());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("vgb context Destroyed...");
        Properties props = new Properties();
        ServletContext application = sce.getServletContext();

        Enumeration<String> names = application.getAttributeNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = application.getAttribute(name).toString();
            if (name.indexOf("app.") == 0) {
                props.setProperty(name, value);
            }
        }
        
        String path = application.getRealPath("/WEB-INF/vgb_attributes.properties");
        File file = new File(path);        
        try(FileOutputStream out = new FileOutputStream(file);
                BufferedOutputStream bOut = new BufferedOutputStream(out);
                OutputStreamWriter writer = new OutputStreamWriter(bOut, "UTF-8")
                ){
            props.store(writer, "Application屬性存檔成功!");
            application.log("Application屬性存檔成功: " + file.getAbsolutePath());
        }catch(IOException ex){
            application.log("Application屬性存檔失敗: " + file.getAbsolutePath(), ex);
        }

    }
}
