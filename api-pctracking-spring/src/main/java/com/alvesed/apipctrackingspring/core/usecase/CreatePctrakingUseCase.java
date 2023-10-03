package com.alvesed.apipctrackingspring.core.usecase;

import com.alvesed.apipctrackingspring.core.domain.Pctracking;
import com.alvesed.apipctrackingspring.core.ports.in.ListPctrackingPort;
import com.alvesed.apipctrackingspring.core.ports.out.CreatePctrackingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatePctrakingUseCase {

    private final CreatePctrackingPort createPctrackingPort;
    public Pctracking execute(Pctracking pctracking) {
        return createPctrackingPort.createPctrackingPort(pctracking);
    }
}
