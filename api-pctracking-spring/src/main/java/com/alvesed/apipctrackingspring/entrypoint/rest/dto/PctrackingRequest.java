package com.alvesed.apipctrackingspring.entrypoint.rest.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PctrackingRequest {

    private String idPctracking;
    private LocalDateTime dateTimeRequestTracking;

}
