package com.oksanabosenko.lotteryproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participant")
public class ParticipantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "participant_name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "city")
    private String city;

    @OneToOne(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private WinnerDataEntity winner;
}
