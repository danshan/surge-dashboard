package com.shanhh.surge.dashboard.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @RequestMapping("/")
    public String index(ModelMap model) throws JsonProcessingException {
        return "dashboard";
    }

    @RequestMapping("/dashboard")
    public String dashboard(ModelMap model) throws JsonProcessingException {
        return "dashboard";
    }

    @RequestMapping("/requests")
    public String policy(@RequestParam(value = "physicalAddress", required = false) String physicalAddress,
                         ModelMap model) throws JsonProcessingException {
        Map<String, String> data = new HashMap<>();
        data.put("physicalAddress", physicalAddress);
        model.addAttribute("data", objectMapper.writeValueAsString(data));
        return "requests";
    }

}
