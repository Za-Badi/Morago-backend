package com.habsida.morago.controller;

import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.DepositsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deposits")
public class DepositsController {

    @GetMapping("test")
    public String test(Model model){
        model.addAttribute("message", "this is deposit test controller !");
        return "deposits/test";
    }

    @Autowired
    private DepositsService depositsService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/create")
    public String showCreateDeposits(Model model) {
        model.addAttribute("deposit", new Deposits());

        return "deposits/create";
    }

    @PostMapping("/create")
    public String createDeposits(@ModelAttribute Deposits deposits, @RequestParam Long userId) {
        System.out.println("Received userId: " + userId);
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            deposits.setUser(user);
            depositsService.saveDeposit(deposits);
        }

        return "redirect:/deposits/list";
    }

    @GetMapping("/list")
    public String showDepositsList(Model model) {
        List<Deposits> allDeposits = depositsService.getAllDeposits();
        model.addAttribute("deposits", allDeposits);
        return "deposits/allDeposit";
    }
}
