package com.yejunfeng.PO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="classroom")
public class ClassroomPO {
    @Id @Column(name="room_id")
    private String roomId;
    @Column(name="room_name")
    private String roomName;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "classroomPO")
//    @OrderBy("room_id")
//    private Set<AttendanceMachinePO> attendanceMachinePOS;

    public ClassroomPO() {
    }

    public ClassroomPO(String roomId) {
        this.roomId = roomId;
    }

    public ClassroomPO(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "ClassroomPO{" +
                "roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}
