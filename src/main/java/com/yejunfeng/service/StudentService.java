package com.yejunfeng.service;

import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.VO.StudentVO;
import com.yejunfeng.exception.NoClassesFindException;
import com.yejunfeng.util.StudentInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    /**
     * 查找一个班级的学生信息
     * @param classId 班级id
     * @return 学生实例的list集合
     */
    public List<StudentInfo> getStudentInfo(String classId);

    /**
     * 导入一个班的学生信息
     * @param studentVO 学生info实例
     */
    public void addStudentList(StudentVO studentVO, MultipartFile file);

    /**
     * 获取学生信息的分页视图
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @return 分页信息实例
     */
    public DividePageVO getDividePageView(int thisPage,int rowOfEachPage,String classId);

    /**
     * 获取单个学生
     * @param studentId 学生id
     * @return 学生信息实例
     */
    public StudentVO getStudentById(String studentId);

    /**
     * 删除学生
     * @param studentId 学生id
     */
    public void deleteStudent(String studentId);

    /**
     * 获取一个班级的学生
     * @param classId 班级id
     */
    public String getStudentByClassId(String classId) throws NoClassesFindException;

    /**
     * 获取学生头像
     * @param stuId 学生id
     * @return 头像数据的byte[]数组
     */
    public byte[] getStudentImg(String stuId) throws Exception;
}
