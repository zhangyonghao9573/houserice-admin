package com.luobida.houserice.admin.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangyonghao
 * 白名单配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "house-rice.filters.token")
public class BlanklistProperties {
    private List<String> blanklist;
}
