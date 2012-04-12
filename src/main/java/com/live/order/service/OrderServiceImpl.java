package com.live.order.service;

import java.util.Calendar;
import java.util.logging.Logger;

import org.apache.commons.lang.ObjectUtils;

import com.live.order.domain.Order;

/**
* <pre>
* Service implementation for {@link OrderService}
* </pre>
*
* @see OrderService
*
*/
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    public OrderServiceImpl() {
    }

    public String placeOrder(Order order) {
        logger.info("Order has been placed. Order Info is : " + ObjectUtils.toString(order));
        return getRandomOrderRefNo();
    }

    public boolean cancelOrder(String orderRef) {
        logger.info("Order has been placed. Order Reference : " + orderRef);
        return true;
    }

    private String getRandomOrderRefNo() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return "Ref-" + year + "-" + month + "-" + day + "-" + Math.random();

    }
}