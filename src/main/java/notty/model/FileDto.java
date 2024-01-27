package notty.model;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.File;
import java.io.Serializable;
import java.util.List;

@Data
public class FileDto implements Serializable {

    File file ;
    List<Integer> pagesNumber ;
}
