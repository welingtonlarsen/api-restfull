package com.api.service;

import com.api.entity.WalletItem;
import com.api.util.enums.TypeEnum;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WalletItemService {

    WalletItem save(WalletItem i);

    Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page);

    List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type);

    BigDecimal sumByWalletId(Long wallet);

    Optional<WalletItem> findById(Long id);

    void deleteById(Long id);

}
