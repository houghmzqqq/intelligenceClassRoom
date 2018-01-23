package com.yejunfeng.action;

import com.yejunfeng.PO.ClassesPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.VO.StudentVO;
import com.yejunfeng.rfid.Impl.Client;
import com.yejunfeng.service.ClassesService;
import com.yejunfeng.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
@RequestMapping(value="/stu")
public class StudentAction {

    @Autowired
    private ClassesService classesService;
    @Autowired
    private StudentService studentService;

    private List<ClassesPO> classesPOS;
    private DividePageVO dividePage;

    /**
     * 从文件中导入学生数据
     * @param classId 班级id
     * @param file 文件
     * @return 导入页面名称
     * @throws IOException
     */
    @RequestMapping(value="/addList")
    public String addStudentFromFile(@RequestParam(value="classId") String classId,@RequestPart(value="stulist")MultipartFile file) throws IOException {
        System.out.println(classId);

        String name = file.getName();
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        System.out.println("name -> " + name +
                " originalFilename -> " + originalFilename +
                " contentType -> " + contentType);


        InputStream in = file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));

        String strLine = reader.readLine();
        while(strLine != null){
            System.out.println(strLine);
            StudentVO si = new StudentVO();
            String[] fields = strLine.split(",");
            if(fields.length != 3){
                throw new RuntimeException("数据读取错误！");
            }
            //一行一行的读取学生数据，并存入数据库
            si.setStudentId(fields[0]);
            si.setStudentName(fields[1]);
            si.setGender(fields[2]);
            si.setClassId(classId);
            studentService.addStudentList(si,null);

            strLine = reader.readLine();
        }
        reader.close();
        in.close();

        return "redirect:/stu/toAddList";
    }

    /**
     * 跳到批量导入学生的页面
     */
    @RequestMapping(value="/toAddList")
    public String toStuAddList(Model model){
        classesPOS = classesService.findAddClasses();
        model.addAttribute("classesPOS",classesPOS);
        return "stu/student-addlist";
    }

    /**
     * 跳到学生信息页面
     * @return 视图名称
     */
    @RequestMapping(value="/toAdd")
    public String toStuAdd(Model model){
        classesPOS = classesService.findAddClasses();
        model.addAttribute("classesPOS",classesPOS);
        return "stu/student-add";
    }

    /**
     * 修改学生信息或添加单个学生
     * @param studentVO 学生信息
     * @param file 头像文件
     * @return 视图名称
     */
    @RequestMapping(value="/addOne")
    public String addOneByOne(StudentVO studentVO, @RequestPart(value="photo")MultipartFile file){
//        System.out.println(studentVO);

        studentService.addStudentList(studentVO,file);

        return "redirect:/stu/toView?thisPage=1&classId=0";
    }

    /**
     * 学生列表视图，分页的
     * @param thisPage 当前页
     * @param classId 班级id
     * @return 视图名称
     */
    @RequestMapping(value="/toView")
    public String toStuView(@RequestParam(value="thisPage",defaultValue = "1") Integer thisPage,
                            @RequestParam(value="classId",defaultValue = "0") String classId,Model model){
        dividePage = studentService.getDividePageView(thisPage,10,classId);
        model.addAttribute("dividePage",dividePage);
        model.addAttribute("classesPOS",classesService.findAddClasses());
        model.addAttribute("studentPOS",dividePage.getStudentPOS());
        model.addAttribute("classId",classId);
        return "stu/student-view";
    }

    /**
     * 跳到修改学生信息的页面
     * @param studentId 学生id
     * @return 视图名称
     */
    @RequestMapping(value="/toUpdate")
    public String toStuAdd(@RequestParam(value="studentId") String studentId,Model model) throws InterruptedException, ExecutionException {
        StudentVO studentVO = studentService.getStudentById(studentId);
        List<ClassesPO> classesPOS = classesService.findAddClasses();
        model.addAttribute("studentVO",studentVO);
        model.addAttribute("classesPOS",classesPOS);
        model.addAttribute("photo",studentVO.isHasPhoto());

//        ExecutorService exec = Executors.newCachedThreadPool();
//        Future<String> res = exec.submit(new Client());
//        Thread.sleep(1000);
//        exec.shutdown();

//        System.out.println(res);
//        model.addAttribute("rfid",res.get());

        return "stu/student-add";
    }

    /**
     * 删除学生
     * @param studentId 学生id
     * @return 学生列表视图[
     */
    @RequestMapping(value="/toDelete")
    public String toStuDelete(@RequestParam(value="studentId") String studentId){
        studentService.deleteStudent(studentId);
        return "redirect:/stu/toView?thisPage=1&classId=0";
    }

    /**
     * 从数据库获取学生头像，并显示在jsp页面中
     * @param stuId 学生id
     * @param request
     * @param response
     */
    @RequestMapping(value="/displayImg",method = RequestMethod.GET)
    public void displayImg(@RequestParam("stuId") String stuId, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        try {
            byte[] photoData = studentService.getStudentImg(stuId);
            response.setContentType("img/jpeg");
            response.setCharacterEncoding("utf-8");
            OutputStream out = response.getOutputStream();
//            FileOutputStream fout = new FileOutputStream("D:\\photo02.jpg");
//            fout.write(photoData);
            out.write(photoData);
//            InputStream in = new ByteArrayInputStream(photoData);
//
//            int len = -1;
//            byte[] buff = new byte[512];
//            while ((len=in.read(buff)) != -1){
//                out.write(buff,0,len);
//            }

//            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
