package com.nys.study.spring.springbootstudy.main.configure;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.util.Asserts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: Elasticsearch配置
 * @date 2022/12/22 5:17 下午
 */
@Configuration
@Slf4j
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.rest.uris}")
    private List<String> ipAddressList;

    @Value("${spring.elasticsearch.rest.connection-timeout}")
    private int connectionTimeout;

    @Value("${spring.elasticsearch.rest.read-timeout}")
    private int readTimeout;

    /**
     * 创建 HttpHost 对象
     *
     * @return 返回 HttpHost 对象数组
     */
    private HttpHost[] createHttpHost() {
        Asserts.check(!CollectionUtils.isEmpty(ipAddressList), "ElasticSearch cluster ip address cannot empty");

        HttpHost[] httpHosts = new HttpHost[ipAddressList.size()];
        for (int i = 0, len = ipAddressList.size(); i < len; i++) {
            String ipAddress = ipAddressList.get(i);
            String[] values = ipAddress.split(":");

            String ip = values[0];
            int port = Integer.parseInt(values[1]);
            // 创建 HttpHost
            httpHosts[i] = new HttpHost(ip, port);
        }

        return httpHosts;

    }

    /**
     * highLevelClient 客户端
     */
    @Bean(name = "restHighLevelClient")
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder builder = RestClient.builder(createHttpHost());
        return new RestHighLevelClient(builder);
    }

}
