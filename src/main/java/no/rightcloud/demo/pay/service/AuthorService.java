package no.rightcloud.demo.pay.service;

import no.rightcloud.demo.pay.dao.AuthorDAO;
import no.rightcloud.demo.pay.dao.DAOFactory;
import no.rightcloud.demo.pay.jooq.tables.records.AuthorRecord;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by lassejenssen on 15/09/16.
 */
public class AuthorService {

   AuthorDAO dao;

   public AuthorService() {
      dao = DAOFactory.getAuthorDAO();
   }

   public int insertAuthor(String first_name, String last_name){
      int i = dao.insertAuthor(first_name, last_name);
      return i;
   }

   public int deleteAuthor(BigInteger author_id){
      System.out.println("Into AuthorService");
      boolean deleted = dao.deleteAuthor(author_id);
      return (deleted ? author_id.intValue() : 0);
   }

   public int updateAuthor(BigInteger author_id, String first_name, String last_name){
      AuthorRecord ar = new AuthorRecord(author_id, first_name, last_name);
      boolean updated = dao.updateAuthor(ar);
      return (updated ? author_id.intValue() : 0);
   }

   public List<AuthorRecord> fetchAllAuthors(){
      List<AuthorRecord> list = dao.fetchAllAuthors();
      return list;
   }

   public List<AuthorRecord> fetchAuthorsByName(String last_name, String first_name){
      List<AuthorRecord> list = dao.fetchAuthors(last_name, first_name);
      return list;
   }

   public AuthorRecord fetchAuthorById (int authorid){
      return dao.fetchAuthorById(authorid);
   }
}
