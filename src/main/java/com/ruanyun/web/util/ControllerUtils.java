package com.ruanyun.web.util;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.TStockPlan;
import com.ruanyun.web.model.payeasy.OrderParmentResultReturnEntity;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.model.web.SearchPriceContentModel;
import com.ruanyun.web.service.sys.DictionaryService;
import com.ruanyun.web.service.web.WebInterface;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ControllerUtils {

    public void getOrderPaymentResult(HttpServletResponse response,
                                      OrderParmentResultReturnEntity orderParmentResultReturnEntity,
                                      WebInterface webService) {
        int result = webService.getOrderPaymentResult(orderParmentResultReturnEntity);
        String message = "success";
        if (result == 0) {
            message = "error";
        }
        //TODO 需要返回message
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List toWebStockListJson(HttpServletResponse response,
                                   SearchPriceContentModel stockModel,
                                   String Page_size,
                                   String Page_no,
                                   DictionaryService dictionaryService,
                                   WebInterface webService) {
        String max = stockModel.getMax_option_price();
        String min = stockModel.getMin_option_price();

        if (StringUtils.isNotEmpty(max) || StringUtils.isNotEmpty(min)) {
            TDictionary dictionary = dictionaryService.getDictionary("MANAGE_FEE", true);
            BigDecimal chuShu = new BigDecimal(dictionary.getItemCode()).add(BigDecimal.ONE);

            if (StringUtils.isNotEmpty(max)) {
                BigDecimal bigDecimalMax = new BigDecimal(max).divide(chuShu, 3, BigDecimal.ROUND_HALF_UP);
                stockModel.setMax_option_price(bigDecimalMax.toString());
            }
            if (StringUtils.isNotEmpty(min)) {
                BigDecimal bigDecimalMin = new BigDecimal(min).divide(chuShu, 3, BigDecimal.ROUND_HALF_UP);
                stockModel.setMin_option_price(bigDecimalMin.toString());
            }
        }
        List stockList = webService.queryStockListByApi(
                stockModel.getSymbols(),
                stockModel.getCycle(),
                stockModel.getMax_option_price(),
                stockModel.getMin_option_price(),
                "15",
                "1");
        return stockList;
    }

    public void doWebStockExercise(Model model, TStockPlan stockPlan, WebInterface webService, BaseController controller) {
        try {
            webService.exercise(stockPlan);
            controller.addModel(model, "msg", "行权申请成功");
        } catch (Exception e) {
            controller.logger.error(e);
            controller.addModel(model, "msg", "行权申请失败,原因:" + e.getMessage());
        }
    }

    public void doWebStockWithdraw(Model model, HttpSession session, BigDecimal money, WebInterface webService, BaseController controller) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        try {
            webService.withdraw(currentUser.getUserId(), money);
            controller.addModel(model, "msg", "提现申请成功，请等待人工审核");
        } catch (Exception e) {
            controller.logger.error(e);
            controller.addModel(model, "msg", e.getMessage());
        }
    }

    public void doWebStockDeposit(Model model, HttpSession session, BigDecimal money, String payType, WebInterface webService, BaseController controller) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        try {
            webService.deposit(currentUser.getUserId(), money, payType);
            controller.addModel(model, "msg", "充值成功");
        } catch (Exception e) {
            controller.logger.error(e);
            controller.addModel(model, "msg", e.getMessage());
        }
    }
}
