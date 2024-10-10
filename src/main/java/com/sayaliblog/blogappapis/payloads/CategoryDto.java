package com.sayaliblog.blogappapis.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty
    @Size(min = 4, message="Title must be min of 4 characters")
    private String categoryTitle;
    @Size(min=3,max=10)
    private String categoryDescription;
}
