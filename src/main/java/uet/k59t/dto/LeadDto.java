package uet.k59t.dto;

import lombok.Data;

@Data
public class LeadDto {
    private Long id;

    private boolean isDeleted;

    private String firstName;

    private String lastName;

    private String company;

    private String email;

    private String source;

    private int status;

    private boolean isConverted;
}
