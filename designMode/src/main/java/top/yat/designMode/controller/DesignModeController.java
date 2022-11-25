package top.yat.designMode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.yat.designMode.request.TransferFeeRequest;
import top.yat.designMode.service.LogisticsService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class DesignModeController {

    @Autowired
    private List<LogisticsService> logisticsServices;

    /**
     * 策略模式
     * @param transferFeeRequest
     * @return
     */
    @PostMapping("/calculate")
    private BigDecimal calculateFee(@RequestBody TransferFeeRequest transferFeeRequest) {
        LogisticsService logisticsService = this.logisticsServices.stream().filter(l -> l.isCurrentLogistics(transferFeeRequest.getType()))
                .findFirst().orElse(null);
        if (logisticsService != null) {
            return logisticsService.calculateFee(transferFeeRequest);
        }
        return null;
    }
}
