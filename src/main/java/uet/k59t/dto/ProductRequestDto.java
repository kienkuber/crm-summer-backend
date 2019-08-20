package uet.k59t.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private int price;

    @NotBlank
    private String storage;
}
