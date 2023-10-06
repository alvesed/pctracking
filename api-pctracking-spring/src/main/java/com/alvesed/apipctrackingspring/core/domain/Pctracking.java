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
public class Pctracking implements Comparable<Pctracking> {

    private String idPctracking;
    private LocalDateTime dateTimeRequestTracking;

    public LocalDateTime getDateTimeRequestTracking() {
        if (this.dateTimeRequestTracking == null) {
            setDateTimeRequestTracking(LocalDateTime.now());
        }
        return this.dateTimeRequestTracking;
    }

    @Override
    public int compareTo(Pctracking o) {
        return this.getIdPctracking().compareTo(o.getIdPctracking());
    }
}
