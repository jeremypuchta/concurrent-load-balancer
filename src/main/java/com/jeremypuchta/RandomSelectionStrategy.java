package com.jeremypuchta;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSelectionStrategy implements SelectionStrategy {
    @Override
    public Optional<BackendInstance> select(Map<String, BackendInstance> instances) {
        if (instances.isEmpty()) return Optional.empty();
        List<String> addresses = instances.keySet().stream().toList();
        String randomAddress = addresses.get(ThreadLocalRandom.current().nextInt(instances.keySet().size()));
        return Optional.of(instances.get(randomAddress));
    }
}
