package no.rightcloud.demo.pay.db;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by lassejenssen on 15/09/16.
 */
public class DatabaseUtil {
   public static Connection getConnection() {
      try {
         InitialContext ctx = new InitialContext();
         // Here we lookup the datasource with the name
         // "java:comp/env/jdbc/jcgDS"
         DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UCPPool");
         return ds.getConnection();
      } catch (Exception exc) {
         exc.printStackTrace();
         return null;
      }
   }

}
