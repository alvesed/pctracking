package com.alvesed.apipctrackingspring.entrypoint.rest.resources;

import com.alvesed.apipctrackingspring.entrypoint.rest.dto.PctrackingRequest;
import com.alvesed.apipctrackingspring.entrypoint.rest.dto.PctrackingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pctracking")
@RequiredArgsConstructor
public class PctrackingResource {

    @GetMapping
    public List<PctrackingResponse> listRequests() {
        return null;
    }

    @PostMapping
    public PctrackingResponse createRequest(@RequestBody PctrackingRequest request) {
        return null;
    }

}
