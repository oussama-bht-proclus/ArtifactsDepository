import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {
	def title = message.getProperty("logTitle") as String;
	return processData(message, title);
}

def Message processData(Message message, String title) {
    def body = message.getBody(java.lang.String) as String;
    def messageLog = messageLogFactory.getMessageLog(message);

    if(messageLog != null){
		title = (title != null && title.length() > 0) ? title : "Payload";
		messageLog.addAttachmentAsString(title, body, "text/xml");
    }
    return message;
}

def Message logAsOriginal(Message message) {
	return processData(message, 'Original');
}

def Message logAsEndMapping(Message message) {
	return processData(message, 'End mapping');
}

def Message logAsMainException(Message message) {
	return processData(message, 'Main Exception');
}

def Message logAsTransmitException(Message message) {
	return processData(message, 'Transmit Exception');
}

def Message logAsMappingException(Message message) {
	return processData(message, 'Mapping Exception');
}