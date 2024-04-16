package com.jeremypuchta.selections;

import com.jeremypuchta.BackendInstance;
import com.jeremypuchta.LoadBalancer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class RoundRobinSelectionStrategyTest {
    LoadBalancer loadBalancer;

    @BeforeEach
    void setUp() {
        loadBalancer = new LoadBalancer(new RoundRobinSelectionStrategy());
    }

    @Test
    void shouldSelectInstancesInOrder() {
        registerInstance("128.164.2.1:80");
        registerInstance("128.164.2.1:81");
        registerInstance("128.164.2.1:82");
        BackendInstance expectedFirst = new BackendInstance("128.164.2.1:80");
        BackendInstance expectedSecond = new BackendInstance("128.164.2.1:81");

        Optional<BackendInstance> backendInstanceOne = loadBalancer.selectInstance();
        Optional<BackendInstance> backendInstanceSecond = loadBalancer.selectInstance();

        assertThat(backendInstanceOne).isEqualTo(expectedFirst);
        assertThat(backendInstanceSecond).isEqualTo(expectedSecond);
    }

    @Test
    void shouldReturnEmptyOptionalIfNoInstancesRegistered() {
        assertThat(loadBalancer.selectInstance()).isEmpty();
    }

    private void registerInstance(String address) {
        loadBalancer.register(new BackendInstance(address));
    }
}