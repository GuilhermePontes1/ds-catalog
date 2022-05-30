package com.guilherme.descatalog.dto;


import com.guilherme.descatalog.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    
    public CategoryDTO (Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }


}
