package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.ConsultantProfileDTO;
import com.habsida.morago.model.inputs.ConsultantPage;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.serviceImpl.ConsultantProfileServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultantProfileQueryResolver implements GraphQLQueryResolver {
    private final ConsultantProfileServiceImp consultantProfileServiceImp;

    public ConsultantPage getAllConsultantProfilesPaged(PagingInput pagingInput) {
        return consultantProfileServiceImp.getAllConsultantProfilesPaged(pagingInput);
    }

    public ConsultantProfileDTO getConsultantProfileById(Long id) throws ExceptionGraphql {
        return consultantProfileServiceImp.getConsultantProfileById(id);
    }


    public ConsultantPage getConsultantProfilesByIsOnlineAndLanguageIdPaged(PagingInput pagingInput, Long id, Boolean isOnline) {
        return consultantProfileServiceImp.getConsultantProfilesByIsOnlineAndLanguageIdPaged(pagingInput, id, isOnline);
    }


    public ConsultantPage getConsultantProfilesByLanguageIdPaged(PagingInput pagingInput, Long id) {
        return consultantProfileServiceImp.getConsultantProfilesByLanguageIdPaged(pagingInput, id);
    }


    public ConsultantPage getConsultantProfilesByLanguageNamePaged(PagingInput pagingInput, String name) {
        return consultantProfileServiceImp.getConsultantProfilesByLanguageNamePaged(pagingInput, name);
    }

    public ConsultantPage searchConsultants(String searchInput, PagingInput pagingInput) {
        return consultantProfileServiceImp.searchConsultants(searchInput, pagingInput);
    }
}
