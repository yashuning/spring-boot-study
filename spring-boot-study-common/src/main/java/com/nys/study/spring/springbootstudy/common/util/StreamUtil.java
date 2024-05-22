package com.nys.study.spring.springbootstudy.common.util;

import org.apache.commons.collections4.ListUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: stream工具
 * @date 2024/3/15 15:22
 */
public class StreamUtil {

    /**
     * List转Map，如果有重复的键，则保留最后一个
     * @param list 待转换列表
     * @param keyMapper 从列表元素获取key的函数
     * @param valueMapper 从列表元素获取value的函数
     * @return 转换后的Map
     * @param <T> 列表元素类型
     * @param <K> Map key 类型
     * @param <V> Map value 类型
     */
    public static <T, K, V> Map<K, V> listToMapMergeIfRepeat(List<T> list,
                                                             Function<T, K> keyMapper,
                                                             Function<T, V> valueMapper) {
        return ListUtils.emptyIfNull(list)
                .stream()
                .collect(Collectors.toMap(keyMapper, valueMapper, (oldValue, newValue) -> newValue));
    }

    /**
     * List转Map，如果有重复的键，则保留最后一个
     * @param list list 待转换列表
     * @param keyMapper keyMapper 从列表元素获取key的函数
     * @return 转换后的Map
     * @param <T> 列表元素类型
     * @param <K> Map key 类型
     */
    public static <T, K> Map<K, T> listToMapMergeIfRepeat(List<T> list, Function<T, K> keyMapper) {
        return listToMapMergeIfRepeat(list, keyMapper, Function.identity());
    }

    public static <T, R> Set<R> listToSet(List<T> list, Function<T, R> mapper) {
        return ListUtils.emptyIfNull(list)
                .stream()
                .map(mapper)
                .collect(Collectors.toSet());
    }

    /**
     * 合并两个list 并进行去重<p>
     * 注意：<p>
     *     1、传入list类型如果是对象，需要检查是否实现 equals() 和 hashCode() 方法<p>
     *     2、尽可能不要在多线程下使用，防止线程安全问题
     * @param list1
     * @param list2
     * @return 返回去重后的合并列表
     * @param <T> 是否实现 equals() 和 hashCode() 方法
     */
    public static <T> List<T> listMergeAndDistinct(List<T> list1, List<T> list2){
        return Stream.of(ListUtils.emptyIfNull(list1), ListUtils.emptyIfNull(list2))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

}
