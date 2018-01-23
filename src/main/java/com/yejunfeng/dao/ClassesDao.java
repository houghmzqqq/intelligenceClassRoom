package com.yejunfeng.dao;

import com.yejunfeng.PO.ClassesPO;
import com.yejunfeng.VO.DividePageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClassesDao {

    /**
     * 增加班级
     * @param classesPO
     */
    public void  addClasses(ClassesPO classesPO);

    /**
     * 删除班级
     * @param classId 班级编号
     */
    public void deleteClasses(String classId);

    /**
     * 更新班级信息
     * @param classesPO
     */
    public void updateClasses(ClassesPO classesPO);

    /**
     * 查找单个班级
     * @param classId 班级id
     * @return 班级实例
     */
    public ClassesPO searchById(String classId);

    /**
     * 查找所有班级
     * @return 班级的list集合
     */
    public List<ClassesPO> searchAll();

    /**
     * 分页查找班级
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @return 分页信息
     */
    public DividePageVO searchForDivide(int thisPage, int rowOfEachPage);

}
