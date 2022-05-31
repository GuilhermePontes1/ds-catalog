package com.guilherme.descatalog.services;

import com.guilherme.descatalog.dto.CategoryDTO;
import com.guilherme.descatalog.dto.ProductDTO;
import com.guilherme.descatalog.entities.Category;
import com.guilherme.descatalog.entities.Product;
import com.guilherme.descatalog.repositories.CategoryRespository;
import com.guilherme.descatalog.repositories.ProductRespository;
import com.guilherme.descatalog.services.exceptions.DatabaseException;
import com.guilherme.descatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRespository respository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {

        Page<Product> list = respository.findAll(pageRequest);
        return list.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        Optional<Product> obj = respository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {

        Product entity = new Product();
        entity.setName(dto.getName());
        entity = respository.save(entity);
        return new ProductDTO(entity);
    }


    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {

            Product entity = respository.getOne(id);
            entity.setName(dto.getName());
            entity = respository.save(entity);
            return new ProductDTO(entity);

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