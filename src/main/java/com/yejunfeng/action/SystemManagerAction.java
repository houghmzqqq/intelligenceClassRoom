package com.yejunfeng.action;

import com.yejunfeng.PO.DeviceTypePO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.exception.DeviceFindException;
import com.yejunfeng.server.AttendanceServerServlet;
import com.yejunfeng.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="sysMana")
public class SystemManagerAction {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value="toDevManaView")
    public String toDevManaView(@RequestParam(defaultValue = "1") int thisPage
            , @RequestParam(defaultValue = "0") String devTypeId, Model model) {
        try {
            List<DeviceTypePO> deviceTypePOS = deviceService.getAllDevType();
            DividePageVO dividePage = deviceService.getDividePageView(thisPage,10,devTypeId);

            model.addAttribute("deviceTypePOS",deviceTypePOS);
            model.addAttribute("dividePage",dividePage);
            model.addAttribute("devicePOS",dividePage.getDevicePOS());
            model.addAttribute("devTypeId",devTypeId);
        } catch (DeviceFindException e) {
            e.printStackTrace();
            model.addAttribute("error","未发现设备类型！");
        }

        return "sysMana/device-manager";
    }

    @RequestMapping(value="devControlView")
    public String devControlView(Model model){
        deviceService.getAllDev();
        model.addAttribute("devicePOS",deviceService.getAllDev());

        return "sysMana/dev-control";
    }

    @RequestMapping(value="doOpen")
    public String doOpen(@RequestParam String devId,Model model) throws InterruptedException {
        deviceService.doOpen(devId);

//        Thread.sleep(2000);
        model.addAttribute("devicePOS",deviceService.getAllDev());
        return "redirect:/sysMana/devControlView";
    }

    @RequestMapping(value="doClose")
    public String doClose(@RequestParam String devId,Model model) throws InterruptedException {
        deviceService.doClose(devId);

//        Thread.sleep(2000);
        model.addAttribute("devicePOS",deviceService.getAllDev());
        return "redirect:/sysMana/devControlView";
    }

    @RequestMapping(value="closeServer")
    public void closeServer(@RequestParam(value="controlId") String controlId, HttpServletResponse res){
        try {
            AttendanceServerServlet.getClientList().get(controlId).getOutputStream().close();
            System.out.println("The socket of controlId--" + controlId + " is closed.");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                res.getOutputStream().write("No find controlId！".getBytes());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
