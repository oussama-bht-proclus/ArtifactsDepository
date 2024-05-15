import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.JsonSlurper;
import groovy.json.JsonOutput;


def Message processData(Message message) {
    def body = message.getBody(java.lang.String)
    def jsonInstance = new JsonSlurper()
    def myJson = jsonInstance.parseText(body);

   def newRecords = []

   myJson.record.each { record -> 
        def recordContent = [:]
        def transList = [] 
        def tempTransMap = [:]
        
        record.XA.each { item ->
            def key = item.keySet().first()
            def value = item.values().first()
    
            if (key.startsWith('X_61')) {
                
                if (!tempTransMap.isEmpty() && tempTransMap.containsKey('X_61') && !tempTransMap.containsKey('X_86')) {
                    recordContent[tempTransMap.keySet().first()] = tempTransMap.values().first()
                    tempTransMap.clear()
                }
                tempTransMap['X_61'] = value
            } else if (key.startsWith('X_86')) {
                if (tempTransMap.containsKey('X_61')) {
                    tempTransMap['X_86'] = value
                    
                    transList << tempTransMap.clone()
                    tempTransMap.clear()
                } else {
                    
                    recordContent[key] = value
                }
            } else {
                
                recordContent[key] = value
            }
        }
        
        if (!tempTransMap.isEmpty() && tempTransMap.containsKey('X_61')) {
            recordContent[tempTransMap.keySet().first()] = tempTransMap.values().first()
        }
    
    
        if (!transList.isEmpty()) {
            recordContent['trans'] = transList
        }
        
        newRecords << recordContent
    }
    
    def newJson = [record: newRecords]
    def newJsonString = JsonOutput.toJson(newJson)

   
   
   
    message.setBody(newJsonString)   
    message.setProperty("AAA_NEW_JSON", newJsonString)
    message.setProperty("AA_PARSED_JSON",myJson );
    //message.setProperty("AA_COUNTER",AA_COUNTER);
    //message.setProperty("XX", );
    return message;
}