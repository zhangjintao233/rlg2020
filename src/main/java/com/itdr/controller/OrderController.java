package com.itdr.controller;

import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.pojo.Users;
import com.itdr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@ResponseBody
@RequestMapping("/portal/order/")
public class OrderController {

    @Autowired
    OrderService orderService;


    /**
     * 创建一个订单
     * @param shippingId
     * @param session
     * @return
     */
    @RequestMapping("create.do")
    public ServerResponse create(Integer shippingId, HttpSession session){
        Users user = (Users) session.getAttribute("user");
        if ( user == null){
            return ServerResponse.defeatedRS(ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return orderService.create(shippingId,user);
    }


    /**
     * 查询该用户所有未支付订单
     * @param session
     * @param pageNum                            pageNum    每页显示的条数
     * @param pageSize                           pageSize   每页的大小
     * @param orderBy                            orderBy    排序规则
     * @return
     */
    @RequestMapping("getallorder.do")
    public ServerResponse getAllOrder(HttpSession session,
                                      @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize,
                                      @RequestParam(value = "orderBy",required = false,defaultValue = "") String orderBy){
        Users user = (Users) session.getAttribute("user");
        if ( user == null){
            return ServerResponse.defeatedRS(ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return orderService.getAllOrder(user,pageNum,pageSize,orderBy);
    }


    /**
     * 获取订单商品信息
     * @param session
     * @return
     */
    @RequestMapping("getorderitem.do")
    public ServerResponse getOrderItem(HttpSession session){
        Users user = (Users) session.getAttribute("user");
        if ( user == null){
            return ServerResponse.defeatedRS(ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return orderService.getOrderItem(user);
    }


}
