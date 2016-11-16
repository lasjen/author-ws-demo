package no.rightcloud.demo.author.ws;

import no.rightcloud.demo.author.model.AuthorType;
import no.rightcloud.demo.author.ws.exceptions.AuthorServiceException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by lassejenssen on 15/11/2016.
 */
@WebService(name="AuthorWS")
@SOAPBinding(style= SOAPBinding.Style.DOCUMENT)
public interface AuthorWS {
   @WebMethod
   @WebResult(name = "response")
   String insertAuthor(@WebParam(name = "firstname") String firstname,
                       @WebParam(name = "lastname") String lastname) throws AuthorServiceException;

   @WebMethod
   @WebResult(name = "response")
   String deleteAuthor (@WebParam(name = "authorid") BigInteger author_id);

   @WebMethod
   @WebResult(name = "response")
   String updateAuthor(@WebParam(name = "authorid") BigInteger author_id,
                       @WebParam(name = "firstname") String first_name,
                       @WebParam(name = "lastname") String last_name);

   @WebMethod
   @WebResult(name = "author")
   List<AuthorType> selectAllAuthors();

   @WebMethod
   @WebResult(name = "author")
   List<AuthorType> selectAuthorsByName(@WebParam(name = "lastname") String last_name,
                                        @WebParam(name = "firstname") String first_name);

   @WebMethod
   @WebResult(name = "author")
   AuthorType selectAuthor(@WebParam(name = "authorid") int author_id);

   @WebMethod
   @WebResult(name = "response")
   String truncateAuthor();
}
