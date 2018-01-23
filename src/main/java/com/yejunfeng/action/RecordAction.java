package com.yejunfeng.action;

import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.service.AttendanceRecordService;
import com.yejunfeng.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="record")
public class RecordAction {

    @Autowired
    private AttendanceRecordService ars;
    @Autowired
    private ClassesService classesService;

    /**
     * 跳转到学生考勤记录页面
     * @param thisPage 当前页
     * @param stuName 每一页的行数
     * @return 学生记录页面
     */
    @RequestMapping(value="toStuRecodeView")
    public String toStuRecordView(@RequestParam(defaultValue="1" ) int thisPage
            , @RequestParam(defaultValue="no") String stuName, Model model){
        DividePageVO dividePage = ars.getStuRecordDivide(thisPage,5,stuName);

//        System.out.println(stuName);
//        System.out.println(dividePage.getStudentRecordVOS());

        model.addAttribute("dividePage",dividePage);
        model.addAttribute("thisPage",thisPage);
        model.addAttribute("studentRecordVOS",dividePage.getStudentRecordVOS());
        model.addAttribute("stuName",stuName);

        return "attenRecord/atten-record-stu-view";
    }

    /**
     * 通过学生姓名查找学生考勤记录
     * @param stuName 学生姓名
     * @return 学生记录页面
     */
    @RequestMapping(value="findByName")
    public String findByStuName(@RequestParam String stuName,Model model){
        DividePageVO dividePage = ars.getStuRecordDivide(1,5,stuName);

        model.addAttribute("dividePage",dividePage);
        model.addAttribute("thisPage",1);
        model.addAttribute("studentRecordVOS",dividePage.getStudentRecordVOS());
        model.addAttribute("stuName",stuName);

        return "attenRecord/atten-record-stu-view";
    }

    /**
     * 跳转到班级考勤记录页面
     * @param thisPage 当前页
     * @return 班级考勤页面
     */
    @RequestMapping(value="toClassView")
    public String toClassView(@RequestParam int thisPage, Model model){
        DividePageVO dividePage = classesService.getClassesDivide(thisPage,5);



        model.addAttribute("dividePage",dividePage);
        model.addAttribute("classesPOS",dividePage.getClassesPOS());
        model.addAttribute("thisPage",thisPage);

        return "attenRecord/atten-record-class-view";
    }
}
