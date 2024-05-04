
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
import java.awt.List;
 
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

import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author daniel
 */

class MyRunnable implements Runnable {

    private int v;

    public MyRunnable(int v) {
        this.v = v;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            try {
                System.out.println("estoy aqui: "+v);
                v++;
            } catch (Exception ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}



class Pila {

    Stack<String> STACK;

    Pila()
    {
        STACK = new Stack<String>();
    }
    void PUSH(String p){
        STACK.push(p);
    }

    String POP(){
        return STACK.pop();
    }
    void display(){
        System.out.println(STACK);      
    }
    int size(){
        return STACK.size();
    }
}


class consultas_2 {


public int get_alias(int num){//retorna el alias a partir de el nombre orginal
    int []list = {1,2,3,6,8,10,13,21,23,25,26,32,33,34,36,37,38,39,41,44,45,46,52,54,57,58,59,63,65,69,71,72,74,75,76,80,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,100,101,102,103,104,105};
    for (int i=0;i<list.length;i++ ) {
        if(list[i]==num)return i;
    }
    return -1;
}
public int get_name(int num){//retorna el nombre orginal a partir del alias 
    int []list = {1,2,3,6,8,10,13,21,23,25,26,32,33,34,36,37,38,39,41,44,45,46,52,54,57,58,59,63,65,69,71,72,74,75,76,80,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,100,101,102,103,104,105};
    return list[num];
}

public int get_name_partidas(int num){
     int []list = {401,402,403,404,405,406,407,408,409,410,411};
     return list[num];
}
public int get_alias_partida(int num){
    int []list = {401,402,403,404,405,406,407,408,409,410,411};
    for (int i=0;i<list.length;i++ ) {
        if(list[i]==num)return i;
    }
    return -1;
}

public int get_partida(String partida){

    try{
        //int res=Integer.parseInt(partida.substring(0,3));
        if(partida.indexOf("401") >-1)return 401;
        else if(partida.indexOf("402") >-1)return 402;
        else if(partida.indexOf("403") >-1)return 403;
        else if(partida.indexOf("404") >-1)return 404;
        else if(partida.indexOf("405") >-1)return 405;
        else if(partida.indexOf("406") >-1)return 406;
        else if(partida.indexOf("407") >-1)return 407;
        else if(partida.indexOf("408") >-1)return 408;
        else if(partida.indexOf("409") >-1)return 409;
        else if(partida.indexOf("410") >-1)return 410;
        else if(partida.indexOf("411") >-1)return 411;
        //else if(partida == null ) return 401;
        //return res;
    }catch(Exception e){// si algo sale mal creando el archivo
        //System.out.println(partida +"---"+e);
        return 401;
    }
    return 0;
}
public int sql_pendientes(String url,String name,String pass,String query1,boolean filter){

   System.out.println("\u001B[42m Connection : "+url+"\033[0m"); 
  
   Connection con = null;  
   int count=0;
  // String []campos = {"ORGANISMO","401","402","403","404","405","406","407","408","409","410","411"};
   
   MyRunnable myRunnable = new MyRunnable(10);
   Thread t = new Thread(myRunnable);
   //t.start();
   

   String orga = null;
   String partida = null;
   String obse = null;
   float monto = 0;
   try{ 
            con = DriverManager.getConnection(url,name,pass);  
            System.out.println("\u001B[43m Connection created\033[0m");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(query1);  
         

            float []partidas= new float[20];
            float total_total=0;
            float total=0;
            
                /************************************************************/
            try{  //try para crear el archivo
                
                    Pila[] st;
                    st = new Pila[70];
                    for (int i=0; i<70; i++) {
                         st[i] = new Pila();
                    }
                    //st[0].PUSH("hola");
                    //System.out.println(st[0].POP());

                    while(rs.next()){
                        
                        orga = rs.getString("ORGANISMO");
                        partida = rs.getString("PARTIDA");
                        obse = rs.getString("OBSERVACION");
                        monto = rs.getFloat("MONTO");

                        int organismo  = Integer.parseInt(orga.substring(0,3));//aqui saco los organismos

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

                        
                        st[get_alias(organismo)].PUSH(organismo+"<>"+Float.toString(monto)+"<>"+get_partida(partida));

                        //if(count==2000)break;
                        count++;
                    }
                    
                    con.close(); 
                    System.out.println("\u001B[43m Connection closed "+ count +"\033[0m"); 
                    //t.stop();

                    int suma =0;
                    boolean ok=false;


                    BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"));

                    
                    writer.write("ORGANISMO,"); 
                    for (int i=0;i<11 ; i++) {
                        writer.write(get_name_partidas(i)+","); 
                    }
                    writer.write("total");
                    writer.newLine();  



                   ArrayList<String> list=new ArrayList<String>();

                    for (int i=0; i<60; i++) {
                         if(st[i].size()!=0){
                            System.out.println("**********************************************");
                            if(st[i].size() > 0){
                                //writer.write(get_name(i)+",");
                                System.out.println(get_name(i));
                                //list.add(get_name(i)+"");
                                list.add(get_name(i)+"-"+new utils().get_organismo(get_name(i)));
                            }
                            while(st[i].size() > 0){
                                String line = st[i].POP();
                                String []line2 = line.split("<>");
                                int index = get_alias_partida(get_partida(line2[2]));
                                partidas[index] += Float.parseFloat(line2[1]);
                                ok=true;
                            }
                            if(ok){
                                for (int f=0;f<11;f++) {
                                    System.out.println(get_name_partidas(f)+"-"+partidas[f]);
                                    list.add(Float.toString(partidas[f]));
                                    total += partidas[f];
                                }
                                list.add(total+""); 
                                for(int r=0;r<list.size();r++)
                                {
                                     writer.write(list.get(r)+",");  
                                }
                                writer.newLine();
                                list.clear();
                                total_total += total;
                                total=0;
                                for (int w=0;w<20;w++) {
                                    partidas[w]=0;
                                }
                                ok=false;
                            }
                         }
                    }

                  
                    for (int f=0;f<12;f++) {
                        writer.write(","); 
                    }
                    writer.write(Float.toString(total_total)+",");  
                    writer.flush();
                    writer.close();

                   new CsvToExcel().start();
                    return count;

            }catch(Exception e){// si algo sale mal creando el archivo
                System.out.println(e);
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
        /*int rowCount = 1;
        int rowCount2 = 0; 
        int rowCount3 = 0;
        int rowCount4 = 0;   
        float p401=0;
        float p402=0;
        float p403=0;
        float p404=0;
        float p405=0;
        float p406=0;
        float p407=0;
        float p408=0;
        float p409=0;
        float p410=0;
        float p411=0;*/

        //String []campos = {"ORGANISMO","401","402","403","404","405","406","407","408","409","410","411"};
        float []partidas= new float[20];
        float total_total=0;
        float total=0;

        try{  
            con = DriverManager.getConnection(url,name,pass);  
            System.out.println("\u001B[43m Connection created\033[0m");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(query1);  

            /************************************************************/
            try{  //try para crear el archivo

                Pila[] st;
                st = new Pila[70];
                for (int i=0; i<70; i++) {
                     st[i] = new Pila();
                }
                while(rs.next()){
                    int anho = rs.getInt("ANHO");
                    String orga = rs.getString("ORGANISMO");//51 
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

                    int organismo  = Integer.parseInt(orga.substring(0,3));

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

                   
                    st[get_alias(organismo)].PUSH(organismo+"<>"+Float.toString(monto)+"<>"+get_partida(partida));

                    count++;
                   }
                   con.close();
                   System.out.println("\u001B[43m Connection closed "+ count +"\033[0m"); 


                    int suma =0;
                    boolean ok=false;


                    BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"));

                    
                    writer.write("ORGANISMO,"); 
                    for (int i=0;i<11 ; i++) {
                        writer.write(get_name_partidas(i)+","); 
                    }
                    writer.write("total");
                    writer.newLine();  

                   ArrayList<String> list=new ArrayList<String>();

                    for (int i=0; i<60; i++) {
                         if(st[i].size()!=0){
                            System.out.println("**********************************************");
                            if(st[i].size() > 0){
                                //writer.write(get_name(i)+",");
                                System.out.println(get_name(i));
                                //list.add(get_name(i)+"");
                                list.add(get_name(i)+"-"+new utils().get_organismo(get_name(i)));
                            }
                            while(st[i].size() > 0){
                                String line = st[i].POP();
                                String []line2 = line.split("<>");
                                int index = get_alias_partida(get_partida(line2[2]));
                                partidas[index] += Float.parseFloat(line2[1]);
                                ok=true;
                            }
                            if(ok){
                                for (int f=0;f<11;f++) {
                                    System.out.println(get_name_partidas(f)+"-"+partidas[f]);
                                    list.add(Float.toString(partidas[f]));
                                    total += partidas[f];
                                }
                                list.add(total+""); 
                                for(int r=0;r<list.size();r++)
                                {
                                     writer.write(list.get(r)+",");  
                                }
                                writer.newLine();
                                list.clear();
                                total_total += total;
                                total=0;
                                for (int w=0;w<20;w++) {
                                    partidas[w]=0;
                                }
                                ok=false;
                            }
                         }
                    }

                  
                    for (int f=0;f<12;f++) {
                        writer.write(","); 
                    }
                    writer.write(Float.toString(total_total)+",");  
                    writer.flush();
                    writer.close();

                   new CsvToExcel().start();
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
public int sql_pagadas2(String url,String name,String pass,String query1,boolean filter){
        System.out.println("\u001B[42m Connection : "+url+"\033[0m"); 
        Connection con = null;  
        int count=0;
        float []partidas= new float[20];

        try{  
            con = DriverManager.getConnection(url,name,pass);  
            System.out.println("\u001B[43m Connection created\033[0m");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(query1);  

            /************************************************************/
            try{  //try para crear el archivo

                Pila[] st;
                st = new Pila[70];
                for (int i=0; i<70; i++) {
                     st[i] = new Pila();
                }
                while(rs.next()){
                    int anho = rs.getInt("ANHO");
                    String orga = rs.getString("ORGANISMO");//51 
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

                    int organismo  = Integer.parseInt(orga.substring(0,3));

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
                   
                    st[get_alias(organismo)].PUSH(organismo+"<>"+Float.toString(monto)+"<>"+get_partida(partida));

                    count++;
                   }
                   con.close();
                   System.out.println("\u001B[43m Connection closed "+ count +"\033[0m"); 


                    boolean ok=false;


                    BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"));

                    
                    writer.write("ORGANISMO,"); 
                   
                    writer.write("Gasto de personal,");
                    writer.write("Gasto de funcionamiento,");
                    writer.write("Entes descentralizados,");

                    writer.newLine();  

                   ArrayList<String> list=new ArrayList<String>();

                    for (int i=0; i<60; i++) {
                         if(st[i].size()!=0){
                            System.out.println("**********************************************");
                            if(st[i].size() > 0){
                                //writer.write(get_name(i)+",");
                                System.out.println(get_name(i));
                                //list.add(get_name(i)+"");
                                list.add(get_name(i)+"-"+new utils().get_organismo(get_name(i)));
                            }
                            while(st[i].size() > 0){
                                String line = st[i].POP();
                                String []line2 = line.split("<>");
                                int index = get_alias_partida(get_partida(line2[2]));
                                partidas[index] += Float.parseFloat(line2[1]);
                                ok=true;
                            }
                            if(ok){
                                float gp=0;
                                float gf=0;
                                float ed=0;

                                gp = partidas[get_alias_partida(401)];
                                gf = partidas[get_alias_partida(402)] + partidas[get_alias_partida(403)] + partidas[get_alias_partida(404)] + partidas[get_alias_partida(405)] + partidas[get_alias_partida(406)] + partidas[get_alias_partida(408)] + partidas[get_alias_partida(409)] + partidas[get_alias_partida(410)] + partidas[get_alias_partida(411)];
                                ed = partidas[get_alias_partida(407)];



                                list.add(Float.toString(gp));
                                list.add(Float.toString(gf));
                                list.add(Float.toString(ed));

                                System.out.println("Gasto de personal: "+gp);
                                System.out.println("Gasto de funcionamiento: "+gf);
                                System.out.println("Entes descentralizados: "+ed);

                                //list.add(total+""); 
                                for(int r=0;r<list.size();r++)
                                {
                                     writer.write(list.get(r)+",");  
                                }
                                writer.newLine();
                                list.clear();
                                for (int w=0;w<20;w++) {
                                    partidas[w]=0;
                                }
                                ok=false;
                            }
                         }
                    }

                    writer.flush();
                    writer.close();

                   new CsvToExcel().start();
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
public int sql_pendientes2(String url,String name,String pass,String query1,boolean filter){

   System.out.println("\u001B[42m Connection : "+url+"\033[0m"); 
  
   Connection con = null;  
   int count=0;
  // String []campos = {"ORGANISMO","401","402","403","404","405","406","407","408","409","410","411"};
   
   MyRunnable myRunnable = new MyRunnable(10);
   Thread t = new Thread(myRunnable);
   //t.start();
   

   String orga = null;
   String partida = null;
   String obse = null;
   float monto = 0;
   int regularizada=0;
   int patria=0;
   int especial=0;

   try{ 
            con = DriverManager.getConnection(url,name,pass);  
            System.out.println("\u001B[43m Connection created\033[0m");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(query1);  


            float []partidas= new float[20];
            float total_total=0;
            float total=0;
            
                /************************************************************/
            try{  //try para crear el archivo
                
                    Pila[] st;
                    st = new Pila[70];
                    for (int i=0; i<70; i++) {
                         st[i] = new Pila();
                    }
                    //st[0].PUSH("hola");
                    //System.out.println(st[0].POP());

                    while(rs.next()){
                        
                        orga = rs.getString("ORGANISMO");
                        partida = rs.getString("PARTIDA");
                        obse = rs.getString("OBSERVACION");
                        monto = rs.getFloat("MONTO");

                        int organismo  = Integer.parseInt(orga.substring(0,3));//aqui saco los organismos
                        if(filter){
                            if(new utils().filtro1(obse)){
                               regularizada++;
                               continue;
                            }
                            if(new utils().filtro2(obse)){
                               patria++;
                               continue;
                            }
                            if(new utils().filtro3(obse)){
                               especial++;
                               continue;
                            }
                        }
                       /* if(new utils().filtro4(obse)){
                           especial++;
                           continue;
                        }*/
                        
                        st[get_alias(organismo)].PUSH(organismo+"<>"+Float.toString(monto)+"<>"+get_partida(partida));


                        //if(count==2000)break;
                        count++;
                    }
                    
                    con.close(); 
                    System.out.println("\u001B[43m Connection closed "+ count +"\033[0m"+ "regularizadas: "+regularizada+" patria: "+patria+" especial: "+especial); 
                    //t.stop();



                   boolean ok=false;
                    BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"));

                    
                    writer.write("ORGANISMO,"); 
                    writer.write("Gasto de personal,");
                    writer.write("Gasto de funcionamiento,");
                    writer.write("Entes descentralizados,");

                    writer.newLine();  

                    ArrayList<String> list=new ArrayList<String>();

                    for (int i=0; i<60; i++) {
                         if(st[i].size()!=0){
                            System.out.println("**********************************************");
                            if(st[i].size() > 0){
                                //writer.write(get_name(i)+",");
                                System.out.println(get_name(i));
                                //list.add(get_name(i)+"");
                                list.add(get_name(i)+"-"+new utils().get_organismo(get_name(i)));
                            }
                            while(st[i].size() > 0){
                                String line = st[i].POP();
                                String []line2 = line.split("<>");
                                int index = get_alias_partida(get_partida(line2[2]));
                                partidas[index] += Float.parseFloat(line2[1]);
                                ok=true;
                            }
                            if(ok){
                                float gp=0;
                                float gf=0;
                                float ed=0;

                                gp = partidas[get_alias_partida(401)];
                                gf = partidas[get_alias_partida(402)] + partidas[get_alias_partida(403)] + partidas[get_alias_partida(404)] + partidas[get_alias_partida(405)] + partidas[get_alias_partida(406)] + partidas[get_alias_partida(408)] + partidas[get_alias_partida(409)] + partidas[get_alias_partida(410)] + partidas[get_alias_partida(411)];
                                
                                ed = partidas[get_alias_partida(407)];

                                list.add(Float.toString(gp));
                                list.add(Float.toString(gf));
                                list.add(Float.toString(ed));

                                System.out.println("Gasto de personal: "+gp);
                                System.out.println("Gasto de funcionamiento: "+gf);
                                System.out.println("Entes descentralizados: "+ed);

                                //list.add(total+""); 
                                for(int r=0;r<list.size();r++)
                                {
                                     writer.write(list.get(r)+",");  
                                }
                                writer.newLine();
                                list.clear();
                                for (int w=0;w<20;w++) {
                                    partidas[w]=0;
                                }
                                ok=false;
                            }
                         }
                    }

                    writer.flush();
                    writer.close();

                    new CsvToExcel().start();


                    return count;

            }catch(Exception e){// si algo sale mal creando el archivo
                System.out.println(e);
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

public int pagadas_retenciones2(String url,String name,String pass,String query1){
        System.out.println("\u001B[42m Connection : "+url+"\033[0m"); 
        Connection con = null;  
        int count=0;
        
         Pila[] st;
         st = new Pila[70];
         for (int i=0; i<70; i++) {
                st[i] = new Pila();
         }
        try{  
            con = DriverManager.getConnection(url,name,pass);  
            System.out.println("\u001B[43m Connection created\033[0m");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(query1);  

            /************************************************************/
            try{  //try para crear el archivo
               
                while(rs.next()){
                       String orga= rs.getString("ORGANISMO");
                     
                       String raro=rs.getString("R.RETE_ID||'-'||RT.DENOMINACION"); 
                       //String rif=rs.getString("RIF");
                       //float monto1=rs.getFloat("MONTO_ORDEN_ANT");
                       //float monto2=rs.getFloat("MONTO_ORDEN");
                       float monto3=rs.getFloat("MONTO_1_X_500_ANT"); 
                       //float monto4=rs.getFloat("MONTO_1_X_500");
                       //String fecha=rs.getString("FECHA_PAGO");

                       int organismo  = Integer.parseInt(orga.substring(0,3));//aqui saco los organismo
                       st[get_alias(organismo)].PUSH(organismo+"<>"+raro+"<>"+Float.toString(monto3));
                       count++;
                   }
                   con.close(); 
                   System.out.println("\u001B[43m Connection closed "+ count +"\033[0m"); 
                   boolean ok=false;
                   BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"));
                   writer.write("ORGANISMO,"); 
                   writer.write("RETENCIÓN DEL IVA,");
                   writer.write("RETENCIÓN DEL ISLR,");
                   writer.write("RETENCIÓN DEL IMPUESTO 1X1000,");
                   writer.write("TOTAL,");
                   writer.newLine();
                   ArrayList<String> list=new ArrayList<String>();

                   float iva=0;
                   float islr=0;
                   float impuesto=0;
                   float total=0;

                   float total_iva=0;
                   float total_islr=0;
                   float total_impuesto=0;
                   float total_total=0;


                   for (int i=0; i<60; i++) {
                         if(st[i].size()!=0){
                            System.out.println("**********************************************");
                            if(st[i].size() > 0){
                                System.out.println(get_name(i));
                                list.add(get_name(i)+"-"+new utils().get_organismo(get_name(i)));
                            }
                            while(st[i].size() > 0){
                                String line = st[i].POP();
                                String []line2 = line.split("<>");
                                String tipo=line2[1];
                                float monto = Float.parseFloat(line2[2]);
                                if(tipo.indexOf("IVA")>-1){
                                    iva += monto;
                                }else if(tipo.indexOf("ISLR")>-1){
                                    islr += monto;
                                }else if(tipo.indexOf("IMPUESTO 1X1000")>-1){
                                    impuesto += monto;
                                }

                                ok=true;
                            }
                            if(ok){
                                total = iva + islr + impuesto;
                                System.out.println("RETENCIÓN DEL IVA: "+iva);
                                System.out.println("RETENCIÓN DEL ISLR: "+islr);
                                System.out.println("RETENCIÓN DEL IMPUESTO 1X1000: "+impuesto);
                                System.out.println("total: "+total);
                                list.add(Float.toString(iva));
                                list.add(Float.toString(islr));
                                list.add(Float.toString(impuesto));
                                list.add(Float.toString(total));

                                for(int r=0;r<list.size();r++)
                                {
                                     writer.write(list.get(r)+",");  
                                }
                                writer.newLine();
                                list.clear();
                                total_iva += iva;
                                total_islr += islr;
                                total_impuesto += impuesto;
                                total_total += total;
                                iva=0;
                                islr=0;
                                impuesto=0;
                                total=0;
                                ok=false;
                            }
                         }
                    }

                    writer.write("TOTAL GENERAL,"); 
                    writer.write(Float.toString(total_iva)+",");  
                    writer.write(Float.toString(total_islr)+",");  
                    writer.write(Float.toString(total_impuesto)+",");  
                    writer.write(Float.toString(total_total)+",");  
                    writer.flush();
                    writer.close();
                    new CsvToExcel().start();
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



}
