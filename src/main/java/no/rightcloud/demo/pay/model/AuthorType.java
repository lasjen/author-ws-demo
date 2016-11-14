package no.rightcloud.demo.pay.model;

import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Created by lassejenssen on 14/11/2016.
 */
@XmlAccessorType
public class AuthorType {
   protected int    id;
   protected String first_name;
   protected String last_name;

   public AuthorType() {
      super();
   }

   public AuthorType(int id, String first_name, String last_name ) {
      super();
      this.id = id;
      this.first_name = first_name;
      this.last_name = last_name;
   }

   public int getId() {
      return id;
   }

   public String getFirst_name() {
      return first_name;
   }

   public String getLast_name() {
      return last_name;
   }

   public void setId(int id){
      this.id = id;
   }

   public void setFirst_name(String first_name){
      this.first_name = first_name;
   }

   public void setLast_name(String last_name){
      this.last_name = last_name;
   }
}
