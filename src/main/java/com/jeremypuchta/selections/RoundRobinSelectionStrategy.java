package com.jeremypuchta.selections;

import com.jeremypuchta.BackendInstance;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinSelectionStrategy implements SelectionStrategy {
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Optional<BackendInstance> select(Map<String, BackendInstance> instances) {
        final List<String> addresses = instances.keySet().stream().toList();
        if (addresses.isEmpty()) return Optional.empty();
        final int index = counter.getAndIncrement() % addresses.size();
        return Optional.of(instances.get(addresses.get(index)));
    }
}
