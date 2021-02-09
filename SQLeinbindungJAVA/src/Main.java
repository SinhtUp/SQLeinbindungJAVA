//Grundlage für den Import in die Datenbank war die Datei CIAListe.xls welche erst in eine csv datei umgewandelt wurde.
// In der Datei wurden dann zusätzlich alle ";" durch "," ersetzt , was im deutschen leider eigentlich nicht vorgesehen ist.
//Die Ursprüngliche Quelle der Datei lautet: https://luo-darmstadt.de/sqltutorial/db_cia.html

//-Verbindung mit der SQL Datenbank und sie veränden läuft
//-einlesen der ersten zeile der csv läuft
//Fehlt noch:
// -das erstellen der zeilentitel aus der ersten zeile der csv
// -Zeilen der Datenbank mit Werten aus der CSV Datei ab Zeile 2 füllen
//-aufräumen und in funktionen/methoden aufteilen

// Rest der aktuellen Fehler siehst du ja beim Kompilieren,die libs und co lade ich später noch hoch!

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

        BufferedReader br = new BufferedReader(new FileReader("E:\\JavaProjects\\SQLeinbindungJAVA2\\AAAAResourcen\\CIAListeCSV.csv"));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        br.close();

        //return fileAsString;
        System.out.println(line);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Erfolgreich mit Datenbankserver verbunden");

            //Einfügen/Verändern

            String createDatabase ="CREATE DATABASE IF NOT EXISTS cia_database"; //Datenbank wird nur angelegt wenn sie nicht bereits existiert
            String createTable = "CREATE TABLE IF NOT EXISTS cia_liste ("+line+"); ";
            Statement stmt = conn.createStatement();
            stmt.execute(createDatabase);
            stmt.execute("USE cia_database;");
            System.out.println("Datenbank cia_database erzeugt(wenn sie nicht bereits vorhanden war)");
            stmt.execute(createTable);
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
