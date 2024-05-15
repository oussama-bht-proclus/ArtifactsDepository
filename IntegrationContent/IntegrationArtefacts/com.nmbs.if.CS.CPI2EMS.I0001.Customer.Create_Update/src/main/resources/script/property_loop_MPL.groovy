import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {

    def messageLog = messageLogFactory.getMessageLog(message);
    if(messageLog != null){

       def allProperties = message.getProperties();

       def logProperties = allProperties.grep{it.key.startsWith("AX_")};


       logProperties.each{
           entry -> messageLog.addCustomHeaderProperty("$entry.key".replaceFirst(/^AX_[.,]?/,''), "$entry.value".replaceFirst(/^0+0[.,]?/,''));
           };

    }
    return message;
}