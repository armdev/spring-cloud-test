package io.project.collector.controllers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import io.project.collector.dto.ServerInfo;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author armena
 */
@RestController
@RequestMapping("/api/v2/tests")
public class TestController {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping(path = "/discovery", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> testDiscovery() {
        List<String> services = discoveryClient.getServices();

        return ResponseEntity.status(HttpStatus.OK).body(services);
    }

    @GetMapping(path = "/micro", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> testEureka() {
        final List<ServerInfo> resultFromEureka = printAvailableServices();

        return ResponseEntity.status(HttpStatus.OK).body(resultFromEureka);
    }

    public List<ServerInfo> printAvailableServices() {
        List<ServerInfo> serverList = new ArrayList<>();
        try {

            Applications applications = eurekaClient.getApplications();

            ServerInfo info = new ServerInfo();

            List<Application> appList = applications.getRegisteredApplications();

            for (Application app : appList) {
                List<InstanceInfo> instances = app.getInstances();
                for (InstanceInfo instance : instances) {

                    info = new ServerInfo(instance.getId(), instance.getAppName(),
                            instance.getInstanceId(), instance.getHealthCheckUrl(), instance.getHomePageUrl(), instance.getVIPAddress(),
                            instance.getASGName(), instance.getStatusPageUrl(), instance.getActionType().toString(),
                            instance.getLastDirtyTimestamp(),
                            instance.getVIPAddress(), instance.getPort(), instance.getLeaseInfo().toString(), instance.getIPAddr(),
                            instance.getSecureHealthCheckUrl(),
                            instance.getStatus().name(),
                            instance.getMetadata());
                    serverList.add(info);

                }

            }

//            applications.getRegisteredApplications().forEach((registeredApplication) -> {
//
//                registeredApplication.getInstances().forEach((instance) -> {
//
//                    LOGGER.info("*******************start************************");
//                    LOGGER.info("App Name " + instance.getAppName());
//                    LOGGER.info("Instance Id " + instance.getInstanceId());
//                    LOGGER.info("HealthCheckUrl  " + instance.getHealthCheckUrl());
//                    LOGGER.info("getHomePageUrl  " + instance.getHomePageUrl());
//                    LOGGER.info("getSecureVipAddress  " + instance.getSecureVipAddress());
//                    LOGGER.info("getASGName  " + instance.getASGName());
//                    LOGGER.info("getStatusPageUrl  " + instance.getStatusPageUrl());
//                    LOGGER.info("getActionType  " + instance.getActionType().toString());
//                    LOGGER.info("getLastDirtyTimestamp  " + instance.getLastDirtyTimestamp());
//                    LOGGER.info("getVIPAddress  " + instance.getVIPAddress());
//                    LOGGER.info("getPort  " + instance.getPort());
//                    LOGGER.info("getLeaseInfo  " + instance.getLeaseInfo().toString());
//                    LOGGER.info("ip  " + instance.getIPAddr());
//                    LOGGER.info("ID  " + instance.getId());
//                    LOGGER.info("getSecureHealthCheckUrl  " + instance.getSecureHealthCheckUrl());
//                    LOGGER.info("status name  " + instance.getStatus().name());
//                    LOGGER.info("metadata  " + instance.getMetadata().toString());
//
//                    LOGGER.info("*******************stop************************");
//
//                }
//                );
//
//            });
        } catch (Exception e) {
        }
        return serverList;
    }

}
