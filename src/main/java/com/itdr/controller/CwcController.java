package com.itdr.controller;

import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.pojo.Users;
import com.itdr.service.GwcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@ResponseBody
@RequestMapping("/portal/gwc/")
public class CwcController {


    @Autowired
    GwcService gwcService;


    /**
     * 查看购物车里商品
     *
     * @param session
     * @return
     */
    @RequestMapping("selectgwc.do")
    public ServerResponse list(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc()
            );
        }
        return gwcService.list(user);
    }


    /**
     * 在购物车添加商品或更新商品
     *
     * @param productId
     * @param count
     * @param type
     * @param session
     * @return
     */
    @RequestMapping("add.do")
    public ServerResponse add(Integer productId,
                              @RequestParam(value = "count", required = false, defaultValue = "1") Integer count,
                              @RequestParam(value = "type", required = false, defaultValue = "1") Integer type,
                              HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc()
            );
        }
        return gwcService.add(productId, count, type, user);
    }


    /**
     * 更新购物车商品数量
     *
     * @param productId
     * @param count
     * @param type
     * @param session
     * @return
     */
    @RequestMapping("update.do")
    public ServerResponse update(Integer productId,
                                 @RequestParam(value = "count", required = false, defaultValue = "1") Integer count,
                                 @RequestParam(value = "type", required = false, defaultValue = "1") Integer type,
                                 HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc()
            );
        }
        return gwcService.update(productId, count, type, user);
    }


    /**
     * 删除购物车选中的一条数据
     *
     * @param productId
     * @param session
     * @return
     */
    @RequestMapping("delete.do")
    public ServerResponse delete(@RequestParam(value = "count", required = false, defaultValue = "0") Integer productId,
                                 HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc()
            );
        }
        return gwcService.delete(productId, user);
    }


    /**
     * 查看用户购物车里的商品数量
     *
     * @param session
     * @return
     */
    @RequestMapping("selectuserproduct.do")
    public ServerResponse selectUserProduct(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc()
            );
        }
        return gwcService.selectUserProduct(user);

    }


    /**
     * 选中/取消一个商品或所有商品
     * @param productId
     * @param type
     * @param session
     * @return
     */
    @RequestMapping("checked.do")
    public ServerResponse checked(Integer productId,
                                  @RequestParam(value = "type", required = false, defaultValue = "0")Integer type,
                                  HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc()
            );
        }
        return gwcService.checked(productId, type, user);
    }


    /**
     * 判断购物车去结算
     * @param shippingId
     * @param session
     * @return
     */
    @RequestMapping("over.do")
    public ServerResponse over(HttpSession session){
        Users user = (Users) session.getAttribute("user");
        if ( user == null){
            return ServerResponse.defeatedRS(ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return gwcService.over(user);
    }

}
