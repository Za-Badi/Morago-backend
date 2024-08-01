package com.habsida.morago.model.results;

import com.habsida.morago.model.dto.LanguageDTO;
import com.habsida.morago.model.dto.UserProfileDTO;

import java.util.List;

public class UserProfilePageOutput extends PageOutput<UserProfileDTO> {
    public UserProfilePageOutput(int currentPage, int totalPages, long totalElements, List<UserProfileDTO> objectList) {
        super(currentPage, totalPages, totalElements, objectList);
    }
}
