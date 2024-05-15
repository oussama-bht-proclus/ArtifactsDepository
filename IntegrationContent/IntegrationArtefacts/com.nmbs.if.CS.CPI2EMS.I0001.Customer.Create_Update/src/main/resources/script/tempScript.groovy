import com.sap.it.api.mapping.*;

def String getProperty(String prop, MappingContext  context) {
    
    def property = context.getProperty(prop).toBoolean();
    return property;
}


