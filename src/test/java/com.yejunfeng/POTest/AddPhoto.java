package com.yejunfeng.POTest;

import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.dao.StudentDao;
import com.yejunfeng.service.StudentService;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class AddPhoto {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentService studentService;

    @Test
    @Transactional
    @Rollback(false)
    public void addpicture() throws FileNotFoundException {
        File file = new File("D:\\head.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        Blob photo = Hibernate.getLobCreator(hibernateTemplate.getSessionFactory().openSession()).createBlob(fileInputStream,file.length());
        StudentPO studentPO = studentDao.searchById("wl132_09");
        studentPO.setPhoto(photo);
        studentDao.updateStudent(studentPO);
    }

    @Test
    @Transactional
    public void getPicture() throws Exception {
        byte[] picture = studentService.getStudentImg("wl132_09");
        FileOutputStream out = new FileOutputStream("D:\\photo.jpg");
        out.write(picture);
        out.close();
    }
}
