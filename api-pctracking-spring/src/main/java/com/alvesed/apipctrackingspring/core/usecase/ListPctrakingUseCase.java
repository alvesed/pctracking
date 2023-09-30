package com.alvesed.apipctrackingspring.core.usecase;

import com.alvesed.apipctrackingspring.core.domain.Pctracking;
import com.alvesed.apipctrackingspring.core.ports.in.ListPctrackingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPctrakingUseCase {

    private final ListPctrackingPort listPctrackingPort;
    public List<Pctracking> execute() {
        return listPctrackingPort.listPctrackingPort();
    }
}
