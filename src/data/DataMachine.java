package data;

import java.util.HashMap;

public class DataMachine implements Data {
    private String machineName;

    //DOC: valores boolean são sempre true no erro
    private final HashMap<String , Object> ATRIBUTTES = new HashMap<>();

    public void addAttribute( String name , Object value ) {
        ATRIBUTTES.put( name , value );
    }

}
