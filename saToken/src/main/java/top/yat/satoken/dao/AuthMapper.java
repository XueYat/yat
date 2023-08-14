package top.yat.satoken.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import top.yat.satoken.pojo.entity.User;


@Component
public interface AuthMapper extends BaseMapper<User> {

}
