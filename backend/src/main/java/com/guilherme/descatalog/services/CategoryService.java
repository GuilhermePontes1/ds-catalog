package com.guilherme.descatalog.services;

import com.guilherme.descatalog.dto.CategoryDTO;
import com.guilherme.descatalog.entities.Category;
import com.guilherme.descatalog.repositories.CategoryRespository;
import com.guilherme.descatalog.services.exceptions.DatabaseException;
import com.guilherme.descatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository respository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> list = respository.findAll(pageable);
        return list.map(CategoryDTO::new);
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
    public void delete(Long id) {
    try {
        respository.deleteById(id);

    }catch (EmptyResultDataAccessException e ) {
        throw new ResourceNotFoundException("Id not found " + id);
    }catch (DataIntegrityViolationException e ) {
        throw new DatabaseException("Integrity violation");
    }

    }
}