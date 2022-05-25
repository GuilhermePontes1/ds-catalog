package com.guilherme.descatalog.entities;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

}
