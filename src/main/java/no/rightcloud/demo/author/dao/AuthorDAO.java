package no.rightcloud.demo.author.dao;

import no.rightcloud.demo.author.jooq.tables.records.AuthorRecord;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * Created by lassejenssen on 15/09/16.
 */
public interface AuthorDAO {
   public boolean truncate();
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
