package com.oksanabosenko.lotteryproject.service;

import com.oksanabosenko.lotteryproject.dto.ParticipantDto;
import com.oksanabosenko.lotteryproject.entity.ParticipantEntity;
import com.oksanabosenko.lotteryproject.entity.WinnerDataEntity;
import com.oksanabosenko.lotteryproject.mapper.ParticipantMapper;
import com.oksanabosenko.lotteryproject.repository.ParticipantRepository;
import com.oksanabosenko.lotteryproject.client.RandomServiceClient;
import com.oksanabosenko.lotteryproject.util.exception.NotEnoughParticipantsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class LotteryServiceImpl implements LotteryService {

    private final ParticipantRepository participantRepository;

    private final RandomServiceClient client;


    @Autowired
    public LotteryServiceImpl(ParticipantRepository participantRepository, RandomServiceClient client) {
        this.participantRepository = participantRepository;
        this.client = client;
    }

    @Override
    @Transactional
    public void saveParticipant(ParticipantDto participantDto) {
        ParticipantEntity savedEntity = participantRepository.save(ParticipantMapper.instance.mapDtoToEntity(participantDto));
        log.info("Saved participant entity: {}", savedEntity);
    }

    @Override
    public List<ParticipantDto> getParticipants() {
        List<ParticipantEntity> allParticipants = participantRepository.findAll();
        log.info("Participants retrieved from database: {}", allParticipants);
        return ParticipantMapper.instance.mapEntityListToDtoList(allParticipants);
    }

    public List<String> getAllWinnersNames() {
        List<String> allWinnersNames = participantRepository.getAllWinners();
        log.info("Winners are: {}", allWinnersNames);
        if (Objects.isNull(allWinnersNames) || allWinnersNames.isEmpty()) {
            return Collections.emptyList();
        }
        return allWinnersNames;
    }

    @Override
    @Transactional
    public ParticipantDto startLotteryAndGetWinner() {
        long numberOfParticipants = participantRepository.count();
        if (numberOfParticipants < 2) {
            throw new NotEnoughParticipantsException("It's not possible to proceed with lottery " +
                    "when number of participants is less than 2");
        }
        Integer winnableMoney = client.getRandomNumber(1, 1000);
        log.info("Amount of money to win: {}", winnableMoney);
        List<ParticipantEntity> participants = participantRepository.findAll();
        int winnerId = client.getRandomNumber(0, participants.size() - 1);
        ParticipantEntity winnerParticipant = participants.get(winnerId);
        setAndSaveWinner(winnerParticipant, winnableMoney);
        return ParticipantMapper.instance.mapEntityToDto(winnerParticipant);
    }

    private void setAndSaveWinner(ParticipantEntity winnerParticipant, int winnableMoney) {
        log.info("Lottery winner: {}", winnerParticipant);
        WinnerDataEntity winnerDataEntity = new WinnerDataEntity();
        winnerDataEntity.setParticipant(winnerParticipant);
        winnerDataEntity.setLotteryMoney(winnableMoney);
        winnerParticipant.setWinner(winnerDataEntity);
        ParticipantEntity savedEntity = participantRepository.save(winnerParticipant);
        log.info("Winner Participant saved: {}", savedEntity);
    }
}
