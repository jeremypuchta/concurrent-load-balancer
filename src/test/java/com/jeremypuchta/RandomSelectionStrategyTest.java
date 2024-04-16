package com.jeremypuchta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomSelectionStrategyTest {
    private SelectionStrategy selectionStrategy;
    private LoadBalancer loadBalancer;

    @BeforeEach
    void setUp() {
        loadBalancer = new LoadBalancer(new RandomSelectionStrategy());
        selectionStrategy = new RandomSelectionStrategy();
    }

    @Test
    void shouldReturnEmptyOptionalWhenNoInstancesExist() {
        assertThat(selectionStrategy.select(loadBalancer.getInstances())).isEmpty();
    }
}