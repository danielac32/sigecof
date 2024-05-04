
package Test;
import java.sql.*;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import javax.swing.JOptionPane;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

 
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook; 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author daniel
 */
public class utils {

    

    String get_organismo(int name){

        BufferedReader reader;
        int count=0;

        String []organismos = new String[60];
        try {
            reader = new BufferedReader(new FileReader("utils/organismos.txt"));
            String line;
            while ((line=reader.readLine())!= null) {
                organismos[count]=line;
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return organismos[new consultas_2().get_alias(name)];
    }


    String get_host(){
        BufferedReader reader;
        String [] ln = new String[10];
        int count=0;
        String host=null;
        try {
            reader = new BufferedReader(new FileReader("connection/config.txt"));
            String line;

            while ((line=reader.readLine())!= null) {
                ln[count]=line;
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        for (int i=0; i<count;i++ ) {
            String obj = ln[i].substring(0,1);
            if(obj.equals("*")){
                host = ln[i].replace("*","");
                break;
            }
        }
        return host;
    }
    String get_name(){
        BufferedReader reader;
        String [] ln = new String[10];
        int count=0;
        try {
            reader = new BufferedReader(new FileReader("connection/config.txt"));
            String line;

            while ((line=reader.readLine())!= null) {
                ln[count]=line;
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return ln[3];
    }
    String get_pass(){
        BufferedReader reader;
        String [] ln = new String[10];
        int count=0;
        try {
            reader = new BufferedReader(new FileReader("connection/config.txt"));
            String line;

            while ((line=reader.readLine())!= null) {
                ln[count]=line;
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return ln[4];
    }
    String get_date(String m){
        String str_fecha=null;
        try{
            String temp = JOptionPane.showInputDialog("Ingrese fecha "+ m + " (mm/dd/yyyy): ");
            //mensaje para capturar la fecha 
            int month = Integer.parseInt(temp.substring(0,2));
            int day = Integer.parseInt(temp.substring(3,5));
            int year = Integer.parseInt(temp.substring(6));
            str_fecha = "'"+month+"/"+day+"/"+year+"'";
        }catch(Exception e){ 
            return null;
        }
        return str_fecha;
    }
   String save_file_dialog(){
       try{
           JFrame parentFrame = new JFrame();
           JFileChooser fileChooser = new JFileChooser();
           fileChooser.setDialogTitle("Guardar archivo");    
           int userSelection = fileChooser.showSaveDialog(parentFrame);
           if (userSelection == JFileChooser.APPROVE_OPTION) {
               File f = fileChooser.getSelectedFile();
               if (f.exists()) {
                   int result = JOptionPane.showConfirmDialog((Component) null, "El archivo ya existe, desea sobre reemplazar?","alert", JOptionPane.YES_NO_CANCEL_OPTION);
                   switch (result) {
                       case JOptionPane.YES_OPTION:
                           f.delete();
                           String namefile = ""+f.getAbsolutePath();
                           //f.delete();
                           //System.out.println(f.getName()+" "+namefile);
                           return namefile;
                       case JOptionPane.CANCEL_OPTION:
                           return null;
                       default:
                           return null;

                   }
               }else{//si no existe el archivo
                   String namefile = ""+f.getAbsolutePath() + ".xls";
                   return namefile;
               }
           }
       }catch(Exception e){
            System.out.println(e);
       }
       return null;
  }

 int test_connection(String url,String name,String pass){
    Connection con = null;  
    try{ 
       con = DriverManager.getConnection(url,name,pass);  
       //System.out.println("\u001B[43m Connection created\033[0m");  
       Statement stmt=con.createStatement();  
       con.close(); 
       return 0;
    }catch(Exception e){
        //JOptionPane.showMessageDialog(null, e);
       return -1;
    }
 }


 boolean check_filter(){
    int reply = JOptionPane.showConfirmDialog(null, "Desea aplicar filtros", "alert", JOptionPane.YES_NO_OPTION);
    if(reply == JOptionPane.YES_OPTION) {
       return true;
    }else {
       return false;
    }
 }

  boolean filtro1(String a){
       if(a.indexOf("regula")>-1 || a.indexOf("REGULA")>-1 || a.indexOf("Regula")>-1 ){
         return true;
       }else return false;
  }
  boolean filtro2(String a){
    if(a.indexOf("patria")>-1 || a.indexOf("PATRIA")>-1){
        return true;
    }else return false;
  }
  
  boolean filtro3(String a){

    if(a.indexOf("especial")>-1 || a.indexOf("ESPECIAL")>-1 || a.indexOf("Especial")>-1){
        return true;
    }else return false;
      /* int index = a.indexOf("ESPECIAL");
       int len = a.length();

       if(index > -1 && index < 1 || index == 1 || index == (len - 8) ){// al principio o final
          return true;
       }else return false;*/
  }



  //boolean filtro4(String a){
       /*int index = a.indexOf("especial");
       int len = a.length();

       if(index > -1 && index < 1 || index == 1 || index == (len - 8) ){// al principio o final
          return true;
       }else return false;*/
  //}

   String get_sql_pagadas(String file){
     try {
            String content2 = new String(Files.readAllBytes(Paths.get(file)));
            String str_fecha = new utils().get_date("Desde");
            String newContent = content2.replace(":PAR_DESDE",str_fecha);
            str_fecha = new utils().get_date("Hasta");
            String newContent2 = newContent.replace(":PAR_HASTA",str_fecha);
            return newContent2;
        }catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex);
        }
        return null;
   }


   String get_sql_pendientes(String file){
     try {
            String content2 = new String(Files.readAllBytes(Paths.get(file)));
            String str_fecha = new utils().get_date("Desde");
            String newContent = content2.replace(":PAR_DESDE",str_fecha);
            str_fecha = new utils().get_date("Hasta");
            String newContent2 = newContent.replace(":PAR_HASTA",str_fecha);
            return newContent2;
        }catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex);
        }
        return null;
   }
/******************************************************************************/



}
