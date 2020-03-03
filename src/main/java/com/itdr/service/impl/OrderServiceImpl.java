package com.itdr.service.impl;

import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.mapper.*;
import com.itdr.pojo.*;
import com.itdr.pojo.vo.GwcVo;
import com.itdr.pojo.vo.OrderItemVO;
import com.itdr.pojo.vo.OrderVo;
import com.itdr.pojo.vo.ShippingVo;
import com.itdr.service.GwcService;
import com.itdr.service.OrderService;
import com.itdr.utils.BigDecimalUtils;
import com.itdr.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GwcMapper gwcMapper;

    @Autowired
    GwcService gwcService;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    ShippingMapper shippingMapper;

    // 随机制作一个订单号
    private Long getOrderOn(){
        long round = Math.round(Math.random() * 100);
        long l = System.currentTimeMillis()+round;
        return l;
    }

    @Override
    public ServerResponse create(Integer shippingId, Users user) {
        // 判断参数是否合法
        if (shippingId == null || shippingId < 0){
            return ServerResponse.defeatedRS(ConstCode.UNLAWFULNESS_ALL);
        }

        // 判断购物车里有没有被选中的数据
        ServerResponse over = gwcService.over(user);
        if (!over.isSuccess()){
            return ServerResponse.defeatedRS(ConstCode.GwcEnum.NO_PRODUCT.getDesc());
        }

        // 获取购物车里所以商品
        List<Gwc> gwcs = gwcMapper.selectByUserId(user.getId());

        // 封装一下
        GwcVo gwcVo = ((GwcServiceImpl) gwcService).getGwcVo(gwcs);

        // 获取订单号
        Long orderOn = getOrderOn();

        // 创建一个订单
        Order order = new Order();
        order.setUid(user.getId());
        order.setOrderNo(orderOn);
        order.setShippingId(shippingId);
        order.setPayment(gwcVo.getCarTotalPrice());
        order.setPostage(0);
        order.setStatus(10);
        int insert = orderMapper.insert(order);
        if (insert <= 0){
            return ServerResponse.defeatedRS(ConstCode.GwcEnum.FAIL_ORDER.getDesc());
        }

        List<OrderItemVO> list = new ArrayList<>();
        for (Gwc gwc : gwcs){
            OrderItem orderItem = new OrderItem();
            orderItem.setUid(user.getId());
            orderItem.setOrderNo(orderOn);

            // 获取购物车里选中的商品数据
            if (gwc.getChecked() == 1){
                Product product = productMapper.selectById(gwc.getPid());
                if (product.getStatus() == 1 && product.getCount() >= gwc.getQuantiy()){
                    orderItem.setPid(gwc.getPid());
                    orderItem.setPname(product.getPname());
                    orderItem.setPimg(product.getImg());
                    orderItem.setCurrentUnitPrice(product.getPrice());
                    orderItem.setQuantity(gwc.getQuantiy());
                    orderItem.setTotalPrice(BigDecimalUtils.mul(product.getPrice().doubleValue(),gwc.getQuantiy().doubleValue()));
                }
            }
            int insert1 = orderItemMapper.insert(orderItem);
            if (insert1 <= 0){
                return ServerResponse.defeatedRS(ConstCode.GwcEnum.FAIL_ORDERITER.getDesc());
            }
            OrderItemVO orderItemVO =  ObjectUtils.orderItemVO(orderItem);
            list.add(orderItemVO);
        }

        // 清空购物车
        int i = gwcMapper.deleteByuserid(user.getId());

        Shipping si = shippingMapper.selectByPrimaryKey(shippingId);
        if (si == null){
            return ServerResponse.defeatedRS(ConstCode.ShippingEnum.NULL_SHIPPING.getDesc());
        }
        ShippingVo sv = ObjectUtils.shippingVo(si);
        OrderVo ov = orderVo(order,shippingId,list,sv);
        return ServerResponse.successRS(ov);
    }



    /**
     * 封装订单数据
     * @param o
     * @param shippingId
     * @param list
     * @param shippingVo
     * @return
     */
    private static OrderVo orderVo(Order o, Integer shippingId, List<OrderItemVO> list,ShippingVo shippingVo){
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderNo(o.getOrderNo());
        orderVo.setShippingId(shippingId);
        orderVo.setPayment(o.getPayment());
        orderVo.setPaymentType(o.getPaymentType());
        orderVo.setPostage(o.getPostage());
        orderVo.setStatus(o.getStatus());
        orderVo.setOrderItemVOList(list);
        orderVo.setShippingVo(shippingVo);
        return orderVo;
    }
}
