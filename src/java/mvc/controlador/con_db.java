/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

/**
 *
 * @author kebryan
 */
public class con_db {
    public static final int MSSQL_SM = 1;
    public static final int MSSQL_IP = 2;
    private static final String[] configMSSQL = {"","1433","sm","sm1234"};
    //Linux
    //private static final String[] configMSSQL = {"","1433","sa","FKebryan1995"}; 
    
    public static C_BD open(int typeDB){
        switch(typeDB){
            case con_db.MSSQL_SM:
                configMSSQL[0] = "bd_sm";
                return new C_SQLServer(configMSSQL);
            case con_db.MSSQL_IP:
                configMSSQL[0] = "bd_ip";
                return new C_SQLServer(configMSSQL);
                default:
                    return null;
        }
    }
    
}
