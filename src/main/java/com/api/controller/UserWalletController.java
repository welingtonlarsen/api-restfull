package com.api.controller;

import com.api.dto.UserWalletDTO;
import com.api.entity.User;
import com.api.entity.UserWallet;
import com.api.entity.Wallet;
import com.api.response.Response;
import com.api.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user-wallet")
public class UserWalletController {

    @Autowired
    private UserWalletService service;

    @PostMapping
    public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result) {

        Response<UserWalletDTO> response = new Response<UserWalletDTO>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        UserWallet uw = service.save(this.convertDtoToEntity(dto));
        response.setData(this.convertEntityToDto(uw));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public UserWallet convertDtoToEntity(UserWalletDTO dto) {
        UserWallet uw = new UserWallet();

        User u = new User();
        u.setId(dto.getUsers());

        Wallet w = new Wallet();
        w.setId(dto.getWallet());

        uw.setId(dto.getId());
        uw.setUsers(u);
        uw.setWallet(w);

        return uw;
    }

    public UserWalletDTO convertEntityToDto(UserWallet uw) {
        UserWalletDTO dto = new UserWalletDTO();
        dto.setId(uw.getId());
        dto.setUsers(uw.getUsers().getId());
        dto.setWallet(uw.getWallet().getId());

        return dto;
    }
}
