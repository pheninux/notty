package notty.model;

import lombok.Data;

@Data
public class SearchDto {

    String Context ;
    String Description ;
    boolean inFile ;
    boolean deepSearch ;
}
