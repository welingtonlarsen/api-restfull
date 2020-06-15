package com.api.service.service.impl;

import com.api.entity.UserWallet;
import com.api.repository.UserWalletRepository;
import com.api.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWalletServiceImpl implements UserWalletService {

    @Autowired
    UserWalletRepository repository;

    @Override
    public UserWallet save(UserWallet uw) {
        return repository.save(uw);
    }
}
