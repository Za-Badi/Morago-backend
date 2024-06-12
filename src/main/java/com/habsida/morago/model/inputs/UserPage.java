package com.habsida.morago.model.inputs;

import com.habsida.morago.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserPage {
    private List<User> content;
    private Integer totalPages;
    private Integer totalElements;
    private Integer size;
    private Integer number;
}
