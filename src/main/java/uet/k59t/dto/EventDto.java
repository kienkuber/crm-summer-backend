package uet.k59t.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EventDto {
    private Long id;

    private AccountDto accountDto;

    private String subject;

    private Timestamp activityDateTime;

    private Long durationInMinute;

    private String description;
}
