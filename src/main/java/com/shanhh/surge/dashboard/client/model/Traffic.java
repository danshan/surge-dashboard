package com.shanhh.surge.dashboard.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author honghao.shan
 * @since 1.0.0
 */
@Data
public class Traffic {
    private Double startTime;
    private Map<String, Connector> connector;
    @JsonProperty("interface")
    private Map<String, Interface> interfaces;

    @Data
    public static class Connector implements Serializable {
        private Long in;
        private Long inCurrentSpeed;
        private Long inMaxSpeed;
        private Long out;
        private Long outCurrentSpeed;
        private Long outMaxSpeed;
        private List<Statistic> statistics;
    }

    @Data
    public static class Statistic implements Serializable {
        private Integer rttcur;
        private Integer srtt;
        private Integer rttvar;
        private Integer txpackets;
        private Integer txretransmitpackets;
    }

    @Data
    public static class Interface implements Serializable{
        private Long in;
        private Long inCurrentSpeed;
        private Long inMaxSpeed;
        private Long out;
        private Long outCurrentSpeed;
        private Long outMaxSpeed;
    }
}
