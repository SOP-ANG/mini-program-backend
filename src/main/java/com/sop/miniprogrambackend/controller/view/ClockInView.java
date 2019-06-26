package com.sop.miniprogrambackend.controller.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClockInView {
    private Integer id;
    private Integer userId;
    private Integer courseId;
    private String title;
    private String content;
    private boolean done;
    private String recordPath;
}
