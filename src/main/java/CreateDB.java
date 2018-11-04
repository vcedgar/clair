package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.json;

public class CreateDB {
    Connection database;

    public CreateDB(String host, String port, String user, String dbName) throws SQLException, ClassNotFoundException, IOException {

        Connection server = this.connectToServer(host, port, user);
        this.database = this.createDB(server, host, port, user, dbName);
        this.createSchemaAndTables();
    }


    private Connection connectToServer(String host, String port, String user) throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + user;
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, "hello");
    }

    private Connection createDB(Connection server, String host, String port, String user, String dbName) throws SQLException {
        Statement createDB = server.createStatement();
        String createDBBuilder = "CREATE DATABASE " + dbName +
                "\n\tWITH \n\tOWNER = " +
                user +
                "\n\tENCODING = 'UTF8';";
        createDB.executeUpdate(createDBBuilder);
        server.close();
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        return DriverManager.getConnection(url, user, "hello");
    }

    private void createSchemaAndTables() throws IOException, SQLException {
        String regexForSQLSplit = "(?<=;)";
        BufferedReader fileReader = new BufferedReader(new FileReader("sql/createAndLoad.sql"));
        StringBuffer fileBuffer = new StringBuffer();
        String line;
        while((line = fileReader.readLine()) != null){
            fileBuffer.append(line);
        }
        String[] statements = fileBuffer.toString().split(regexForSQLSplit);
        for(int i = 0; i < statements.length; i++){
            System.out.println("Try: " + statements[i]);
            Statement statement = this.database.createStatement();
            statement.executeUpdate(statements[i]);
            System.out.println(statements[i]);
        }
    }

    private void loadData(){

    }
}
