package com.nys.study.spring.springbootstudy.main.repository.elasticsearch;

import com.nys.study.spring.springbootstudy.common.util.JsonTool;
import com.nys.study.spring.springbootstudy.main.BaseTest;
import com.nys.study.spring.springbootstudy.dao.es.EsIndexOperateService;
import com.nys.study.spring.springbootstudy.dao.es.index.ProductIndex;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: elasticsearch 测试用例
 * @date 2022/12/22 7:43 下午
 */
public class ElasticsearchTest extends BaseTest {
    @Resource
    private EsIndexOperateService esIndexOperateService;

    @Test
    public void testIsExistIndex() throws Exception {
        String index = "product";
        boolean existIndex = esIndexOperateService.isExistIndex(index);
        System.out.println(existIndex);
    }

    @Test
    public void testGetIndex() throws Exception {
        String index = "product";
        ProductIndex t = esIndexOperateService.get(index, "1", ProductIndex.class);
        System.out.println(JsonTool.toJsonString(t));
    }

    @Test
    public void testSearchByQuery() throws Exception {
        String index = "product";
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("name.keyword", "xiaomi phone"));
        List<ProductIndex> productList = esIndexOperateService.searchByQuery(index, sourceBuilder, ProductIndex.class);
        assert CollectionUtils.isNotEmpty(productList);
        System.out.println(JsonTool.toJsonString(productList));
    }

}
