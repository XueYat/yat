package top.yat.designMode.service;

import top.yat.designMode.request.TransferFeeRequest;

import java.math.BigDecimal;

public interface LogisticsService {

    boolean isCurrentLogistics(Integer type);

    BigDecimal calculateFee(TransferFeeRequest transferFeeRequest);
}
