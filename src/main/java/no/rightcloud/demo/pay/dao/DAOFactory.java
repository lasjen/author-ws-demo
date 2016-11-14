package no.rightcloud.demo.pay.dao;

/**
 * Created by lassejenssen on 15/09/16.
 */
public class DAOFactory {
   public static AuthorDAO getAuthorDAO() {
      return new AuthorDAOImpl();
   }
}
