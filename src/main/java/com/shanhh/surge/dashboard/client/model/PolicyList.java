package com.shanhh.surge.dashboard.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author honghao.shan
 * @since
 */
@Data
public class PolicyList implements Serializable {
    private static final long serialVersionUID = -2759985347981594415L;

    @JsonProperty("proxies")
    private List<String> proxies;

    @JsonProperty("policy-groups")
    private List<String> policyGroups;
}
