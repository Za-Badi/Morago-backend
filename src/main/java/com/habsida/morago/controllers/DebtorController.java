package com.habsida.morago.controllers;

import com.habsida.morago.model.input.DebtorInput;
import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.resolver.DebtorResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DebtorController {

    private final DebtorResolver debtorResolver;

    @Autowired
    public DebtorController(DebtorResolver debtorResolver) {
        this.debtorResolver = debtorResolver;
    }

    @QueryMapping
    public List<Debtor> getAllDebtors() {
        return debtorResolver.getAllDebtors();
    }

    @QueryMapping
    public Debtor getDebtorById(@Argument Long id) throws Exception {
        return debtorResolver.getDebtorById(id);
    }

    @MutationMapping
    public Debtor addDebtor(@Argument DebtorInput debtorDto) {
        return debtorResolver.addDebtor(debtorDto);
    }

    @MutationMapping
    public Debtor updateDebtor(@Argument Long id, @Argument DebtorInput debtorDto) throws Exception {
        return debtorResolver.updateDebtor(id, debtorDto);
    }

    @MutationMapping
    public Boolean deleteDebtor(@Argument Long id) throws Exception {
        return debtorResolver.deleteDebtor(id);
    }
}