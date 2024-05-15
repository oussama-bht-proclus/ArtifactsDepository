
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import com.equalize.cpi.converter.FormatConversionBean


def Message processData(Message message) {
    // implements JsonTransformBean
    def fcb = new FormatConversionBean(message.exchange, message.getProperties())
	def output = fcb.convert()
	message.setBody(output)
    return message;
}