import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.JsonSlurper;
import groovy.json.JsonOutput;
import java.net.URLEncoder;


def Message processData(Message message) {
    def body = message.getBody(java.lang.String);
    def msgHeaders = message.getHeaders();
    
    def customerJson = new JsonSlurper().parseText(body)
    def jsonString = JsonOutput.toJson(customerJson)
    
    String encodedValues = URLEncoder.encode(jsonString, "UTF-8")
    
    def base = "https://test-belgiantrain.trinergy.be/api/v3/invoicing/customers/create";
    def url = base + "?values=" + encodedValues;

    message.setProperty("values", encodedValues);
    
    //empty body for the request : MANDATORY
    message.setBody('')
    return message;
}