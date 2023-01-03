package com.shanhh.surge.dashboard.client;

import com.shanhh.surge.dashboard.client.model.Devices;
import com.shanhh.surge.dashboard.client.model.PolicyList;
import com.shanhh.surge.dashboard.client.model.Requests;
import com.shanhh.surge.dashboard.client.model.Traffic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author honghao.shan
 * @since 1.0.0
 */
@FeignClient(name = "surge-client", url = "${app.feign.remote.surge.url}", configuration = SurgeClientConfig.class)
public interface SurgeClient {

    @GetMapping("/v1/policies")
    PolicyList getPolicies();

    @GetMapping("/v1/traffic")
    Traffic getTraffic();

    @GetMapping("/v1/devices")
    Devices getDevices();

    @GetMapping("/v1/requests/recent")
    Requests getRecentRequests();

    @GetMapping("/v1/requests/recent")
    Requests getActiveRequests();
}
