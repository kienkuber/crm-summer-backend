package uet.k59t.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;

    private Long category_id;

    private String name;

    private int price;

    private String storage;
}
