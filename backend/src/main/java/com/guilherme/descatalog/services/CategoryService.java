package com.guilherme.descatalog.services;

import com.guilherme.descatalog.dto.CategoryDTO;
import com.guilherme.descatalog.entities.Category;
import com.guilherme.descatalog.exceptions.ResourceNotFoundException;
import com.guilherme.descatalog.repositories.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
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

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {

        Optional<Category> obj = respository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {

        Category entity = new Category();
        entity.setName(dto.getName());
        entity = respository.save(entity);
        return new CategoryDTO(entity);
    }


    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {

            Category entity = respository.getOne(id);
            entity.setName(dto.getName());
            entity = respository.save(entity);
            return new CategoryDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }
}