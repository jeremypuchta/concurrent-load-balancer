package com.jeremypuchta;

import com.jeremypuchta.exceptions.MaximumCapacityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LoadBalancerTest {
    private LoadBalancer loadBalancer;

    @BeforeEach
    void setUp() {
        loadBalancer = new LoadBalancer(new RandomSelectionStrategy());
    }

    @Test
    void shouldRegisterInstance() {
        loadBalancer.register(new BackendInstance("1.2.3.4"));
        assertThat(loadBalancer.getInstances()).isNotEmpty();
    }

    @Test
    void shouldThrowWhenInstanceIsNull() {
        assertThatThrownBy(() -> loadBalancer.register(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Instance not available");
    }

    @Test
    void shouldThrowWhenInstanceAddressIsNull() {
        assertThatThrownBy(() -> loadBalancer.register(new BackendInstance(null)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Instance address is null");
    }

    @Test
    void shouldThrowWhenInstanceAddressIsEmpty() {
        assertThatThrownBy(() -> loadBalancer.register(new BackendInstance("")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Instance address is empty");
    }

    @Test
    void shouldThrowWhenMaximumCapacityIsReached() {
        for (int i = 0; i < 10; i++) {
            loadBalancer.getInstances().put("" + i, new BackendInstance("" + 1));
        }
        assertThatThrownBy(() -> loadBalancer.register(new BackendInstance("11")))
                .isInstanceOf(MaximumCapacityException.class)
                .hasMessage("Maximum capacity is reached. Cannot register another instance.");
    }
}