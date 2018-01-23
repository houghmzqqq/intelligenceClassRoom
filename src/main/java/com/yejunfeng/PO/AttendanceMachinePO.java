package com.yejunfeng.PO;

import javax.persistence.*;

@Entity
@Table(name="attendance_machine")
public class AttendanceMachinePO {
    @Id @Column(name="atten_mach_id")
    private String attendanceMachineId;
    @Column(name="ip")
    private String IP;
    @Column(name="port")
    private String port;
    @Column(name="status")
    private String status;

    @ManyToOne(targetEntity = ClassroomPO.class)
    @JoinColumn(name="room_id")
    private ClassroomPO classroomPO;

    public AttendanceMachinePO() {
    }

    public AttendanceMachinePO(String attendanceMachineId, String IP, String port, ClassroomPO classroomPO) {
        this.attendanceMachineId = attendanceMachineId;
        this.IP = IP;
        this.port = port;
        this.classroomPO = classroomPO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttendanceMachineId() {
        return attendanceMachineId;
    }

    public void setAttendanceMachineId(String attendanceMachineId) {
        this.attendanceMachineId = attendanceMachineId;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public ClassroomPO getClassroomPO() {
        return classroomPO;
    }

    public void setClassroomPO(ClassroomPO classroomPO) {
        this.classroomPO = classroomPO;
    }

    @Override
    public String toString() {
        return "AttendanceMachinePO{" +
                "attendanceMachineId='" + attendanceMachineId + '\'' +
                ", IP='" + IP + '\'' +
                ", port=" + port +
                ", status='" + status + '\'' +
                ", classroomPO=" + classroomPO +
                '}';
    }
}
