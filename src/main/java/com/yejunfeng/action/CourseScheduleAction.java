package com.yejunfeng.action;

import com.yejunfeng.PO.ClassroomPO;
import com.yejunfeng.PO.TermBegin;
import com.yejunfeng.VO.CourseScheduleVO;
import com.yejunfeng.service.CourseScheduleService;
import com.yejunfeng.util.JSONAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/couSch")
public class CourseScheduleAction {

    @Autowired
    private CourseScheduleService css;

    private List<ClassroomPO> classroomPOS;

    /**
     * 跳转到下发课程时间安排的页面
     * @return
     */
    @RequestMapping(value="toView")
    public String toView(Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date now = new Date();
        String year = sdf.format(now);

        classroomPOS = css.findAllClassroom();
        model.addAttribute("classroomPOS",classroomPOS);
        model.addAttribute("year",year);
        return "attend/atten-send-sche";
    }

    /**
     * 查找某个教室、某一学期的课程时间安排
     * @param roomId 教室id
     * @param range 学年
     * @param term 学期
     * @return 页面名称
     */
    @RequestMapping(value="toFind")
    public String toView(@RequestParam(value="roomId") String roomId,
            @RequestParam String range,@RequestParam int term,Model model){
        List<CourseScheduleVO> courseScheduleVOS = css.findByRoomIdForTerm(roomId,range,term);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date now = new Date();
        String year = sdf.format(now);

        classroomPOS = css.findAllClassroom();
        model.addAttribute("roomId",roomId);
        model.addAttribute("range",range);
        model.addAttribute("term",term);
        model.addAttribute("classroomPOS",classroomPOS);
        model.addAttribute("year",year);
        model.addAttribute("courseScheduleVOS",courseScheduleVOS);
        return "attend/atten-send-sche";
    }

    /**
     * 下发课程时间安排到考勤机
     * @param roomId 教室id
     * @param range 学年
     * @param term 学期
     * @return 课程时间安排表页面
     */
    @RequestMapping(value="sendToAtten")
    public String sendToAtten(@RequestParam String roomId,@RequestParam String range,@RequestParam int term){
        css.sendMsgToAtten(roomId,range,term);
        return "redirect:/couSch/toView";
    }

    /**
     * 设置开学周
     * @return 设置页面
     */
    @RequestMapping(value="toSetTermView")
    public String toSetTermBegin(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//        Date now = new Date();
//        String year = sdf.format(now);
//
//        model.addAttribute("year",year);

        return "attend/atten-set-termBegins";
    }

    @RequestMapping(value="setTermBegin")
    public String setTermBeginTime(@Valid TermBegin termBegin, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println("error");
            return "attend/atten-set-termBegins";
        }
        return "attend/atten-set-termBegins";
    }

    /*@RequestMapping(value="toAttenMachView")
    public String toAttenMach(){

        return null;
    }*/

    @RequestMapping(value="toAddView")
    public String toAddView(){
        return "/couSch/cour-sche-add";
    }

    @RequestMapping(value="addCouSchs")
    public String addCouSchs(@RequestParam String url, Model model, @RequestPart(value="couSch") MultipartFile file){
        try {
            if(url!=null && !url.equals("")) {
                JSONAnalysis jsonAnalysis = new JSONAnalysis(url);
                css.saveCourseSchedule(jsonAnalysis.analysis(jsonAnalysis.getResponseMsg()));
                model.addAttribute("result", "success");
            }else if(file!=null) {
                css.readCouSchFromFile(file);
                model.addAttribute("result", "success");
            }else{
                model.addAttribute("result","请选择一个导入方式.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("result","导入失败，检查文件和url路径！");
        }finally {
            return "/couSch/cour-sche-add";
        }
    }
}
