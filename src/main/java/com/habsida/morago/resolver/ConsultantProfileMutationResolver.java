package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.ConsultantProfileDTO;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.inputs.ConsultantProfileInput;
import com.habsida.morago.serviceImpl.ConsultantProfileServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultantProfileMutationResolver implements GraphQLMutationResolver {
    private final ConsultantProfileServiceImp consultantProfileServiceImp;

    public ConsultantProfileDTO addConsultantProfile(Long id, ConsultantProfileInput consultantProfileInput) throws ExceptionGraphql {
        return consultantProfileServiceImp.addConsultantProfile(id, consultantProfileInput);
    }

    public ConsultantProfileDTO updateConsultantProfile(Long id, ConsultantProfileInput consultantProfileInput) throws ExceptionGraphql {
        return consultantProfileServiceImp.updateConsultantProfile(id, consultantProfileInput);
    }

    public Boolean deleteConsultantProfile(Long id) throws ExceptionGraphql {
        consultantProfileServiceImp.deleteConsultantProfile(id);
        return true;
    }

    public ConsultantProfileDTO updateConsultantProfileByUserId(Long id, ConsultantProfileInput consultantProfileInput) throws ExceptionGraphql {
        return consultantProfileServiceImp.updateConsultantProfileByUserId(id, consultantProfileInput);
    }

    public Boolean changeIsAvailableForConsultant(Long id, Boolean isAvailable) throws ExceptionGraphql {
        return consultantProfileServiceImp.changeIsAvailable(id, isAvailable);
    }

    public Boolean changeIsOnlineForConsultant(Long id, Boolean isOnline) throws ExceptionGraphql {
        return consultantProfileServiceImp.changeIsOnline(id, isOnline);
    }

    public ConsultantProfileDTO addLanguageToConsultantProfile(Long languageId, Long consultantProfileId) throws ExceptionGraphql {
        return consultantProfileServiceImp.addLanguageToConsultantProfile(languageId, consultantProfileId);
    }

    public Boolean deleteLanguageFromConsultantProfile(Long languageId, Long consultantProfileId) throws ExceptionGraphql {
        consultantProfileServiceImp.deleteLanguageFromConsultantProfile(languageId, consultantProfileId);
        return true;
    }

    public UserDTO changeBalanceForConsultantProfileId(Long id, Double balance) throws ExceptionGraphql {
        return consultantProfileServiceImp.changeBalanceForConsultantProfileId(id, balance);
    }
}
