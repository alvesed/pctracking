package com.alvesed.apipctrackingspring.core.ports.out;

import com.alvesed.apipctrackingspring.core.domain.Pctracking;

import java.util.List;

public interface CreatePctrackingPort {

    Pctracking createPctrackingPort(Pctracking pctracking);

}
