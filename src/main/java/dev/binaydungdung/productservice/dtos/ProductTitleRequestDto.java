package dev.binaydungdung.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductTitleRequestDto {
    private List<String> categories;
}
