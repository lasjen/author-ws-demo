package no.rightcloud.demo.pay.dao;

import no.rightcloud.demo.pay.jooq.tables.records.AuthorRecord;
import javax.sql.RowSet;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * Created by lassejenssen on 15/09/16.
 */
public interface AuthorDAO {
   public int insertAuthor(String first_name, String last_name);
   public boolean deleteAuthor(BigInteger author_id);
   public boolean updateAuthor(AuthorRecord author);
   public AuthorRecord fetchAuthorById(int id);
   public AuthorRecord findAuthor(String last_name, String first_name);
   public List<AuthorRecord> fetchAuthors(String last_name, String first_name);
   public List<AuthorRecord> fetchAllAuthors();
   public int countAuthors();
   public Collection findAuthorsTO(String last_name, String first_name);
}
