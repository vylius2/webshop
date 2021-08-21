package com.webshop.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @Size(min = 2, max = 55)
    private String name;

    @Range(min = 0, max = 10000001)
    private BigDecimal price;

    @Size(max = 9999)
    private String description;

    @Size(max = 255)
    @NotBlank
    private String picturePath;

}
