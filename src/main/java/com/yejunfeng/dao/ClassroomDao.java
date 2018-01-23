package com.yejunfeng.dao;

import com.yejunfeng.PO.ClassroomPO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClassroomDao {
    /**
     * 添加教室
     * @param classroomPO 教室实例
     */
    public void saveClassroom(ClassroomPO classroomPO);

    /**
     * 删除课室
     * @param roomId 课室id
     */
    public void deleteClassroom(String roomId);

    /**
     * 更新课室
     * @param classroomPO 课室实例
     */
    public void updateClassroom(ClassroomPO classroomPO);

    /**
     * 查找单个教室
     * @param roomId 教室id
     * @return 教室实例
     */
    public ClassroomPO searchById(String roomId);

    /**
     * 查找所有课室
     * @return 课室集合
     */
    public List<ClassroomPO> searchAll();
}
