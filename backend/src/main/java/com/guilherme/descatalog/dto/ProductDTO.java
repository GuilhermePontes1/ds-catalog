package com.guilherme.descatalog.dto;

import com.guilherme.descatalog.entities.Category;
import com.guilherme.descatalog.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter private Long id;

    @Getter @Setter private String name;

    @Column(columnDefinition = "TEXT")
    @Getter @Setter private String description;

    @Getter @Setter private Double price;
    @Getter @Setter private String imgUrl;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @Getter @Setter private Instant date;
    @Getter Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.date = entity.getDate();
    }

    public ProductDTO (Product entity, Set<Category> categories){
        this(entity);
        categories.forEach(x -> this.categories.add(new CategoryDTO(x)));
    }
}
