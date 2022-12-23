package com.nys.study.spring.springbootstudy.elasticsearch;

import com.nys.study.spring.springbootstudy.common.util.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: product索引操作
 * @date 2022/12/22 7:41 下午
 */
@Slf4j
@Service
public class IndexOperateService {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 判断索引是否存在
     *
     * @param index 索引
     * @return 返回 true，表示存在
     */
    public boolean isExistIndex(String index) throws Exception {
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        getIndexRequest.local(false);
        getIndexRequest.humanReadable(true);
        getIndexRequest.includeDefaults(false);

        try {
            return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("判断索引是否存在异常！");
            throw new Exception("判断索引是否存在异常");
        }
    }

    /**
     * 根据索引和文档 ID 获取数据
     * @param index      索引
     * @param id         文档 ID
     * @param resultType
     * @return
     * @throws IOException
     */
    public <T> T get(String index, String id, Class<T> resultType) throws IOException {
        GetRequest getRequest = new GetRequest(index, id);
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        if (getResponse.isExists()) {
            String sourceAsString = getResponse.getSourceAsString();
            return JsonTool.toObject(sourceAsString, resultType);
        } else {
            log.error("没有找到该 id 的文档");
            return null;
        }
    }

}
