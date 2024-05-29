package com.habsida.morago.resolver;

//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.dtos.LoginResponse;
import com.habsida.morago.dtos.LoginUserDto;
import com.habsida.morago.dtos.RegisterUserDto;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.service.AuthenticationService;
import com.habsida.morago.service.JwtService;
import lombok.RequiredArgsConstructor;
//import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Component
@RequiredArgsConstructor
public class UserResolver  {



}
