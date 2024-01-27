package notty.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link Note} entity
 */
@Data
@NoArgsConstructor
public class NoteDto implements Serializable {

    Long id;
    String context;
    LocalDate date;
    String description;

}
