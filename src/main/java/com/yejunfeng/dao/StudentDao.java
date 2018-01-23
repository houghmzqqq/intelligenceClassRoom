package com.yejunfeng.dao;

import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.VO.DividePageVO;

import java.util.List;

public interface StudentDao {
    /**
     * 增加学生
     * @param studentPO
     */
    public void addStudent(StudentPO studentPO);

    /**
     * 删除学生
     * @param studentId 学生id
     */
    public void deleteStudent(String studentId);

    /**
     * 更新学生信息
     * @param studentPO
     */
    public void updateStudent(StudentPO studentPO);

    /**
     * 通过学生id查找学生
     * @param studentId 学生id
     * @return 学生实例
     */
    public StudentPO searchById(String studentId);

    /**
     * 通过班级id查找学生
     * @param classId 班级id
     * @return 学生实例的list集合
     */
    public List<StudentPO> searchByClassId(String classId);

    /**
     * 获取指定范围内的学生信息
     * @param thisPage 当前页
     * @param rowOfEachPage 每页的行数
     * @return 分页信息
     */
    public DividePageVO searchByDivide(int thisPage, int rowOfEachPage);

    /**
     * 根据班级编号分页查询学生列表
     * @param thisPage 当前页
     * @param rowOfEachPage 每页的行数
     * @param classId 班级id
     * @return 分页信息
     */
    public DividePageVO searchByDivide(int thisPage, int rowOfEachPage,String classId);

    /**
     * 通过姓名查找学生
     * @param stuName 学生姓名
     * @return 学生集合
     */
    public List<StudentPO> searchByStuName(String stuName);
}
