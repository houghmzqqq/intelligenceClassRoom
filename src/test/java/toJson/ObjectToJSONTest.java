package toJson;

import com.yejunfeng.util.ObjectToJSON;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class ObjectToJSONTest {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory session;

//    private Blob photo = null;

    @Test
    public void test() throws IOException, SQLException {
        InputStream input = new FileInputStream(new File("D:\\head.jpg"));

        Blob photo = Hibernate.getLobCreator(session.openSession()).createBlob(input,input.available());

        ObjectToJSON otj = new ObjectToJSON();
//        String str = otj.photoToString(photo);
//        byte[] datas = str.getBytes();
        FileOutputStream out = new FileOutputStream("D:\\head02.jpg");
//        out.write(datas);

        out.close();
    }


}
