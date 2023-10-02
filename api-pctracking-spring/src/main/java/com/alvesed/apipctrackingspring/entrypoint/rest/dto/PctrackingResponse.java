package com.alvesed.apipctrackingspring.entrypoint.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PctrackingResponse {

    private Long idPctracking;
    private LocalDateTime dateTimeRequestTracking;
}
