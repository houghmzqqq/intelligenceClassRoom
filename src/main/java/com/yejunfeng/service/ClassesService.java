package com.yejunfeng.service;

import com.yejunfeng.PO.ClassesPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.exception.ClassesExistException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClassesService {

    /**
     * 从dao层获取所有班级信息
     * @return 班级信息的list集合
     */
    public List<ClassesPO> findAddClasses();

    /**
     * 添加班级
     * @param classesPO 班级实例
     */
    public void addClasses(ClassesPO classesPO) throws ClassesExistException;

    /**
     * 分页查找班级
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @return 分页信息
     */
    public DividePageVO getClassesDivide(int thisPage, int rowOfEachPage);

    /**
     * 查找单个班级
     * @param classId 班级id
     * @return 班级实例
     */
    public ClassesPO getByClassId(String classId);

    /**
     * 分页查找班级
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页行数
     * @return 分页信息
     */
    public DividePageVO getForDivide(int thisPage, int rowOfEachPage);
}
