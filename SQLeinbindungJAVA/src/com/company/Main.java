//Grundlage für den Import in die Datenbank war die Datei CIAListe.xls welche erst in eine csv datei umgewandelt wurde.
// In der Datei wurden dann zusätzlich alle ";" durch "," ersetzt , was im deutschen leider eigentlich nicht vorgesehen ist.
//Die Ursprüngliche Quelle der Datei lautet: https://luo-darmstadt.de/sqltutorial/db_cia.html

//-Verbindung mit der SQL Datenbank und sie veränden läuft
//-einlesen der ersten zeile der csv läuft
//Fehlt noch:
// -das erstellen der zeilentitel aus der ersten zeile der csv
// -Zeilen der Datenbank mit Werten aus der CSV Datei ab Zeile 2 füllen
//-aufräumen und in funktionen/methoden aufteilen


//hochkoma mit stringbuilder beim String.split() hinzufügen damit strings korrekt an sql übergeben werden können?! richtig?!
package com.company;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Main {


    public static void main(String[] args) throws IOException {

        String url = "jdbc:mysql://localhost:3306/?useJDBCCompliantTimezoneshift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
        String user = "root";
        String password ="";


        BufferedReader br = new BufferedReader(new FileReader("src/abhängigkeiten/CIAListeCSV.csv"));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        String[] header = line.split(",");  //String.split(trennzeichen) teilt einen eingelesenen string an vorgegebener stelle
        br.close();

        System.out.println(line);


        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Erfolgreich mit Datenbankserver verbunden");

            //Einfügen/Verändern

            String createDatabase ="CREATE DATABASE IF NOT EXISTS cia_database"; //Datenbank wird nur angelegt wenn sie nicht bereits existiert
            String createTable = "CREATE TABLE IF NOT EXISTS cia_liste ; "; //Tabelle wird angelegt wenn sie nicht existiert

            //String firstRowId;  "ALTER TABLE cia_database " +
              //      "ADD "+i+2+header[i];
            Statement stmt = conn.createStatement();
            stmt.execute(createDatabase);
            stmt.execute("USE cia_database;");
            System.out.println("Datenbank cia_database erzeugt(wenn sie nicht bereits vorhanden war)");
            stmt.execute(createTable);


                for (int i = 0; i < header.length;i++){   //Ausgabe der einzelnen im Array gespeicherten Strings durch For Schleife
                    System.out.println(header[i]);
                    int j = i+1;
                   // String firstRowId = "ALTER TABLE cia_database "
                     //       +"ADD 'id'+j+;
                    //stmt.execute(firstRowId);
                }

            for (int i = 0; i < header.length;i++){   //Ausgabe der einzelnen im Array gespeicherten Strings durch For Schleife
            System.out.println(header[i]);
            String headerQuery = "ALTER TABLE cia_database " +
                                    "ADD i+3"+header[i];                           //+2 wegen ab 0 zählen und vorher erstellte id column 1 .
            stmt.execute(headerQuery);
            }
            stmt.close();
            //----------------------------------------------------------------------
            //ausgeben
/**
        }
        public static void ausgabe () {
            query = "SELECT * FROM spieler ORDER BY spieler_id ASC ;";
        }
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        int columns = rs.getMetaData().getColumnCount();
        for (int i = 1; i < columns; i++)
            System.out.println(String.format("%-15s", rs.getMetaData().getColumnLabel(i)));
        System.out.println();
        System.out.println("____________________________________________");

        while (rs.next()) {
            for (int i = 1; i <= columns; i++)
                System.out.println(String.format("%-15s", rs.getString(i)));
            System.out.println();

        }
        rs.close();
        stmt.close();
 **/
    }catch(SQLException ex)

    {
        System.err.println(ex.getMessage());
    }

}



}




   /**     HSSFWorkbook datei = new HSSFWorkbook();

        try{
            FileOutputStream output = new FileOutputStream("Main.xls");
            datei.write(output);
            output.close();
        }catch (Exception e){

        }
**/


//}
