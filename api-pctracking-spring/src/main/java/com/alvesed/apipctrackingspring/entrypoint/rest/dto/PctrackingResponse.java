package com.alvesed.apipctrackingspring.entrypoint.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PctrackingResponse {

    private String idPctracking;
    private LocalDateTime dateTimeRequestTracking;

    public String getDateTimeRequestTracking() {
        return dateTimeRequestTracking.format(DateTimeFormatter.ofPattern("d/MMM/uuuu - HH:mm:ss"));
    }
}
