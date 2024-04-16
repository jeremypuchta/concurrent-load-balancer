package com.jeremypuchta;

public class Main {
    public static void main(String[] args) {
        LoadBalancer loadBalancer = new LoadBalancer(new RandomSelectionStrategy());
        loadBalancer.register(new BackendInstance("1.2.3.4"));
        loadBalancer.register(new BackendInstance("0.0.0.0"));
        System.out.println(loadBalancer.getInstances().toString());
    }
}