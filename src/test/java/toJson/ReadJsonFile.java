package toJson;

import com.yejunfeng.service.CourseScheduleService;
import com.yejunfeng.util.JSONAnalysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class ReadJsonFile {

    @Autowired
    private CourseScheduleService css;

//    @Test
//    @Transactional
//    @Rollback(false)
    public void readFile() throws Exception {
        FileInputStream in = new FileInputStream(new File("C:\\Users\\handsome-boy\\Desktop\\course.json"));
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = -1;
        while((len=in.read(buff)) != -1){
            bout.write(buff,0,len);
        }
        String str = bout.toString("UTF-8");
        String tempStr = str.substring(0,str.length());
//        System.out.println(str);
        JSONAnalysis jsonAnalysis = new JSONAnalysis();
//        jsonAnalysis.analysis(str);
        css.saveCourseSchedule(jsonAnalysis.analysis(tempStr));
    }
}
