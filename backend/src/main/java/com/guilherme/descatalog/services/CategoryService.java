package com.guilherme.descatalog.services;

import com.guilherme.descatalog.dto.CategoryDTO;
import com.guilherme.descatalog.entities.Category;
import com.guilherme.descatalog.repositories.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository respository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = respository.findAll();

        return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }


}
