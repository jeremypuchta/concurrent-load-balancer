package com.jeremypuchta.selections;

import com.jeremypuchta.BackendInstance;

import java.util.Map;
import java.util.Optional;

public interface SelectionStrategy {
    Optional<BackendInstance> select(Map<String, BackendInstance> instances);
}
