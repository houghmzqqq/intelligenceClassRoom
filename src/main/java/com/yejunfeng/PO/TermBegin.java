package com.yejunfeng.PO;

import org.aspectj.lang.annotation.DeclareError;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TermBegin {
//    private String range;
//    private String term;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @DeclareError(value = "日期格式为: yyyy-mm-dd")
    private Date beginTime;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
}
