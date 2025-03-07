package com.h2.h2.api.service;

import com.h2.h2.api.model.FaqModel;

import java.util.List;

public interface FaqService {
    List<FaqModel> listados();

    List<FaqModel> listadosA();

    FaqModel crear(FaqModel faqModel);

    FaqModel update(FaqModel faqModel);

    FaqModel delete (Integer id);

}
