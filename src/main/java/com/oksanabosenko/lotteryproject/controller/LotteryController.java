package com.oksanabosenko.lotteryproject.controller;

import com.oksanabosenko.lotteryproject.dto.ParticipantDto;
import com.oksanabosenko.lotteryproject.service.LotteryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/lottery")
@Slf4j
public class LotteryController {

    private LotteryServiceImpl service;

    @Autowired
    public LotteryController(LotteryServiceImpl service) {
        this.service = service;
    }

    @PostMapping(path = "/participant")
    public ResponseEntity<String> registerParticipant(@RequestBody ParticipantDto participant) {
        log.info("Participant to be saved: {}", participant);
        service.saveParticipant(participant);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Participant Saved");
    }

    @GetMapping(path = "/participant")
    public ResponseEntity<List<ParticipantDto>> getAllParticipants() {
        log.info("Starting retrieving participants");
        List<ParticipantDto> participants = service.getParticipants();
        return ResponseEntity.status(HttpStatus.OK)
                .body(participants);
    }

    @GetMapping(path = "/start")
    public ResponseEntity<ParticipantDto> startLottery() {
        log.info("Starting the lottery");
        ParticipantDto winner = service.startLotteryAndGetWinner();
        return ResponseEntity.status(HttpStatus.OK)
                .body(winner);
    }

    @GetMapping(path = "/winners")
    public ResponseEntity<List<String>> getWinners() {
        log.info("Retrieving winners");
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllWinnersNames());
    }
}
