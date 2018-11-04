package main.java;

import java.io.IOException;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        new CreateDB("localhost", "5432", "postgres", "practice");
    }
}
