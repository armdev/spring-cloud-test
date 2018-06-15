/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.project.collector.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author armena
 */
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ServerInfo {
    
    private String id;
    private String applicationName;
    private String instanceId;
    private String healthCheckUrl;
    private String homePageUrl;
    private String secureVipAddress;
    private String asgName;
    private String statusPageUrl;
    private String actionType;
    private Long lastDirtyTimestamp;
    private String vipAddress;
    private Integer port;
    private String leaseInfo;
    private String ip;
    private String secureHealthCheckUrl;
    private String status;
    private Map<String, String> metadata;
    
    
}
