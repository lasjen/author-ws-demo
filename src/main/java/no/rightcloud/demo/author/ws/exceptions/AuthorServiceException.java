package no.rightcloud.demo.author.ws.exceptions;

import javax.xml.ws.WebFault;
import java.lang.Exception;

/**
 * Created by lassejenssen on 16/11/2016.
 */
@WebFault
public class AuthorServiceException extends Exception{
   public AuthorServiceException(String message, Throwable cause) {
      super(message, cause);
   }

   public AuthorServiceException(String message) {
      super(message);
   }
}
