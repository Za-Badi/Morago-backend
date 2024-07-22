package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.ConsultantProfileDTO;
import com.habsida.morago.model.inputs.ConsultantPage;
import com.habsida.morago.serviceImpl.ConsultantProfileServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultantProfileQueryResolver implements GraphQLQueryResolver {
    private final ConsultantProfileServiceImp consultantProfileServiceImp;

    public List<ConsultantProfileDTO> getAllConsultantProfiles() {
        return consultantProfileServiceImp.getAllConsultantProfiles();
    }

    public ConsultantPage getAllConsultantProfilesPaged(Integer page, Integer size) {
        return consultantProfileServiceImp.getAllConsultantProfilesPaged(page, size);
    }

    public ConsultantProfileDTO getConsultantProfileById(Long id) throws ExceptionGraphql {
        return consultantProfileServiceImp.getConsultantProfileById(id);
    }

    public List<ConsultantProfileDTO> getConsultantProfilesByIsOnlineAndLanguageId(Boolean isOnline, Long id) {
        return consultantProfileServiceImp.getConsultantProfilesByIsOnlineAndLanguageId(isOnline, id);
    }

    public ConsultantPage getConsultantProfilesByIsOnlineAndLanguageIdPaged(Integer page, Integer size, Long id, Boolean isOnline) {
        return consultantProfileServiceImp.getConsultantProfilesByIsOnlineAndLanguageIdPaged(page, size, id, isOnline);
    }

    public List<ConsultantProfileDTO> getConsultantProfilesByLanguageId(Long id) {
        return consultantProfileServiceImp.getConsultantProfilesByLanguageId(id);
    }

    public ConsultantPage getConsultantProfilesByLanguageIdPaged(Integer page, Integer size, Long id) {
        return consultantProfileServiceImp.getConsultantProfilesByLanguageIdPaged(page, size, id);
    }

    public List<ConsultantProfileDTO> getConsultantProfilesByLanguageName(String name) {
        return consultantProfileServiceImp.getConsultantProfilesByLanguageName(name);
    }

    public ConsultantPage getConsultantProfilesByLanguageNamePaged(Integer page, Integer size, String name) {
        return consultantProfileServiceImp.getConsultantProfilesByLanguageNamePaged(page, size, name);
    }

    public ConsultantPage searchConsultants(String searchInput, Integer page, Integer size) {
        return consultantProfileServiceImp.searchConsultants(searchInput, page, size);
    }
}
