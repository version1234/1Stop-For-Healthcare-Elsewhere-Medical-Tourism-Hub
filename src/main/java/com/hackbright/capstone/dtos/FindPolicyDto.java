package com.hackbright.capstone.dtos;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindPolicyDto {

    private Long profileid;

    private Long policyid;

    private Long price;

    private Date startDate;

    private Date endDate;
}
