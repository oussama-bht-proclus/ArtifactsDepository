import com.sap.gateway.ip.core.customdev.util.Message;
import groovy.json.JsonSlurper

def processData(Message message){
    def body = message.getBody(String)
    def statusCode = message.getHeaders().get("CamelHttpResponseCode");
    def response = new JsonSlurper().parseText(body)
    
    def empty = response.size() == 0 ? 'true' : 'false';
    def customerID = empty == 'true' ? null : response[0].id;
    
    //messageLog.addAttachmentAsString("body.txt", message.getProperty("Payload"), "text/plain");
    
    message.setProperty("EmptyPaylaod", empty); // when empty == true => the Customer doesn't exist thus CREATE
    message.setProperty("StatusCode", statusCode);
    message.setProperty("AX_customerID", customerID);
    message.setBody(message.getProperty("Payload"));
    return message;
}