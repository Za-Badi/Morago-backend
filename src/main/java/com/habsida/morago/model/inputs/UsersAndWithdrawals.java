package com.habsida.morago.model.inputs;

import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.dto.WithdrawalsDTO;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.Withdrawals;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UsersAndWithdrawals {
    private UserDTO user;
    private List<WithdrawalsDTO> withdrawals;

}