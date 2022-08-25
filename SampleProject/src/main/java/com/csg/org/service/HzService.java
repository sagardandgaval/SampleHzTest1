package com.csg.org.service;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HzService {
    HazelcastInstance hazelcastInstance;

    public HzService(@Qualifier("hazelcastClientInstance") HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }


}
