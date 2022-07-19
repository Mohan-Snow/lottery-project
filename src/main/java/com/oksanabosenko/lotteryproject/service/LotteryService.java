package com.oksanabosenko.lotteryproject.service;

import com.oksanabosenko.lotteryproject.dto.ParticipantDto;

import java.util.List;

public interface LotteryService {

    void saveParticipant(ParticipantDto participant);

    List<ParticipantDto> getParticipants();

    ParticipantDto startLotteryAndGetWinner();
}
