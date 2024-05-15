import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.*;

def Message processData(Message message) {
   def body = message.getBody(String)
   def rootXML = new XmlSlurper().parseText(message.getProperty("Payload"));
   def statusCode = message.getHeaders().get("CamelHttpResponseCode");
   
   def operation = message.getProperty("AX_customerID") == null ? "CREATE" : "UPDATE";
   
   def errorMsg = null;
   def success = null;
   def ackMsg = null;
   
   if ( statusCode == 500 ) throw new Exception(message.getHeaders().get("CamelHttpResponseText"));
   if ( statusCode != 200 ){
       success = false;
       errorMsg = message.getHeaders().get("CamelHttpResponseText"); 
       ackMsg = "ERROR :"+statusCode;
   } else {
       success = true;
       ackMsg = "Company "+rootXML.IDOC.E1KNA1M.KUNNR+" "+operation;
   }
   //rootXML.IDOC.EDI_DC40.STATUS = status;
   //def outxml = groovy.xml.XmlUtil.serialize( rootXML )
    
   message.setProperty("success", success);
   message.setProperty("operation", operation);
   message.setProperty("ackMsg", ackMsg)
   message.setBody(message.getProperty("Payload"));
   return message;
}