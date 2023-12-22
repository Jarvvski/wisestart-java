package com.github.jarvvski.wisestart.demo.application;

import com.github.jarvvski.wisestart.demo.domain.Transfer;
import com.github.jarvvski.wisestart.demo.domain.TransferId;
import com.github.jarvvski.wisestart.demo.domain.TransferRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferCreationService {

    private final TransferRepository transferRepository;

    public TransferCreatedResponse createTransfer(CreateTransferCommand createTransferCommand) {
        final var transfer = Transfer.builder()
            .id(TransferId.next())
            .money(createTransferCommand.money())
            .source(createTransferCommand.source())
            .recipient(createTransferCommand.recipient())
            .creationDate(Instant.now())
            .lastUpdatedDate(Instant.now())
            .build();

        transferRepository.save(transfer);

        return transfer.toCreatedResponse();
    }

}
