package com.alvesed.apipctrackingspring.entrypoint.rest.resources;

import com.alvesed.apipctrackingspring.core.domain.Pctracking;
import com.alvesed.apipctrackingspring.core.usecase.ListPctrakingUseCase;
import com.alvesed.apipctrackingspring.entrypoint.rest.dto.PctrackingRequest;
import com.alvesed.apipctrackingspring.entrypoint.rest.dto.PctrackingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pctracking")
@RequiredArgsConstructor
public class PctrackingResource {

    private final ListPctrakingUseCase listPctrakingUseCase;

    @GetMapping
    public List<PctrackingResponse> listRequests() {
        System.out.println("Listing requests");
        var listPctracking = listPctrakingUseCase.execute();

        return toListPctrackingResponse(listPctracking);
        
    }

    private List<PctrackingResponse> toListPctrackingResponse(List<Pctracking> listPctracking) {

        return listPctracking.stream()
                .map(pctracking ->
                        new PctrackingResponse(pctracking.getIdPctracking(), pctracking.getDateTimeRequestTracking()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public PctrackingResponse createRequest(@RequestBody PctrackingRequest request) {
        System.out.println("Create request");
        return null;
    }

}
