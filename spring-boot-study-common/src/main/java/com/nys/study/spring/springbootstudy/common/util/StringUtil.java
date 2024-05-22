package com.nys.study.spring.springbootstudy.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 字符串工具
 * @date 2024/3/15 15:23
 */
public class StringUtil {

    public static final String SPLIT_COMMA = ",";

    public static List<Long> split2LongList(String str, String separatorChars) {
        if (StringUtils.isBlank(str)){
            return Collections.emptyList();
        }
        String[] split = StringUtils.split(str, separatorChars);
        return Arrays.stream(split)
                .filter(StringUtils::isNotBlank)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    public static List<Integer> split2IntegerList(String str, String separatorChars) {
        if (StringUtils.isBlank(str)){
            return Collections.emptyList();
        }
        String[] split = StringUtils.split(str, separatorChars);
        return Arrays.stream(split)
                .filter(StringUtils::isNotBlank)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public static List<String> split2StringList(String str, String separatorChars) {
        if (StringUtils.isBlank(str)){
            return Collections.emptyList();
        }
        String[] split = StringUtils.split(str, separatorChars);
        return Arrays.stream(split)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
    }

}
