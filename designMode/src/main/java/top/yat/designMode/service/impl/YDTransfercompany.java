package top.yat.designMode.service.impl;

import org.springframework.stereotype.Service;
import top.yat.designMode.request.TransferFeeRequest;
import top.yat.designMode.service.LogisticsService;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class YDTransfercompany implements LogisticsService {

    private BigDecimal pickFee = BigDecimal.TEN;

    private BigDecimal minDistance = BigDecimal.valueOf(40);


    @Override
    public BigDecimal calculateFee(TransferFeeRequest transferFeeRequest) {
        BigDecimal distance = minDistance.compareTo(transferFeeRequest.getDistance()) > 0 ?
                minDistance : transferFeeRequest.getDistance();
        BigDecimal fee = distance.multiply(transferFeeRequest.getUnitPrice()).add(pickFee);
        // do business
        return fee;
    }
    @Override
    public boolean isCurrentLogistics(Integer type) {
        return Objects.equals(type, 5);
    }
}
