package uet.k59t.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class EventRequestDto {

    @NotNull
    private Long accountId;

    @NotBlank
    private String subject;

    @NotNull
    private Timestamp activityDateTime;

    @NotNull
    private Long durationInMinute;

    private String description;
}
