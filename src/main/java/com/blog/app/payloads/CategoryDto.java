package com.blog.app.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;

    @NotBlank
    @Size(min = 4,message = "Minimum of 4 characters required")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10,message = "Minimum of 10 characters required")
    private String categoryDescription;
}
