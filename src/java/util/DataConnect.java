package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {

    public static Connection getConnection() {
        try {
            //Configura servidor de Banco de Dados e nome do Banco
            String serverName = "localhost";
            String mydatabase = "test";
            //Login e senha do banco
            String username = "root";
            String password = "root";
            // Carregando o JDBC Driver
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            // Criando a conexao com o Banco de Dados
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase; // a JDBC url
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }
    public static Connection getConnection2() {
        try {
            //Configura servidor de Banco de Dados e nome do Banco
            String serverName = "localhost";
            String mydatabase = "web_collector";
            //Login e senha do banco
            String username = "root";
            String password = "root";
            // Carregando o JDBC Driver
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            // Criando a conexao com o Banco de Dados
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase; // a JDBC url
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}