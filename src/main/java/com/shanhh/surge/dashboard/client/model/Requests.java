package com.shanhh.surge.dashboard.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author honghao.shan
 * @since
 */
@NoArgsConstructor
@Data
public class Requests {
    private List<Request> requests;

    @NoArgsConstructor
    @Data
    public static class Request {
        private Long id;
        private String remoteAddress;
        private Integer inMaxSpeed;
        private Boolean proxyMode;
        private List<String> notes;
        private Integer inCurrentSpeed;
        private Boolean failed;
        private String status;
        private Integer outCurrentSpeed;
        private Boolean completed;
        private Integer sourcePort;
        private Double completedDate;
        private Long outBytes;
        private String sourceAddress;
        private String localAddress;
        private String policyName;
        private Long inBytes;
        private String method;
        private Integer pid;
        private Boolean replica;
        private String rule;
        private Double startDate;
        private Boolean streamHasResponseBody;
        private Double setupCompletedDate;
        @JsonProperty("URL")
        private String url;
        private Integer outMaxSpeed;
        private Boolean modified;
        private Integer engineIdentifier;
        private List<TimingRecords> timingRecords;
        private Boolean streamHasRequestBody;
    }

    @NoArgsConstructor
    @Data
    public static class TimingRecords {
        private Integer durationInMillisecond;
        private String name;
    }
}
