
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.JsonBuilder;

def Message processData(Message message) {
    def body = message.getBody(java.lang.String);
    // array of every block/message
    def root = body.split('\\$');
    //def res = [:]
    def parent = []
    
    for(def i=0; i<root.size() ; i++){
        def res = [:]
    
        def block = root[i];
        
        
        String pattern = /(?s):([20|21|25|28C|60F|60M|61|86|62F|62M|64|65]+):(.*?)(?=(?=\n:[0-9]{2}[A-Z]?|$))/
        def matcher = block =~ pattern
        
        def keyValueArray = []
        matcher.each { match ->
        
            String key = match[1]
            
            String value = match[2].replaceAll("\n", " ").trim()
            
            def myMap = [:]
            myMap["X_"+key] = value
            
            keyValueArray << myMap
        }
        
        
        res["XA"] = keyValueArray
        parent[i] = res
    }
    
    //String arrayString = keyValueArray.join("+")
    
    def jsonBuilder = new JsonBuilder(parent)
    String json = jsonBuilder.toPrettyString()
    
    def jsonToString=json.toString().substring(1, json.length()- 1);
    jsonToString="{\"record\": ["+jsonToString+"]}"
    
    //message.setProperty("key", keyValueArray[2])
    message.setProperty("AX_JSON",json);
    message.setProperty("sizeofarray", root.size())
    message.setBody(jsonToString)
    return message;
}