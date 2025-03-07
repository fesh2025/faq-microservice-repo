package com.h2.h2.api.controller;

import com.h2.h2.api.model.FaqModel;
import com.h2.h2.api.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;

@RestController
@RequestMapping("api/v1/faq")
public class FaqController {
    @Autowired
    private FaqService productService;

    @Autowired
    private DataSource dataSource;

    public void testConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database!");
        }
    }

    @GetMapping("GetAll")
    public List<FaqModel> listar() {
        return productService.listados();
    }

    @GetMapping("GetAllActive")
    public List<FaqModel> listarA() {
        return productService.listadosA();
    }

    @PostMapping("Create")
    public FaqModel create(@RequestBody FaqModel faqModel) {
        return productService.crear(faqModel);
    }

    @PutMapping("{id}")
    public FaqModel update(@PathVariable Integer id, @RequestBody FaqModel faqModel) {
        faqModel.setIdfaq(id);
        return productService.update(faqModel);
    }
    

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }
}
