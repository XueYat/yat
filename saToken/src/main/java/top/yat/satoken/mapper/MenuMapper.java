package top.yat.satoken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import top.yat.satoken.entity.Menu;

import java.util.List;

@Component
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectMenuPermsByUserId(Integer userId);

}
