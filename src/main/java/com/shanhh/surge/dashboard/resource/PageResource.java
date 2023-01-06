package com.shanhh.surge.dashboard.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanhh.surge.dashboard.service.SurgeService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author honghao.shan
 * @since 1.0.0
 */
@Controller
@RequiredArgsConstructor
public class PageResource {

    private final ObjectMapper objectMapper;
    private final SurgeService surgeService;

    @RequestMapping("/")
    public String index(ModelMap model) throws JsonProcessingException {
        return "dashboard";
    }

    @RequestMapping("/dashboard")
    public String dashboard(ModelMap model) throws JsonProcessingException {
        return "dashboard";
    }

    @RequestMapping("/requests")
    public String policy(@RequestParam(value = "sourceIP", required = false) String sourceIP,
                         ModelMap model) throws JsonProcessingException {
        Map<String, String> data = new HashMap<>();
        if (StringUtils.isNotBlank(sourceIP)) {
            data.put("sourceIP", sourceIP);
            surgeService.findDevices().stream()
                    .filter(device -> sourceIP.equals(device.getSourceIP()))
                    .findFirst()
                    .ifPresent(deviceVO -> data.put("device", sourceIP));
        }
        model.addAttribute("data", objectMapper.writeValueAsString(data));
        return "requests";
    }

}
