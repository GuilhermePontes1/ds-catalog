package com.guilherme.descatalog.services;

import com.guilherme.descatalog.entities.Category;
import com.guilherme.descatalog.repositories.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository respository;

    public List<Category> findAll() {
       return respository.findAll();
    }


}
