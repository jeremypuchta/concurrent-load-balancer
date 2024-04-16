package com.jeremypuchta;

import com.jeremypuchta.exceptions.MaximumCapacityException;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LoadBalancer {
    private final static int MAX_INSTANCES = 10;

    private final Map<String, BackendInstance> instances = new ConcurrentHashMap<>(MAX_INSTANCES);
    private final SelectionStrategy selectionStrategy;

    public LoadBalancer(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public void register(final BackendInstance instance) {
        validateRegistration(instance);
        instances.put(instance.getAddress(), instance);
    }

    private void validateRegistration(BackendInstance instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Instance not available");
        }
        if (instance.getAddress() == null) {
            throw new IllegalArgumentException("Instance address is null");
        }
        if (instance.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Instance address is empty");
        }
        if (instances.size() == MAX_INSTANCES) {
            throw new MaximumCapacityException("Maximum capacity is reached. Cannot register another instance.");
        }
    }

    public Optional<BackendInstance> selectInstance() {
        return selectionStrategy.select(instances);
    }

    public Map<String, BackendInstance> getInstances() {
        return instances;
    }
}
