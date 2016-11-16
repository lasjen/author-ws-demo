package no.rightcloud.demo.author.ws;

import no.rightcloud.demo.author.jooq.tables.records.AuthorRecord;
import no.rightcloud.demo.author.service.AuthorService;
import no.rightcloud.demo.author.model.AuthorType;
import no.rightcloud.demo.author.ws.exceptions.AuthorServiceException;

import javax.jws.WebService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "no.rightcloud.demo.author.ws.AuthorWS")
public class AuthorWSImpl implements AuthorWS {
   private AuthorService as;

   public AuthorWSImpl(){
      as = new AuthorService();
   }

   public String insertAuthor(String firstname, String lastname) throws AuthorServiceException {
      firstname.trim(); lastname.trim();
      if (lastname.isEmpty()|| firstname.isEmpty() ) {
         throw new AuthorServiceException("The arguments firstname and lastname must have values");
      }
      int id = as.insertAuthor(firstname, lastname);
      return "OK - id:" + id;
   }

   public String deleteAuthor(BigInteger author_id){
      System.out.println("Into WS");
      int id = as.deleteAuthor(author_id);
      return "Deleted author.ID = " + id;
   }

   public String updateAuthor(BigInteger author_id, String first_name, String last_name){
      int id = as.updateAuthor(author_id, first_name, last_name);
      return "Updated author.ID = " + id;
   }

   public List<AuthorType> selectAllAuthors(){
      List<AuthorRecord> list = as.fetchAllAuthors();
      List<AuthorType> response = new ArrayList<AuthorType>();
      list.forEach((a) -> {
               response.add(new AuthorType(a.getId().intValue(),a.getFirstName(),a.getLastName()));
            }
      );
      return response;
   }

   public List<AuthorType> selectAuthorsByName(String last_name, String first_name){
      List<AuthorRecord> list = as.fetchAuthorsByName(last_name, first_name);
      List<AuthorType> response = new ArrayList<AuthorType>();
      list.forEach((a) -> {
               response.add(new AuthorType(a.getId().intValue(),a.getFirstName(),a.getLastName()));
            }
      );
      return response;
   }

   public AuthorType selectAuthor(int author_id){
      AuthorRecord rec = as.fetchAuthorById(author_id);
      AuthorType author = new AuthorType();
      author.setId(rec.getId().intValue());
      author.setFirst_name(rec.getFirstName());
      author.setLast_name(rec.getLastName());
      return author;
   }

   public String truncateAuthor() {
      as.truncAuthor();
      return "Table truncated!";
   }

}