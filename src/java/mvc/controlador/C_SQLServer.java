/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author kebryan
 */
public final class C_SQLServer extends C_BD{
    
    public C_SQLServer(String[] params) {
        this.params = params;
        this.open();
    }

    @Override
    Connection open() {
        try {
            //String connectionUrl = "jdbc:sqlserver://;database=DB_Name;integratedSecurity=true;";
            //conect = DriverManager.getConnection(connectionUrl);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //jdbc:sqlserver://localhost:1433;databaseName=prueba
            this.conexion = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:"+params[1]+";databaseName="+params[0],params[2],params[3]);
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

        return this.conexion;
    }
    
}
