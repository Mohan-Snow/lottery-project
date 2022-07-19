package com.oksanabosenko.lotteryproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "winner_data")
public class WinnerDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lottery_money")
    private Integer lotteryMoney;

    @OneToOne()
    @JoinColumn(name = "participant_id")
    @ToString.Exclude
    private ParticipantEntity participant;
}
