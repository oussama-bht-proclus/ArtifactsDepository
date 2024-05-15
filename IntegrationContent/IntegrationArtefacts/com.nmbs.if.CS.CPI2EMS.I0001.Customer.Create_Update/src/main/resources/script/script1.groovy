
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
def Message processData(Message message) {
    //Body
    def body = message.getBody();
    def statusCode = message.getHeaders().get("CamelHttpResponseCode");
    def responseText = message.getHeaders().get("CamelHttpResponseText");
    def process = 
    
    return message;
}