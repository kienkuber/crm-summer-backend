package uet.k59t.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LeadRequestDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String company;

    @NotBlank
    private String email;

    private String source;
}
