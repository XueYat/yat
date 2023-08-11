package top.yat.satoken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import top.yat.satoken.entity.User;


@Component
public interface AuthMapper extends BaseMapper<User> {

}
