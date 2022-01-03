package com.norin.core.strategy;

import com.norin.core.entity.NorinAccount;
import com.norin.rest.common.entity.AccountDTO;

import java.time.Instant;
import java.util.UUID;

public class AccountEntityStrategy implements EntityStrategy<AccountDTO, NorinAccount> {
    @Override
    public AccountDTO encode(NorinAccount entity) {
        return new AccountDTO(
                entity.getUniqueId().toString(),
                entity.getCash(),
                entity.getGuild(),
                entity.getLoginDate().toEpochMilli(),
                entity.getCreationDate().toEpochMilli()
        );
    }

    @Override
    public NorinAccount decode(AccountDTO dto) {
        return new NorinAccount(
                UUID.fromString(dto.getUniqueId()),
                dto.getCash(),
                dto.getGuildId(),
                Instant.ofEpochMilli(dto.getLoggedAt()),
                Instant.ofEpochMilli(dto.getCreatedAt())
        );
    }
}
