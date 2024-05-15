import com.sap.it.api.mapping.*;

def String checkArguments(String LOEVM, String SPERR, String CASSD, String AUFSD, String FAKSD, String LIFSD, String Val) {
    if (LOEVM != Val || SPERR != Val|| CASSD != Val || AUFSD != Val || FAKSD != Val  || LIFSD != Val) {
        return 'true'
    } else {
        return 'false'
    }
}


def void checkPARVW_RG(String[] PARVWS, Output out, MappingContext context){
    for(String test in PARVWS){
        if ( test == "RG" ){
            out.addValue("true");
            break;
        }
    }
    out.addValue("false")
}

def void checkPARVW_RE(String[] PARVWS, Output out, MappingContext context){
    for(String test in PARVWS){
        if ( test == "RE" ){
            out.addValue("true");
            break;
        }
    }
    out.addValue("false")
}


def void getFirstItemFromQueue(String[] queue, Output output, MappingContext context){
    output.addValue(queue[0]);
}
 

def void ContainsRG(String[] PARVWS, Output out, MappingContext context){
    for(String test in PARVWS){
        if ( test == 'RG' ){
            out.addValue("true");
            break;
        }
    }
}

