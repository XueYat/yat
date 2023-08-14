package top.yat.satoken.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import top.yat.satoken.pojo.entity.Menu;

import java.util.List;

@Component
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectMenuPermsByUserId(Integer userId);

}
