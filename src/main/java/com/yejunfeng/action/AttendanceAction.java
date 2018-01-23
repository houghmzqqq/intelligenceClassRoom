package com.yejunfeng.action;

import com.yejunfeng.PO.*;
import com.yejunfeng.dao.ClassroomDao;
import com.yejunfeng.exception.AttenMachException;
import com.yejunfeng.exception.NoClassesFindException;
import com.yejunfeng.exception.RecordSaveException;
import com.yejunfeng.service.AttendanceRecordService;
import com.yejunfeng.service.AttendanceService;
import com.yejunfeng.service.CourseScheduleService;
import com.yejunfeng.service.StudentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value="attendance")
public class AttendanceAction {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private AttendanceRecordService attendanceRecordService;
    @Autowired
    private CourseScheduleService courseScheduleService;
    @Autowired
    private StudentService studentService;

    /**
     * 接收并响应考勤机注册的请求
     * @param data 考勤机注册信息
     * @return  json数据，包含action和result两个属性
     */
    @RequestMapping(value="register")
    @ResponseBody
    public String receiveRegister(@RequestParam String data){

        JSONObject jsonObject = JSONObject.fromObject(data);
        jsonObject.get("action");

        AttendanceMachinePO am = new AttendanceMachinePO();
        am.setAttendanceMachineId((String) jsonObject.get("attdSetId"));
        am.setIP((String) jsonObject.get("ip"));
        am.setPort((String) jsonObject.get("port"));
        am.setStatus("unusable");
        System.out.println(am);

        jsonObject = new JSONObject();
        jsonObject.accumulate("action","register");
        try {
            attendanceService.addAttendanceMachine(am);
            jsonObject.accumulate("result","ok");
            //响应的json
        } catch (AttenMachException e) {
            jsonObject.accumulate("result","failed");
        }

        return jsonObject.toString();
    }

    /**
     * 跳到考勤机列表页面
     * @param model
     * @return 考勤机列表页面的名称
     */
    @RequestMapping(value="toAttenView")
    public String toAttenView(Model model){
        List<AttendanceMachinePO> amPOS = attendanceService.findAll();

        model.addAttribute("amPOS",amPOS);
        return "attend/atten-mach-list-view";
    }

    /**
     * 跳到考勤机信息设置页面
     * @param attenId 考勤机id
     * @param model
     * @return 考勤机信息设置页面名称
     */
    @RequestMapping(value="toUpdate")
    public String toUpdateAtten(@RequestParam String attenId, Model model){
        AttendanceMachinePO amPO = attendanceService.findById(attenId);
        List<ClassroomPO> classroomPOS = courseScheduleService.findAllClassroom();

        model.addAttribute("amPO",amPO);
        model.addAttribute("classroomPOS",classroomPOS);
        return "attend/atten-mach-view";
    }

    /**
     * 删除考勤机
     * @param attenId 考勤机id
     * @return 考勤机列表页面名称
     */
    @RequestMapping(value="toDelete")
    public String toDeteleAtten(@RequestParam String attenId){
        attendanceService.deleteAttenMach(attenId);
        return "redirect:/attendance/toAttenView";
    }

    /**
     * 更新考勤机信息
     * @param amPO 考勤机实例
     * @param roomId 教室id
     * @return 考勤机列表页面名称
     */
    @RequestMapping(value="update")
    public String toUpdate(AttendanceMachinePO amPO,@RequestParam String roomId){
        amPO.setClassroomPO(new ClassroomPO());
        amPO.getClassroomPO().setRoomId(roomId);
        attendanceService.updateAttenMach(amPO);

        if(amPO.getStatus().equals("usable")){
            attendanceService.sendMyUrlToAttend(amPO);
        }

        return "redirect:/attendance/toAttenView";
    }

    /**
     * 接收考勤机的考勤数据
     * @param data 考勤数据
     */
    @RequestMapping(value="submit")
    public void getAttenData(@RequestParam String data){

        JSONObject jsonObject = JSONObject.fromObject(data);
        String action = (String) jsonObject.get("action");

        AttendanceRecordPO arPO = new AttendanceRecordPO();
        arPO.setCourseSchedulePO(new CourseSchedulePO((String) jsonObject.get("planId")));
        arPO.setStudentPO(new StudentPO((String) jsonObject.get("stuId")));
        arPO.setClassroomPO(new ClassroomPO((String) jsonObject.get("roomId")));
        arPO.setStatus(Integer.parseInt((String) jsonObject.get("status")));
        arPO.setTime(Integer.parseInt((String) jsonObject.get("time")));
        arPO.setDayOfWeek(Integer.parseInt((String) jsonObject.get("dayOfWeek")));
        arPO.setWeek(Integer.parseInt((String) jsonObject.get("week")));

        System.out.println("[学生考勤信息]：" + arPO);
        try {
            attendanceRecordService.addRecord(arPO);
        } catch (RecordSaveException e) {
            e.printStackTrace();
            System.out.println("[考勤信息录入失败]：" + "请检查学生、课程安排、课室信息！");
        }
    }

    /**
     * 接收考勤机获取学生信息的请求
     * @param data 请求消息
     * @return 返回json格式的学生信息
     */
    @RequestMapping(value="getStudents", produces="application/json;charset=utf-8")
    @ResponseBody
    public String getStudentInfo(@RequestParam String data){
        JSONObject jsonObject = JSONObject.fromObject(data);
        String action = (String) jsonObject.get("action");
        String squadId = (String) jsonObject.get("squadId");

        System.out.println(action + " " + squadId);
        jsonObject = new JSONObject();
        if(action.equals("getStudent")){
            try {
                String str = studentService.getStudentByClassId(squadId);
                jsonObject.accumulate("action",action);
                jsonObject.accumulate("result","ok");
                jsonObject.accumulate("squadId",squadId);
                jsonObject.accumulate("student",str);
            } catch (NoClassesFindException e) {
                jsonObject.accumulate("action",action);
                jsonObject.accumulate("result","failed");
            }
        }else {
            jsonObject.accumulate("action", action);
            jsonObject.accumulate("result", "failed");
        }

        return jsonObject.toString();
    }

    /**
     * 接收考勤机获取学生头像的请求
     * @param data 请求消息
     * @return 图片
     */
    @RequestMapping(value="getImage")
    public String getImage(@RequestParam String data, HttpServletResponse resp){

        JSONObject jsonObject = JSONObject.fromObject(data);
        String action = (String) jsonObject.get("action");
        String stuId = (String) jsonObject.get("id");
        System.out.println(action);
        String json = "";
        if (action.equals("getImage")) {
            try {
                byte[] photo = studentService.getStudentImg(stuId);
                resp.setContentLength( photo.length );
//                jsonObject = new JSONObject();
//                jsonObject.accumulate("action",action);
//                jsonObject.accumulate("length",String.valueOf(photo.length));
//                json = jsonObject.toString();
                OutputStream out = resp.getOutputStream();
//                out.write( json.getBytes("UTF-8") );

                out.write( photo );
                out.flush();

                System.out.println("action: " + action +"   length"+ photo.length   );

            } catch (Exception e) {
                try {
//                    jsonObject.accumulate("length","0");
//                    json = jsonObject.toString();
                    OutputStream out = resp.getOutputStream();
                    out.write("0".getBytes());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return null;
    }

}
