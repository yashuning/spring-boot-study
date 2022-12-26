package com.nys.study.spring.springbootstudy.main.elasticsearch;

import com.nys.study.spring.springbootstudy.common.util.JsonTool;
import com.nys.study.spring.springbootstudy.elasticsearch.IndexOperateService;
import com.nys.study.spring.springbootstudy.elasticsearch.Product;
import com.nys.study.spring.springbootstudy.main.BaseTest;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: elasticsearch 测试用例
 * @date 2022/12/22 7:43 下午
 */
public class ElasticsearchTest extends BaseTest {
    @Resource
    private IndexOperateService indexOperateService;

    @Test
    public void testIsExistIndex() throws Exception {
        String index = "product";
        boolean existIndex = indexOperateService.isExistIndex(index);
        System.out.println(existIndex);
    }

    @Test
    public void testGetIndex() throws Exception {
        String index = "product";
        Product t = indexOperateService.get(index, "1", Product.class);
        System.out.println(JsonTool.toJsonString(t));
    }

    @Test
    public void testSearchByQuery() throws Exception {
        String index = "product";
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("name.keyword", "xiaomi phone"));
        List<Product> productList = indexOperateService.searchByQuery(index, sourceBuilder, Product.class);
        assert CollectionUtils.isNotEmpty(productList);
        System.out.println(JsonTool.toJsonString(productList));
    }

}
