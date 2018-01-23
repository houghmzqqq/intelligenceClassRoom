package com.yejunfeng.service.Impl;

import com.yejunfeng.PO.ClassesPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.dao.ClassesDao;
import com.yejunfeng.exception.ClassesExistException;
import com.yejunfeng.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService{

    @Autowired
    private ClassesDao classesDao;

    public List<ClassesPO> findAddClasses() {
        return classesDao.searchAll();
    }

    @Override
    public void addClasses(ClassesPO classesPO) throws ClassesExistException {
        if(classesDao.searchById(classesPO.getClassId()) != null){
            throw new ClassesExistException("该班级编号已存在");
        }
        classesDao.addClasses(classesPO);
    }

    @Override
    public DividePageVO getClassesDivide(int thisPage, int rowOfEachPage) {
        return classesDao.searchForDivide(thisPage,rowOfEachPage);
    }

    @Override
    public ClassesPO getByClassId(String classId) {
        return classesDao.searchById(classId);
    }

    @Override
    public DividePageVO getForDivide(int thisPage, int rowOfEachPage) {
        return classesDao.searchForDivide(thisPage,rowOfEachPage);
    }
}
