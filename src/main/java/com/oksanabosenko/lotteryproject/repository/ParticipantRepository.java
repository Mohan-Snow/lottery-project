package com.oksanabosenko.lotteryproject.repository;

import com.oksanabosenko.lotteryproject.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

    @Query("select p.name from ParticipantEntity p " +
            "right join WinnerDataEntity w on p.id = w.participant.id ")
    List<String> getAllWinners();
}
