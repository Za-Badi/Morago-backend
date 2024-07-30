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

    public List<ConsultantProfileDTO> getConsultantProfilesByIsOnlineAndLanguageId(Boolean isOnline, Long id) {
        return consultantProfileServiceImp.getConsultantProfilesByIsOnlineAndLanguageId(isOnline, id);
    }

    public ConsultantPage getConsultantProfilesByIsOnlineAndLanguageIdPaged(PagingInput pagingInput, Long id, Boolean isOnline) {
        return consultantProfileServiceImp.getConsultantProfilesByIsOnlineAndLanguageIdPaged(pagingInput, id, isOnline);
    }

    public List<ConsultantProfileDTO> getConsultantProfilesByLanguageId(Long id) {
        return consultantProfileServiceImp.getConsultantProfilesByLanguageId(id);
    }

    public ConsultantPage getConsultantProfilesByLanguageIdPaged(PagingInput pagingInput, Long id) {
        return consultantProfileServiceImp.getConsultantProfilesByLanguageIdPaged(pagingInput, id);
    }

    public List<ConsultantProfileDTO> getConsultantProfilesByLanguageName(String name) {
        return consultantProfileServiceImp.getConsultantProfilesByLanguageName(name);
    }

    public ConsultantPage getConsultantProfilesByLanguageNamePaged(PagingInput pagingInput, String name) {
        return consultantProfileServiceImp.getConsultantProfilesByLanguageNamePaged(pagingInput, name);
    }

    public ConsultantPage searchConsultants(String searchInput, PagingInput pagingInput) {
        return consultantProfileServiceImp.searchConsultants(searchInput, pagingInput);
    }
}
