package no.rightcloud.demo.author.dao;

import no.rightcloud.demo.author.jooq.Sequences;
import no.rightcloud.demo.author.jooq.tables.Author;
import no.rightcloud.demo.author.jooq.tables.records.AuthorRecord;
import no.rightcloud.demo.author.db.DatabaseUtil;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.math.BigInteger;
import java.sql.*;
import java.util.Collection;
import java.util.List;


/**
 * Created by lassejenssen on 15/09/16.
 */
public class AuthorDAOImpl implements AuthorDAO {

   public int insertAuthor(String first_name, String last_name) {
      DSLContext create = getContext("AuthorInsertWS","","");

      AuthorRecord rec = create.insertInto(Author.AUTHOR, Author.AUTHOR.ID, Author.AUTHOR.FIRST_NAME, Author.AUTHOR.LAST_NAME)
            .values(create.nextval(Sequences.AUTHOR_SEQ), first_name, last_name).returning(Author.AUTHOR.ID).fetchOne();

      return rec.getValue(Author.AUTHOR.ID).intValue();

      /* See the difference between jooq and simple jdbc :-)
      ResultSet rs;
      BigInteger id = BigInteger.valueOf(0);
      String query = "begin insert into author values (author_seq.nextval, ?, ?) returning id into ?; end;";
      Connection conn = DatabaseUtil.getConnection();

      try {
         // Statement s = conn.createStatement();
         // s.execute("alter session set tracefile_identifier='LJ'");
         // s.execute("alter session set events '10046 trace name context forever, level 12'");
         CallableStatement cs = conn.prepareCall(query);

         cs.setString(1,first_name);
         cs.setString(2,last_name);
         cs.registerOutParameter(3, OracleTypes.INTEGER);

         cs.execute();
         id = BigInteger.valueOf(cs.getInt(3));

         cs.close();
         conn.close();

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("FAILED");
      }
      return id.intValue();
      */

   }

   public boolean deleteAuthor(BigInteger author_id) {
      DSLContext create = getContext("AuthorDeleteWS","","");

      create.delete(Author.AUTHOR).where(Author.AUTHOR.ID.equal(author_id)).execute();

      return true;
   }

   public boolean updateAuthor(AuthorRecord author) {
      DSLContext create = getContext("AuthorUpdateWS","","");

      create.update(Author.AUTHOR)
            .set(Author.AUTHOR.FIRST_NAME,author.getFirstName())
            .set(Author.AUTHOR.LAST_NAME,author.getLastName())
            .where(Author.AUTHOR.ID.equal(author.getId()))
            .execute();

      return true;
   }

   public AuthorRecord fetchAuthorById(int id) {
      DSLContext create = getContext("AuthorGetByIdWS","","");

      AuthorRecord author =  create.selectFrom(Author.AUTHOR).where(Author.AUTHOR.ID.equal(BigInteger.valueOf(id))).fetchOne();

      return author;
   }

   public AuthorRecord findAuthor(String last_name, String first_name) {
      return null;
   }

   public List<AuthorRecord> fetchAuthors(String last_name, String first_name)  {
      DSLContext create = getContext("AuthorFetchByNameWS","","");

      return create.select().from(Author.AUTHOR)
               .where(Author.AUTHOR.LAST_NAME.like(last_name)).or(Author.AUTHOR.FIRST_NAME.like(first_name))
               .fetchInto(AuthorRecord.class);
   }

   public List<AuthorRecord> fetchAllAuthors(){
      DSLContext create = getContext("AuthorFetchAllWS","","");

      return create.select().from(Author.AUTHOR)
            .fetchInto(AuthorRecord.class);
   }

   public Collection findAuthorsTO(String last_name, String first_name) {
      return null;
   }

   public int countAuthors(){
      DSLContext create = getContext("AuthorCountWS","","");

      return 1; //create.fetchCount(AUTHOR);
   }

   private DSLContext getContext(String module, String action, String clientid){
      Connection conn = DatabaseUtil.getConnection();
      DSLContext create = DSL.using(conn, SQLDialect.ORACLE);

      try {
         conn.setClientInfo("OCSID.MODULE",module);
         conn.setClientInfo("OCSID.ACTION",action);
         conn.setClientInfo("OCSID.CLIENTID",clientid);
      } catch (Exception e) {
         e.printStackTrace();
      }

      return create;
   }

   public boolean truncate(){
      DSLContext create = getContext("AuthorTruncateWS","","");

      create.delete(Author.AUTHOR).where("1=1").execute();

      return true;
   }
}
