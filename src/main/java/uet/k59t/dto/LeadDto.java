package uet.k59t.dto;

import lombok.Data;

@Data
public class LeadDto {
    private Long id;

    private boolean deleted;

    private String firstName;

    private String lastName;

    private String company;

    private String email;

    private String source;

    private Boolean qualified;

    private boolean converted;
}
