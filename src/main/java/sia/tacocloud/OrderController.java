package sia.tacocloud;

import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.TacoOrder;
import sia.tacocloud.data.OrderRepository;
import sia.tacocloud.props.OrderProps;
import sia.tacocloud.security.User;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
//@ConfigurationProperties(prefix="taco.orders") //Благодаря префиксу мы сможем в yml добавить taco:
                                                                                            // orders: pageSize: 10
                                                                                        //с 20 мы сможем снизить до 10, не изменяя код , а изменяя конфигурацию
public class OrderController {

    private OrderProps props;

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo, OrderProps props) {
        this.orderRepo = orderRepo;
        this.props = props;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute TacoOrder order) {
        if(order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
        if(order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if(order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if(order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if(order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }


        return "orderForm";
    }

    @PostMapping
    public String processOrder( @Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
        order.setUser(user);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUsers(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0,props.getPageSize());
        model.addAttribute("orders",
                orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }
}
