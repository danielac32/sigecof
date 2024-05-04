
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


/**
 *
 * @author daniel
 */
public class consultas {
/***************************************************************************************/
public int sql_pendientes(String url,String name,String pass,String query1,boolean filter){
   System.out.println("\u001B[42m Connection : "+url+"\033[0m"); 
  
   Connection con = null;  
   int count=0;
   String []campos = {"ANHO","ORGANISMO","FUENTE","PARTIDA","FONDO","ORDEN","ESTADO","CUENTA","BENEFICIARIO","FECHA_MODIFICACION","OBSERVACION","MONTO"};
   try{ 
            con = DriverManager.getConnection(url,name,pass);  
            System.out.println("\u001B[43m Connection created\033[0m");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(query1);  
            int rowCount = 1;
            int rowCount2 = 0; 
            int rowCount3 = 0;
            int rowCount4 = 0;   
            int count2=0;
            int count3=0;
            int count4=0;
            int count5=0;
                /************************************************************/
            try{  //try para crear el archivo

                    HSSFWorkbook workbook = new HSSFWorkbook();   
                    HSSFSheet sheet1 = workbook.createSheet("sheet1");   
                    HSSFSheet sheet2 = workbook.createSheet("sheet2");   
                    HSSFSheet sheet3 = workbook.createSheet("sheet3");   
                    HSSFSheet sheet4 = workbook.createSheet("sheet4");  

                    HSSFRow rowhead = sheet1.createRow(0);  
                    for (int i=0; i<12;i++ ) {
                        rowhead.createCell(i).setCellValue(campos[i]);  
                    }


                    while(rs.next()){
                        int anho = rs.getInt("ANHO");
                        String orga = rs.getString("ORGANISMO");
                        String fuente = rs.getString("FUENTE");
                        String partida = rs.getString("PARTIDA");
                        String fondo = rs.getString("FONDO");
                        float orden = rs.getFloat("ORDEN");
                        int estado = rs.getInt("ESTADO");
                        String cuenta = rs.getString("CUENTA");
                        String benef = rs.getString("BENEFICIARIO");
                        String fecha_mod = rs.getString("FECHA_MODIFICACION");
                        String obse = rs.getString("OBSERVACION");
                        float monto = rs.getFloat("MONTO");

                        if(filter){
                            if(new utils().filtro1(obse)){
                               continue;
                            }
                            if(new utils().filtro2(obse)){
                               continue;
                            }
                            if(new utils().filtro3(obse)){
                               continue;
                            }
                         }



                        if (count < 65000){
                            rowhead = sheet1.createRow(rowCount); 
                            rowhead.createCell(0).setCellValue(anho);  
                            rowhead.createCell(1).setCellValue(orga);  
                            rowhead.createCell(2).setCellValue(fuente);  
                            rowhead.createCell(3).setCellValue(partida);  
                            rowhead.createCell(4).setCellValue(fondo);
                            rowhead.createCell(5).setCellValue(orden);  
                            rowhead.createCell(6).setCellValue(estado);  
                            rowhead.createCell(7).setCellValue(cuenta);  
                            rowhead.createCell(8).setCellValue(benef);  
                            rowhead.createCell(9).setCellValue(fecha_mod);
                            rowhead.createCell(10).setCellValue(obse);  
                            rowhead.createCell(11).setCellValue(monto);
                            rowCount++;
                        }else if(count > 65000 && count < 130000 ){
                            rowhead = sheet2.createRow(rowCount2); 
                            rowhead.createCell(0).setCellValue(anho);  
                            rowhead.createCell(1).setCellValue(orga);  
                            rowhead.createCell(2).setCellValue(fuente);  
                            rowhead.createCell(3).setCellValue(partida);  
                            rowhead.createCell(4).setCellValue(fondo);
                            rowhead.createCell(5).setCellValue(orden);  
                            rowhead.createCell(6).setCellValue(estado);  
                            rowhead.createCell(7).setCellValue(cuenta);  
                            rowhead.createCell(8).setCellValue(benef);  
                            rowhead.createCell(9).setCellValue(fecha_mod);
                            rowhead.createCell(10).setCellValue(obse);  
                            rowhead.createCell(11).setCellValue(monto);
                            rowCount2++;
                        }else if(count > 130000 && count < 195000 ){
                            rowhead = sheet3.createRow(rowCount3); 
                            rowhead.createCell(0).setCellValue(anho);  
                            rowhead.createCell(1).setCellValue(orga);  
                            rowhead.createCell(2).setCellValue(fuente);  
                            rowhead.createCell(3).setCellValue(partida);  
                            rowhead.createCell(4).setCellValue(fondo);
                            rowhead.createCell(5).setCellValue(orden);  
                            rowhead.createCell(6).setCellValue(estado);  
                            rowhead.createCell(7).setCellValue(cuenta);  
                            rowhead.createCell(8).setCellValue(benef);  
                            rowhead.createCell(9).setCellValue(fecha_mod);
                            rowhead.createCell(10).setCellValue(obse);  
                            rowhead.createCell(11).setCellValue(monto);
                            rowCount3++;
                        }else if(count > 195000 && count < 260000 ){
                            rowhead = sheet4.createRow(rowCount4); 
                            rowhead.createCell(0).setCellValue(anho);  
                            rowhead.createCell(1).setCellValue(orga);  
                            rowhead.createCell(2).setCellValue(fuente);  
                            rowhead.createCell(3).setCellValue(partida);  
                            rowhead.createCell(4).setCellValue(fondo);
                            rowhead.createCell(5).setCellValue(orden);  
                            rowhead.createCell(6).setCellValue(estado);  
                            rowhead.createCell(7).setCellValue(cuenta);  
                            rowhead.createCell(8).setCellValue(benef);  
                            rowhead.createCell(9).setCellValue(fecha_mod);
                            rowhead.createCell(10).setCellValue(obse);  
                            rowhead.createCell(11).setCellValue(monto);
                            rowCount4++;
                        }
                        count++;
                        count2++;
                        count3++;
                        count4++;
                        count5++;
                    }
                    utils c = new utils();
                    String namefile = c.save_file_dialog();
                    FileOutputStream fileOut = new FileOutputStream(namefile); 
                    workbook.write(fileOut);  
                    fileOut.close();   
                    workbook.close();
                    con.close(); 
                    System.out.println("\u001B[43m Connection closed "+ count +"\033[0m");  
                    return count;
            }catch(Exception e){// si algo sale mal creando el archivo
                //JOptionPane.showMessageDialog(null, e);
                System.out.println(e+ "-"+ count);
                return -1;
            }
    }catch(Exception e){ 
            System.out.println("\u001B[41m no se pudo conectar \033[0m");
            System.out.println(e);
            //JOptionPane.showMessageDialog(null, "No se pudo conectar: "+e);
            return -2;
    }
}
/******************************************************************************/

public int sql_pagadas(String url,String name,String pass,String query1,boolean filter){
        System.out.println("\u001B[42m Connection : "+url+"\033[0m"); 
        Connection con = null;  
        int count=0;
        int rowCount = 1;
        int rowCount2 = 0; 
        int rowCount3 = 0;
        int rowCount4 = 0;   
 
        String []campos = {"ANHO","ORGANISMO","FUENTE","PARTIDA","FONDO","ORDEN","ESTADO","CUENTA","BENEFICIARIO","PAGADA","OBSERVACION","MONTO"};

        try{  
            con = DriverManager.getConnection(url,name,pass);  
            System.out.println("\u001B[43m Connection created\033[0m");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(query1);  

            /************************************************************/
            try{  //try para crear el archivo
                HSSFWorkbook workbook = new HSSFWorkbook();   
                HSSFSheet sheet1 = workbook.createSheet("sheet1");   
                HSSFSheet sheet2 = workbook.createSheet("sheet2");   
                HSSFSheet sheet3 = workbook.createSheet("sheet3");   
                HSSFSheet sheet4 = workbook.createSheet("sheet4");  
                HSSFRow rowhead = sheet1.createRow(0);  
                for (int i=0; i<12;i++ ) {
                    rowhead.createCell(i).setCellValue(campos[i]);  
                }

                while(rs.next()){
                    int anho = rs.getInt("ANHO");
                    String orga = rs.getString("ORGANISMO");
                    String fuente = rs.getString("FUENTE");
                    String partida = rs.getString("PARTIDA");
                    String fondo = rs.getString("FONDO");
                    float orden = rs.getFloat("ORDEN");
                    int estado = rs.getInt("ESTADO");
                    String cuenta = rs.getString("CUENTA");
                    String benef = rs.getString("BENEFICIARIO");
                    String pagada = rs.getString("PAGADA");
                    String obse = rs.getString("OBSERVACION");
                    float monto = rs.getFloat("MONTO");

                    if(filter){
                        if(new utils().filtro1(obse)){
                           continue;
                        }
                        if(new utils().filtro2(obse)){
                           continue;
                        }
                        if(new utils().filtro3(obse)){
                           continue;
                        }
                     }
                        
                    if (count < 65000){
                        rowhead = sheet1.createRow(rowCount); 
                        rowhead.createCell(0).setCellValue(anho);  
                        rowhead.createCell(1).setCellValue(orga);  
                        rowhead.createCell(2).setCellValue(fuente);  
                        rowhead.createCell(3).setCellValue(partida);  
                        rowhead.createCell(4).setCellValue(fondo);
                        rowhead.createCell(5).setCellValue(orden);  
                        rowhead.createCell(6).setCellValue(estado);  
                        rowhead.createCell(7).setCellValue(cuenta);  
                        rowhead.createCell(8).setCellValue(benef);  
                        rowhead.createCell(9).setCellValue(pagada);
                        rowhead.createCell(10).setCellValue(obse);  
                        rowhead.createCell(11).setCellValue(monto);
                        rowCount++;
                    }else if(count > 65000 && count < 130000 ){
                        rowhead = sheet2.createRow(rowCount2); 
                        rowhead.createCell(0).setCellValue(anho);  
                        rowhead.createCell(1).setCellValue(orga);  
                        rowhead.createCell(2).setCellValue(fuente);  
                        rowhead.createCell(3).setCellValue(partida);  
                        rowhead.createCell(4).setCellValue(fondo);
                        rowhead.createCell(5).setCellValue(orden);  
                        rowhead.createCell(6).setCellValue(estado);  
                        rowhead.createCell(7).setCellValue(cuenta);  
                        rowhead.createCell(8).setCellValue(benef);  
                        rowhead.createCell(9).setCellValue(pagada);
                        rowhead.createCell(10).setCellValue(obse);  
                        rowhead.createCell(11).setCellValue(monto);
                        rowCount2++;
                    }else if(count > 130000 && count < 195000 ){
                        rowhead = sheet3.createRow(rowCount3); 
                        rowhead.createCell(0).setCellValue(anho);  
                        rowhead.createCell(1).setCellValue(orga);  
                        rowhead.createCell(2).setCellValue(fuente);  
                        rowhead.createCell(3).setCellValue(partida);  
                        rowhead.createCell(4).setCellValue(fondo);
                        rowhead.createCell(5).setCellValue(orden);  
                        rowhead.createCell(6).setCellValue(estado);  
                        rowhead.createCell(7).setCellValue(cuenta);  
                        rowhead.createCell(8).setCellValue(benef);  
                        rowhead.createCell(9).setCellValue(pagada);
                        rowhead.createCell(10).setCellValue(obse);  
                        rowhead.createCell(11).setCellValue(monto);
                        rowCount3++;
                    }else if(count > 195000 && count < 260000 ){
                        rowhead = sheet4.createRow(rowCount4); 
                        rowhead.createCell(0).setCellValue(anho);  
                        rowhead.createCell(1).setCellValue(orga);  
                        rowhead.createCell(2).setCellValue(fuente);  
                        rowhead.createCell(3).setCellValue(partida);  
                        rowhead.createCell(4).setCellValue(fondo);
                        rowhead.createCell(5).setCellValue(orden);  
                        rowhead.createCell(6).setCellValue(estado);  
                        rowhead.createCell(7).setCellValue(cuenta);  
                        rowhead.createCell(8).setCellValue(benef);  
                        rowhead.createCell(9).setCellValue(pagada);
                        rowhead.createCell(10).setCellValue(obse);  
                        rowhead.createCell(11).setCellValue(monto);
                        rowCount4++;
                    }
                    count++;
                   }
                   int j=0;
                   String namefile = new utils().save_file_dialog();

                   /*String[] arrOfStr = namefile.split("/");
                   for (String a : arrOfStr){
                        j++;
                   }
                   String namefile2 = arrOfStr[j-1].replace("/","-");
                   String namefile3 = namefile.replace(namefile2,"");

                   System.out.println(namefile + " " + namefile2 + " " + namefile3);*/

                   FileOutputStream fileOut = new FileOutputStream(namefile);    
                   workbook.write(fileOut);  
                   fileOut.close();   
                   workbook.close();
                   con.close(); 
                   System.out.println("\u001B[43m Connection closed "+ count +"\033[0m");  
                   return count;
            }catch(Exception e){
                //JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
                return -1;
            }
        
        }catch(Exception e){ 
               System.out.println("\u001B[41m no se pudo conectar \033[0m");
               System.out.println(e);
               return -2;
           //JOptionPane.showMessageDialog(null, "No se pudo conectar: "+e);
        } 
    }
/******************************************************************************/

public int pagadas_retenciones(String url,String name,String pass,String query1){
        System.out.println("\u001B[42m Connection : "+url+"\033[0m"); 
        Connection con = null;  
        int count=0;
        int rowCount = 1;
        int rowCount2 = 0; 
        int rowCount3 = 0;
        int rowCount4 = 0;   
 
        String []campos = {"PRESUPUESTO", 
                           "ORGANISMO",
                           "COD_UNIDAD_ADMINISTRADORA", 
                           "DESC_UNIDAD_ADMINISTRADORA", 
                           "ORDEN", 
                           "BENEFICIARIO", 
                           "R.RETE_ID||'-'||RT.DENOMINACION", 
                           "RIF",
                           "MONTO_ORDEN_ANT",
                           "MONTO_ORDEN", 
                           "MONTO_1_X_500_ANT", 
                           "MONTO_1_X_500", 
                           "FECHA_PAGO"};
        


        try{  
            con = DriverManager.getConnection(url,name,pass);  
            System.out.println("\u001B[43m Connection created\033[0m");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(query1);  

            /************************************************************/
            try{  //try para crear el archivo
                HSSFWorkbook workbook = new HSSFWorkbook();   
                HSSFSheet sheet1 = workbook.createSheet("sheet1");   
                HSSFSheet sheet2 = workbook.createSheet("sheet2");   
                HSSFSheet sheet3 = workbook.createSheet("sheet3");   
                HSSFSheet sheet4 = workbook.createSheet("sheet4");  
                HSSFRow rowhead = sheet1.createRow(0);  
                for (int i=0; i<13;i++ ) {
                    rowhead.createCell(i).setCellValue(campos[i]);  
                }

                while(rs.next()){
                       float presupuesto=rs.getFloat("PRESUPUESTO");
                       String organismo= rs.getString("ORGANISMO");
                       String cod_unidad_adm=rs.getString("COD_UNIDAD_ADMINISTRADORA");
                       String desc_unidad_adm=rs.getString("DESC_UNIDAD_ADMINISTRADORA"); 
                       float orden= rs.getFloat("ORDEN");
                       String benef=rs.getString("BENEFICIARIO");
                       String raro=rs.getString("R.RETE_ID||'-'||RT.DENOMINACION"); 
                       String rif=rs.getString("RIF");
                       float monto1=rs.getFloat("MONTO_ORDEN_ANT");
                       float monto2=rs.getFloat("MONTO_ORDEN");
                       float monto3=rs.getFloat("MONTO_1_X_500_ANT"); 
                       float monto4=rs.getFloat("MONTO_1_X_500");
                       String fecha=rs.getString("FECHA_PAGO");

                    if (count < 65000){
                        rowhead = sheet1.createRow(rowCount); 
                        rowhead.createCell(0).setCellValue(presupuesto);  
                        rowhead.createCell(1).setCellValue(organismo);  
                        rowhead.createCell(2).setCellValue(cod_unidad_adm);  
                        rowhead.createCell(3).setCellValue(desc_unidad_adm);  
                        rowhead.createCell(4).setCellValue(orden);
                        rowhead.createCell(5).setCellValue(benef);  
                        rowhead.createCell(6).setCellValue(raro);  
                        rowhead.createCell(7).setCellValue(rif);  
                        rowhead.createCell(8).setCellValue(monto1);  
                        rowhead.createCell(9).setCellValue(monto2);
                        rowhead.createCell(10).setCellValue(monto3);  
                        rowhead.createCell(11).setCellValue(monto4);
                        rowhead.createCell(12).setCellValue(fecha);
                        rowCount++;
                    }else if(count > 65000 && count < 130000 ){
                        rowhead = sheet2.createRow(rowCount2); 
                        rowhead.createCell(0).setCellValue(presupuesto);  
                        rowhead.createCell(1).setCellValue(organismo);  
                        rowhead.createCell(2).setCellValue(cod_unidad_adm);  
                        rowhead.createCell(3).setCellValue(desc_unidad_adm);  
                        rowhead.createCell(4).setCellValue(orden);
                        rowhead.createCell(5).setCellValue(benef);  
                        rowhead.createCell(6).setCellValue(raro);  
                        rowhead.createCell(7).setCellValue(rif);  
                        rowhead.createCell(8).setCellValue(monto1);  
                        rowhead.createCell(9).setCellValue(monto2);
                        rowhead.createCell(10).setCellValue(monto3);  
                        rowhead.createCell(11).setCellValue(monto4);
                        rowhead.createCell(12).setCellValue(fecha);
                        rowCount2++;
                    }else if(count > 130000 && count < 195000 ){
                        rowhead = sheet3.createRow(rowCount3); 
                        rowhead.createCell(0).setCellValue(presupuesto);  
                        rowhead.createCell(1).setCellValue(organismo);  
                        rowhead.createCell(2).setCellValue(cod_unidad_adm);  
                        rowhead.createCell(3).setCellValue(desc_unidad_adm);  
                        rowhead.createCell(4).setCellValue(orden);
                        rowhead.createCell(5).setCellValue(benef);  
                        rowhead.createCell(6).setCellValue(raro);  
                        rowhead.createCell(7).setCellValue(rif);  
                        rowhead.createCell(8).setCellValue(monto1);  
                        rowhead.createCell(9).setCellValue(monto2);
                        rowhead.createCell(10).setCellValue(monto3);  
                        rowhead.createCell(11).setCellValue(monto4);
                        rowhead.createCell(12).setCellValue(fecha);
                        rowCount3++;
                    }else if(count > 195000 && count < 260000 ){
                        rowhead = sheet4.createRow(rowCount4); 
                        rowhead.createCell(0).setCellValue(presupuesto);  
                        rowhead.createCell(1).setCellValue(organismo);  
                        rowhead.createCell(2).setCellValue(cod_unidad_adm);  
                        rowhead.createCell(3).setCellValue(desc_unidad_adm);  
                        rowhead.createCell(4).setCellValue(orden);
                        rowhead.createCell(5).setCellValue(benef);  
                        rowhead.createCell(6).setCellValue(raro);  
                        rowhead.createCell(7).setCellValue(rif);  
                        rowhead.createCell(8).setCellValue(monto1);  
                        rowhead.createCell(9).setCellValue(monto2);
                        rowhead.createCell(10).setCellValue(monto3);  
                        rowhead.createCell(11).setCellValue(monto4);
                        rowhead.createCell(12).setCellValue(fecha);
                        rowCount4++;
                    }
                    count++;
                   }
                   int j=0;
                   String namefile = new utils().save_file_dialog();

                   /*String[] arrOfStr = namefile.split("/");
                   for (String a : arrOfStr){
                        j++;
                   }
                   String namefile2 = arrOfStr[j-1].replace("/","-");
                   String namefile3 = namefile.replace(namefile2,"");

                   System.out.println(namefile + " " + namefile2 + " " + namefile3);*/

                   FileOutputStream fileOut = new FileOutputStream(namefile);    
                   workbook.write(fileOut);  
                   fileOut.close();   
                   workbook.close();
                   con.close(); 
                   System.out.println("\u001B[43m Connection closed "+ count +"\033[0m");  
                   return count;
            }catch(Exception e){
                //JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
                return -1;
            }
        
        }catch(Exception e){ 
               System.out.println("\u001B[41m no se pudo conectar \033[0m");
               System.out.println(e);
               return -2;
           //JOptionPane.showMessageDialog(null, "No se pudo conectar: "+e);
        } 
    }
/******************************************************************************/


}
