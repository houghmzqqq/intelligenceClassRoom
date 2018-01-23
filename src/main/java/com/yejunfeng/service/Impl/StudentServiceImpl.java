package com.yejunfeng.service.Impl;

import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.VO.StudentVO;
import com.yejunfeng.dao.ClassesDao;
import com.yejunfeng.dao.Impl.ClassesDaoImpl;
import com.yejunfeng.dao.StudentDao;
import com.yejunfeng.exception.NoClassesFindException;
import com.yejunfeng.service.StudentService;
import com.yejunfeng.util.ObjectToJSON;
import com.yejunfeng.util.StudentInfo;
import org.dozer.DozerBeanMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private DozerBeanMapper mapper;
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private ClassesDao classesDao;

    private DividePageVO dividePage;

    public List<StudentInfo> getStudentInfo(String classId) {
        List<StudentPO> studentPOS = studentDao.searchByClassId(classId);

        return null;
    }

    public void addStudentList(StudentVO si, MultipartFile file) {
        StudentPO studentPO = mapper.map(si,StudentPO.class);
        if(file!=null){
            try {
                Blob photo = Hibernate.getLobCreator(hibernateTemplate.getSessionFactory().openSession()).createBlob(file.getInputStream(),file.getSize()) ;
                studentPO.setPhoto(photo);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        studentDao.addStudent(studentPO);
    }

    public DividePageVO getDividePageView(int thisPage, int rowOfEachPage,String classId) {
        if(classId.equals("0")){
            dividePage = studentDao.searchByDivide(thisPage,rowOfEachPage);
        }else{
            dividePage = studentDao.searchByDivide(thisPage,rowOfEachPage,classId);
        }

        return dividePage;
    }

    public StudentVO getStudentById(String studentId) {
        StudentPO studentPO = studentDao.searchById(studentId);
        StudentVO studentVO = mapper.map(studentPO,StudentVO.class);
        if(studentPO.getPhoto()!=null){
            studentVO.setHasPhoto(true);
        }else{
            studentVO.setHasPhoto(false);
        }
        return studentVO;
    }

    public void deleteStudent(String studentId) {
        studentDao.deleteStudent(studentId);
    }

    public byte[] getStudentImg(String stuId) throws Exception {
        StudentPO studentPO = studentDao.searchById(stuId);
        if(studentPO.getPhoto()==null){
            throw new Exception();
        }
        InputStream in = studentPO.getPhoto().getBinaryStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[512];
        int len = -1;
        while((len=in.read(buff)) != -1){
            out.write(buff,0,len);
        }
        byte[] result = out.toByteArray();

        return result;
    }

    public String getStudentByClassId(String classId) throws NoClassesFindException {
        if(classesDao.searchById(classId) == null){
            throw new NoClassesFindException();
        }
        ObjectToJSON objectToJSON = new ObjectToJSON();


        String result = objectToJSON.studentToJson(studentDao.searchByClassId(classId));
        return result;
    }
}
