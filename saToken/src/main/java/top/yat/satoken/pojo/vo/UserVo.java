package top.yat.satoken.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yat.satoken.pojo.entity.Menu;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private Integer userId;

    private String nickname;

    private String username;

    private String password;

    private Integer age;

    private String mobile;

    private String email;

    private String status;

    private List<String> roles;

    private List<String> menus;

}
