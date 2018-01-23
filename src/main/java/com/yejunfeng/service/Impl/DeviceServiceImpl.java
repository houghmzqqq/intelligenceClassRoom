package com.yejunfeng.service.Impl;

import com.yejunfeng.PO.DevicePO;
import com.yejunfeng.PO.DeviceTypePO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.dao.DeviceDao;
import com.yejunfeng.dao.DeviceTypeDao;
import com.yejunfeng.exception.DeviceFindException;
import com.yejunfeng.server.AttendanceServerServlet;
import com.yejunfeng.server.SendToControl;
import com.yejunfeng.service.DeviceService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private DeviceTypeDao deviceTypeDao;
    @Autowired
    private DividePageVO dividePage;

    @Override
    public DividePageVO getDividePageView(int thisPage, int rowOfEachPage, String typeId) throws DeviceFindException {

        if(typeId.equals("0")){
            dividePage = deviceDao.searchByDivide(thisPage,rowOfEachPage);
        }else if(!typeId.equals("0")){
            dividePage = deviceDao.searchByDivide(thisPage,rowOfEachPage,typeId);
        }else {
            throw new DeviceFindException();
        }

        return dividePage;
    }

    @Override
    public List<DeviceTypePO> getAllDevType() {
        return deviceTypeDao.searchAll();
    }

    @Override
    public List<DevicePO> getAllDev() {
        return deviceDao.searchAllDev();
    }

    @Override
    public void addDevice(DevicePO devicePO) {
        deviceDao.addDevice(devicePO);
    }

    @Override
    public void doOpen(String devId) {
        DevicePO devicePO = deviceDao.searchById(devId);
        if(devicePO.getStatus()!=1){
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("op","control");
            jsonObject.accumulate("controlId", "c001");
            jsonObject.accumulate("devId",devicePO.getDeviceId());
            jsonObject.accumulate("status","1");

            new Thread(new SendToControl(jsonObject.toString())).start();

            //给该数据库中该设备的状态
            devicePO.setStatus(1);
            deviceDao.updateDevice(devicePO);
        }
    }

    @Override
    public void doClose(String devId) {
        DevicePO devicePO = deviceDao.searchById(devId);
        if(devicePO.getStatus()!=0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("op","control");
            jsonObject.accumulate("controlId","c001");
            jsonObject.accumulate("devId",devicePO.getDeviceId());
            jsonObject.accumulate("status","0");

            new Thread(new SendToControl(jsonObject.toString())).start();

            //更改数据库中该设备的状态
            devicePO.setStatus(0);
            deviceDao.updateDevice(devicePO);
        }
    }

    @Override
    public void updateDev(DevicePO devicePO) {
        deviceDao.updateDevice(devicePO);
    }

    @Override
    public DevicePO getByDevId(String devId) {
        return deviceDao.searchById(devId);
    }
}
