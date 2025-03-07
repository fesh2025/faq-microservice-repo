package com.h2.h2.api.service.impl;

import com.h2.h2.api.model.FaqModel;
import com.h2.h2.api.respository.FaqRepository;
import com.h2.h2.api.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    private FaqRepository faqRepository;

    @Override
    public List<FaqModel> listados() {        
        return faqRepository.findAll();
    }

    @Override
    public FaqModel crear(FaqModel faqModel) {
    if (faqModel.getStatus() == null) {
        faqModel.setStatus("A");
    }

    if (faqModel.getCreateUser() == null) {
        faqModel.setCreateUser("ADMIN");
    }

    if (faqModel.getCategory() == null) {
        faqModel.setCategory("Sin Categoria asignada");
    }

    return faqRepository.save(faqModel);
    }


    @Override
    public FaqModel update(FaqModel faqModel) {
        if (faqRepository.existsById(faqModel.getIdfaq())) {
            if (faqModel.getUpdateUser() == null) {
                faqModel.setUpdateUser("USER");
            }
            faqModel.setStatus("A");
            return faqRepository.save(faqModel);
        } else {
            throw new EntityNotFoundException("Unidad Operativa con id " + faqModel.getIdfaq() + " no encontrada.");
        }
    }

    @Override
    public FaqModel delete(Integer id) {
        FaqModel unidadbusca = faqRepository.findById(id).get();
        unidadbusca.setStatus("I");
        return faqRepository.save(unidadbusca);
    }

    @Override
    public List<FaqModel> listadosA() {
        return faqRepository.findByStatus("A");
    }

}
