package com.jeremypuchta;

import java.util.Map;
import java.util.Optional;

public interface SelectionStrategy {
    Optional<BackendInstance> select(Map<String, BackendInstance> instances);
}
