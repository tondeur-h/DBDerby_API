package com.htdev.javaDB;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de test pour l'api Database
 * @author herve (2016)
 * @see Database
 */
public class DatabaseTest {

    public DatabaseTest() {
        try {
      //connecter la base de données
            //pour tester le mode embedded...
            Database db=new Database("/home/herve/dev/jNaturos/Naturos", "herve", "herve",null,0,true,false);
            //pour tester le mode client Serveur...
            //Database db=new Database("/home/herve/dev/HTTEST", "herve", "herve","localhost",0,false,false);
            
            if (db.connect()) {System.out.println("Naturos est connectée");} else {System.out.println("Naturos n'est pas connectée!");System.exit(0x01);}
    
            db.setSchema("APP");
            System.out.println("Schema:"+db.schema());
            System.out.println("Catalogue:"+db.catalog());
            System.out.println(db.infoClients());
            System.out.println("URL:"+db.metaData().getURL());
            System.out.println("UserName:"+db.metaData().getUserName());
            System.out.println(db.getAllTables());
            
            db.query("select * from patient");
            while (db.getDB().next()){
                System.out.println(db.getDB().getString(2)+" "+db.getDB().getString(3));  
            }
            
            db.close();
            db.disconnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static void main(String[] args) {
      new DatabaseTest();
    }
    
}
