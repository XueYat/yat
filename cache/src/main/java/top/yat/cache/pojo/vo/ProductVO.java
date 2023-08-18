package top.yat.cache.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

    private Integer productId;

    private String name;

    private String supplier;

    private BigDecimal price;

    private Integer num;

    private String status;

}
