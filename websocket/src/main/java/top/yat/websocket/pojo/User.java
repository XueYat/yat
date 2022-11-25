package top.yat.websocket.pojo;

import lombok.Data;

@Data
public class User {
    private Integer userId;

    private String userName;

    private String passWord;

    private char sex;

    private String realName;

    private String agentCode;

    private String rawStaffId;
}
