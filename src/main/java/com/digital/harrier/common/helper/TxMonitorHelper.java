package com.digital.harrier.common.helper;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.monitor.v20180724.MonitorClient;
import com.tencentcloudapi.monitor.v20180724.models.MetricDatum;
import com.tencentcloudapi.monitor.v20180724.models.PutMonitorDataRequest;
import com.tencentcloudapi.monitor.v20180724.models.PutMonitorDataResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Map;

public class TxMonitorHelper {

    private String endPoint;
    private String region;

    private Credential cred;
    private MonitorClient monitorClient;
    private Logger logger = Logger.getLogger(TxMonitorHelper.class);

    public TxMonitorHelper(String secretId, String secretKey, String endPoint, String region) {

        this.endPoint = endPoint;
        this.region = region;
        this.cred = new Credential(secretId,secretKey);
        this.monitorClient = newMonitorClient();
    }

    public MonitorClient newMonitorClient() {
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(getEndPoint());
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        MonitorClient client = new MonitorClient(getCred(), getRegion(), clientProfile);
        return client;
    }

    public PutMonitorDataResponse report(Map<String,Long>params,String instanceName,String announceIp,Long announceTimestamp) {
        if(params == null) return null;
        MetricDatum[] metrics = new MetricDatum[params.size()];
        int i = 0;
        for (Map.Entry<String,Long> param:params.entrySet()
             ) {
            MetricDatum metricDatum = new MetricDatum();
            metricDatum.setMetricName(param.getKey());
            metricDatum.setValue(param.getValue());
            metrics[i++] = metricDatum;
        }
        PutMonitorDataRequest putMonitorDataRequest = new PutMonitorDataRequest();
        if(!StringUtils.isEmpty(instanceName)) putMonitorDataRequest.setAnnounceInstance(instanceName);
        putMonitorDataRequest.setMetrics(metrics);
        if(!StringUtils.isEmpty(announceIp)) putMonitorDataRequest.setAnnounceIp(announceIp);
        if(announceTimestamp>0) putMonitorDataRequest.setAnnounceTimestamp(announceTimestamp);

        try {
            return getMonitorClient().PutMonitorData(putMonitorDataRequest);
        } catch (TencentCloudSDKException e) {
            getLogger().error(e.getMessage());
        }

        return null;
    }

    public PutMonitorDataResponse report(Map<String,Long>params,String instanceName) {
        return report(params,instanceName,"",0L);
    }

    public PutMonitorDataResponse report(Map<String,Long>params) {
        return report(params,"");
    }


    private String getEndPoint() {
        return endPoint;
    }

    private String getRegion() {
        return region;
    }

    private Credential getCred() {
        return cred;
    }

    private MonitorClient getMonitorClient() {
        return monitorClient;
    }

    private Logger getLogger() {
        return logger;
    }
}
