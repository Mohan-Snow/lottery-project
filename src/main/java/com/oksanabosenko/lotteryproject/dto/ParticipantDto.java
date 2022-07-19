package com.oksanabosenko.lotteryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParticipantDto {
    private String name;
    private int age;
    private String city;
    private Boolean winner;
    private Integer money;
}
