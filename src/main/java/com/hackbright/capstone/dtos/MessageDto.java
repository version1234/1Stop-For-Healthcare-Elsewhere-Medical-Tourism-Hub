package com.hackbright.capstone.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MessageDto {
    String from;
    String to;
    String subject;
    String body;
}
