import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.*;

def Message processData(Message message) {
   def body = message.getBody(String)
   def rootXML = new XmlSlurper().parseText(message.getProperty("Payload"));
   def statusCode = message.getHeaders().get("CamelHttpResponseCode");
   
   def operation = message.getProperty("customerID") == null ? "CREATE" : "UPDATE" // if the process was  a CREATE or UPDAT
   
   def errorMsg = null;
   def success = null;
   def ackMsg = null;
   
   if ( statusCode == 500 ) throw new Exception(message.getHeaders().get("CamelHttpResponseText"));
   if ( statusCode != 200 ){
       success = false;
       errorMsg = message.getHeaders().get("CamelHttpResponseText"); // might aswell add this as a response message for both ERROR and COMPLETED
       ackMsg = "ERROR :"+statusCode;
   }else {
       success = true;
       ackMsg = "Company "+rootXML.IDOC.E1KNA1M.KUNNR+" "+operation;
   }
   
   if (  message.getProperty("CamelExceptionCaught") ){
       
   }
   
   
   message.setBody(message.getProperty("Payload"));
   return message;
}