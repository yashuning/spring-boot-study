package com.nys.study.spring.springbootstudy.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 字符串工具
 * @date 2024/3/15 15:23
 */
public class StringUtil {

    public static final String SPLIT_COMMA = ",";

    public static List<Long> split2LongListDefault(String str) {
        return split2LongList(str, SPLIT_COMMA);
    }

    public static List<Long> split2LongList(String str, String separatorChars) {
        return split2List(str, separatorChars, Long::valueOf);
    }

    public static List<Integer> split2IntegerListDefault(String str) {
        return split2IntegerList(str, SPLIT_COMMA);
    }

    public static List<Integer> split2IntegerList(String str, String separatorChars) {
        return split2List(str, separatorChars, Integer::valueOf);
    }

    public static List<String> split2StringListDefault(String str) {
        return split2StringList(str, SPLIT_COMMA);
    }

    public static List<String> split2StringList(String str, String separatorChars) {
        return split2List(str, separatorChars, String::valueOf);
    }

    public static <T> List<T> split2List(String str, String separatorChars, Function<String, T> mapper) {
        if (StringUtils.isBlank(str)){
            return Collections.emptyList();
        }
        String[] split = StringUtils.split(str, separatorChars);
        return Arrays.stream(split)
                .filter(StringUtils::isNotBlank)
                .map(mapper)
                .collect(Collectors.toList());
    }

}
