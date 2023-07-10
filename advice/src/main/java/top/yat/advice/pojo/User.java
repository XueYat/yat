package top.yat.advice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XueYat
 * @date 2023/07/07
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer userId;

    private String userName;

    private String password;
}
