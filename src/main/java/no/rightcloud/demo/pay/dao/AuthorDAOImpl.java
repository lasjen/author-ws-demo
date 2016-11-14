package no.rightcloud.demo.pay.dao;

import no.rightcloud.demo.pay.db.DatabaseUtil;
import no.rightcloud.demo.pay.jooq.tables.records.AuthorRecord;
import org.jooq.*;
import org.jooq.impl.DSL;

import javax.sql.RowSet;
import java.math.BigInteger;
import java.sql.*;
import java.util.Collection;
import java.util.List;

import static no.rightcloud.demo.pay.jooq.Sequences.AUTHOR_SEQ;
import static no.rightcloud.demo.pay.jooq.tables.Author.AUTHOR;


/**
 * Created by lassejenssen on 15/09/16.
 */
public class AuthorDAOImpl implements AuthorDAO{

   public int insertAuthor(String first_name, String last_name) {
      DSLContext create = getContext("AuthorInsertWS","","");

      AuthorRecord rec = create.insertInto(AUTHOR, AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
            .values(create.nextval(AUTHOR_SEQ), first_name, last_name).returning(AUTHOR.ID).fetchOne();

      return rec.getValue(AUTHOR.ID).intValue();

      /*
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

      create.delete(AUTHOR).where(AUTHOR.ID.equal(author_id)).execute();

      return true;
   }

   public boolean updateAuthor(AuthorRecord author) {
      DSLContext create = getContext("AuthorUpdateWS","","");

      create.update(AUTHOR)
            .set(AUTHOR.FIRST_NAME,author.getFirstName())
            .set(AUTHOR.LAST_NAME,author.getLastName())
            .where(AUTHOR.ID.equal(author.getId()))
            .execute();

      return true;
   }

   public AuthorRecord fetchAuthorById(int id) {
      DSLContext create = getContext("AuthorGetByIdWS","","");

      AuthorRecord author =  create.selectFrom(AUTHOR).where(AUTHOR.ID.equal(BigInteger.valueOf(id))).fetchOne();

      return author;
   }

   public AuthorRecord findAuthor(String last_name, String first_name) {
      return null;
   }

   public List<AuthorRecord> fetchAuthors(String last_name, String first_name) {
      DSLContext create = getContext("AuthorFetchByNameWS","","");

      return create.select().from(AUTHOR)
               .where(AUTHOR.LAST_NAME.like(last_name)).or(AUTHOR.FIRST_NAME.like(first_name))
               .fetchInto(AuthorRecord.class);
   }

   public List<AuthorRecord> fetchAllAuthors(){
      DSLContext create = getContext("AuthorFetchAllWS","","");

      return create.select().from(AUTHOR)
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
}
