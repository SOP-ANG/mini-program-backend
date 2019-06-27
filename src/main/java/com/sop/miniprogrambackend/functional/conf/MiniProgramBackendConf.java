package com.sop.miniprogrambackend.functional.conf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置属性 bean
 */
@Component
@ConfigurationProperties(prefix = "com.sop.miniprogrambackend")
@Getter
@Setter
@ToString
public class MiniProgramBackendConf {
    private String jscode2session_uri;
    private String appid;
    private String secret;
    private Integer member_finished_clock_in_times;
    private String record_path;
    private String relative_record_path;
    private String img_path;
    private String relative_img_path;
    private String file_name;
}
