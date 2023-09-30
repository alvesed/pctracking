package com.alvesed.apipctrackingspring.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Pctracking {

    private Long idPctracking;
    private LocalDateTime dateTimeRequestTracking;

}
