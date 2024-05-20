import com.sap.gateway.ip.core.customdev.util.Message
import java.nio.charset.Charset

def Message processData(Message message) {
        
        String httpQuery = message.getHeader('CamelHttpQuery', String)
        
        if (httpQuery) {
            Map<String, String> queryParameters = URLDecoder.decode(httpQuery, Charset.defaultCharset().name())
			 .replace("\$","")
                .tokenize('&')
                .collectEntries { it.tokenize('=') }
            
            message.setProperties(queryParameters)
        }       
    
    return message
}