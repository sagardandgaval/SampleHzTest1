package com.csg.org.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class HazelcastConfig {

    @Bean
    public ClientConfig clientConfig() {
        String fileName = "src/main/resources/hazelcast-local.xml";

        System.setProperty("hazelcast.client.config", fileName);
        /*if (System.getProperty("hazelcast.client.config", "").isEmpty()) {
        }*/

        XmlClientConfigBuilder xmlClientConfigBuilder = new XmlClientConfigBuilder();
        Properties properties = new Properties();
        properties.setProperty("env", "dev");
        properties.setProperty("instancename", "test");

        xmlClientConfigBuilder.setProperties(properties);
        ClientConfig clientConfig = xmlClientConfigBuilder.build();

        //clientConfig.addF

        ClientNetworkConfig clientNetworkConfig = clientConfig.getNetworkConfig();
        clientNetworkConfig.setSmartRouting(true).setRedoOperation(true).setConnectionTimeout(5000);

        return clientConfig;
    }

    @Bean
    public HazelcastInstance hazelcastClientInstance(ClientConfig config) {
        System.out.println("Hazelcast URL : " + clientConfig().getNetworkConfig().getAddresses());
        return HazelcastClient.newHazelcastClient(config);
    }


}
