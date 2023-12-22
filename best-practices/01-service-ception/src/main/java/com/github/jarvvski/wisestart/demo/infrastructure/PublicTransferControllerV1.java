package com.github.jarvvski.wisestart.demo.infrastructure;

import com.github.jarvvski.wisestart.demo.application.CreateTransferCommand;
import com.github.jarvvski.wisestart.demo.application.TransferCreatedResponse;
import com.github.jarvvski.wisestart.demo.application.TransferCreationService;
import com.github.jarvvski.wisestart.demo.domain.Transfer;
import com.github.jarvvski.wisestart.demo.domain.TransferId;
import com.github.jarvvski.wisestart.demo.domain.TransferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/public/v1/transfer")
@RequiredArgsConstructor
@Slf4j
public class PublicTransferControllerV1 {

    private final TransferCreationService transferCreationService;

    @PostMapping("/create")
    public ResponseEntity<TransferCreatedResponse> createTransfer(@RequestBody CreateTransferCommand createTransferCommand) {
        log.info("Creating transfer via http call");

        final var response = transferCreationService.createTransfer(createTransferCommand);
        return ResponseEntity.created(
                URI.create("/public/v1/transfer" + response.id())
            ).body(response);
    }
}
