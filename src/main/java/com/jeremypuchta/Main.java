package com.jeremypuchta;

import com.jeremypuchta.selections.RoundRobinSelectionStrategy;

public class Main {
    public static void main(String[] args) {
        LoadBalancer loadBalancer = new LoadBalancer(new RoundRobinSelectionStrategy());
        loadBalancer.register(new BackendInstance("128.168.1.1:80"));
        loadBalancer.register(new BackendInstance("128.168.1.1:81"));
        loadBalancer.register(new BackendInstance("128.168.1.1:82"));
        loadBalancer.selectInstance().get().getAddress();
    }
}