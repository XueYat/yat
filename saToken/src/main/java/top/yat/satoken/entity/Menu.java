package top.yat.satoken.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("t_menu")
public class Menu {

    private Integer menuId;

    private String menuName;

    private Integer parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private String queryParam;

    private Integer isFrame;

    private Integer isCache;

    private String menuType;

    private String visible;

    private String status;

    private String perms;

    private String icon;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

}
