package uet.k59t.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
