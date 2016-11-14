package no.rightcloud.demo.author.ws;

import no.rightcloud.demo.author.jooq.tables.records.AuthorRecord;
import no.rightcloud.demo.author.service.AuthorService;
import no.rightcloud.demo.author.model.AuthorType;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@WebService(name="AuthorWS")
public class AuthorWS {
   private AuthorService as;

   public AuthorWS(){
      as = new AuthorService();
   }

   @WebMethod
   public String insertAuthor (String first_name, String last_name){
      int id = as.insertAuthor(first_name, last_name);
      return "OK - id:" + id;
   }

   @WebMethod
   public String deleteAuthor (BigInteger author_id){
      System.out.println("Into WS");
      int id = as.deleteAuthor(author_id);
      return "Deleted author.ID = " + id;
   }

   @WebMethod
   public String updateAuthor (BigInteger author_id, String first_name, String last_name){
      int id = as.updateAuthor(author_id, first_name, last_name);
      return "Updated author.ID = " + id;
   }

   @WebMethod
   public List<AuthorType> selectAllAuthors (){
      List<AuthorRecord> list = as.fetchAllAuthors();
      List<AuthorType> response = new ArrayList<AuthorType>();
      list.forEach((a) -> {
               response.add(new AuthorType(a.getId().intValue(),a.getFirstName(),a.getLastName()));
            }
      );
      return response;
   }

   @WebMethod
   public List<AuthorType> selectAuthorsByName (String last_name, String first_name){
      List<AuthorRecord> list = as.fetchAuthorsByName(last_name, first_name);
      List<AuthorType> response = new ArrayList<AuthorType>();
      list.forEach((a) -> {
               response.add(new AuthorType(a.getId().intValue(),a.getFirstName(),a.getLastName()));
            }
      );
      return response;
   }

   @WebMethod
   public AuthorType selectAuthor (int author_id){
      AuthorRecord rec = as.fetchAuthorById(author_id);
      AuthorType author = new AuthorType();
      author.setId(rec.getId().intValue());
      author.setFirst_name(rec.getFirstName());
      author.setLast_name(rec.getLastName());
      return author;
   }

   @WebMethod
   public String truncateAuthor() {
      as.truncAuthor();
      return "Table truncated!";
   }

  /* private String listToXML(List<AuthorRecord> list){
      StringBuffer xml = new StringBuffer("<authors>");

      list.forEach((a) -> {
               xml.append("<author id="+ a.getId()+">\n<first_name>"+a.getFirstName()+
                     "</first_name>\n<last_name>"+a.getLastName()+"</last_name>\n<a/author>\n");
            }
      );
      xml.append("</authors>");

      return xml.toString();
   }*/

}