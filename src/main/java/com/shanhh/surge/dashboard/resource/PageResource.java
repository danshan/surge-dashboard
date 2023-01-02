package com.shanhh.surge.dashboard.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/policy")
    public String policy(ModelMap model) throws JsonProcessingException {
        return "policy";
    }


    @RequestMapping("/dashboard")
    public String dashboard(ModelMap model) throws JsonProcessingException {
        return "dashboard";
    }

}
