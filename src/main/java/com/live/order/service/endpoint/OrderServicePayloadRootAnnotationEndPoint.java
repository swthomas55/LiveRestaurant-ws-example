package com.live.order.service.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import com.live.order.domain.CancelOrderRequest;
import com.live.order.domain.CancelOrderResponse;
import com.live.order.domain.ObjectFactory;
import com.live.order.domain.PlaceOrderRequest;
import com.live.order.domain.PlaceOrderResponse;
import com.live.order.service.OrderService;

/**
* <pre>
* This is the endpoint for the {@link OrderService}.
* Requests are simply delegated to the {@link OrderService} for processing.
* Two operations are mapped, using annotation, as specified in the link,
* <a href="http://static.springsource.org/spring-ws/sites/1.5/reference/html/server.html#server-at-endpoint"
* >http://static.springsource.org/spring-ws/sites/1.5/reference/html/server.html#server-at-endpoint</a
* ><ul>
*     <li>placeOrderRequest</li>
*     <li>cancelOrderRequest</li>
* </ul>
* </pre>
*
*/
@SuppressWarnings("restriction")
@Endpoint
public class OrderServicePayloadRootAnnotationEndPoint {

    private final OrderService orderService;
    private final ObjectFactory JAXB_OBJECT_FACTORY = new ObjectFactory();

    public OrderServicePayloadRootAnnotationEndPoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @PayloadRoot(localPart = "placeOrderRequest", namespace = "http://www.liverestaurant.com/OrderService/schema")
    public JAXBElement<PlaceOrderResponse> getOrder(
            PlaceOrderRequest placeOrderRequest) {
        PlaceOrderResponse response = JAXB_OBJECT_FACTORY
                .createPlaceOrderResponse();
        response.setRefNumber(orderService.placeOrder(placeOrderRequest
                .getOrder()));

        return new JAXBElement<PlaceOrderResponse>(new QName(
                "http://www.liverestaurant.com/OrderService/schema",
                "placeOrderResponse"), PlaceOrderResponse.class, response);
    }

    @PayloadRoot(localPart = "cancelOrderRequest", namespace = "http://www.liverestaurant.com/OrderService/schema")
    public JAXBElement<CancelOrderResponse> cancelOrder(
            CancelOrderRequest cancelOrderRequest) {
        CancelOrderResponse response = JAXB_OBJECT_FACTORY
                .createCancelOrderResponse();
        response.setCancelled(orderService.cancelOrder(cancelOrderRequest
                .getRefNumber()));
        return new JAXBElement<CancelOrderResponse>(new QName(
                "http://www.liverestaurant.com/OrderService/schema",
                "cancelOrderResponse"), CancelOrderResponse.class, response);
    }

}