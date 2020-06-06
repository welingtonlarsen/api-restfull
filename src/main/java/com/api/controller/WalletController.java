package com.api.controller;

import com.api.dto.WalletDto;
import com.api.entity.Wallet;
import com.api.response.Response;
import com.api.service.WalletService;
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
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    private WalletService service;

    @PostMapping
    public ResponseEntity<Response<WalletDto>> create(@Valid @RequestBody WalletDto dto, BindingResult result) {

        Response<WalletDto> response = new Response<WalletDto>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Wallet w = service.save(convertDtoToEntity(dto));

        response.setData(convertEntityToDto(w));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Wallet convertDtoToEntity(WalletDto dto) {
        Wallet w = new Wallet();
        w.setId(dto.getId());
        w.setName(dto.getName());
        w.setValue(dto.getValue());

        return w;
    }

    private WalletDto convertEntityToDto(Wallet w) {
        WalletDto dto = new WalletDto();
        dto.setId(w.getId());
        dto.setName(w.getName());
        dto.setValue(w.getValue());

        return dto;
    }


}
