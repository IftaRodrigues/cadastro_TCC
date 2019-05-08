
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionFactory implements IConnectionFactory {

    private static final String URL
            = "jdbc:postgresql://localhost:5432/projetos";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "iftaro123";

    private static Connection con;
    
    
    
    
    /*Testar conexão com Banco
    public static void main(String[] args) {
        ConnectionFactory teste = new ConnectionFactory();
        System.out.println(teste.getConnection());
    }*/

    @Override
    public Connection getConnection() {

        try {

            Class.forName(DRIVER);

            if ((con == null) || con.isClosed()) {

                con = DriverManager.getConnection(URL, USUARIO, SENHA);

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;

    }

}
