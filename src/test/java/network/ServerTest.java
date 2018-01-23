package network;

import com.yejunfeng.server.AttendanceServerServlet;

public class ServerTest {
    public static void main(String[] args) {
//        Thread thread = new Thread(new Server());
//        thread.start();
        AttendanceServerServlet.getClientList();
    }
}
