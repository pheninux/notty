package notty.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseDto implements Serializable {

    List<NoteDto> noteDtos;
    List<FileDto> files;



    public ResponseDto(List<NoteDto> noteDtos, List<FileDto> files) {
        this.noteDtos = noteDtos;
        this.files = files;
    }

}
