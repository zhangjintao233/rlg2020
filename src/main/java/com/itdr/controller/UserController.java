package com.itdr.controller;


import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.pojo.Users;
import com.itdr.pojo.vo.UsersVo;
import com.itdr.service.UserService;
import com.itdr.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/portal/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login.do")
    public ServerResponse<Users> login(String username, String password, HttpSession session){
        ServerResponse sr = userService.login(username,password);
        if (sr.isSuccess()){
            session.setAttribute("user",sr.getData());
        }
        return sr;
    }

    /**
     * 用户注册
     * @param u
     * @return
     */
    @RequestMapping("/register.do")
    public ServerResponse<Users> register(Users u){
        return userService.register(u);
    }


    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @RequestMapping("/getuserdata.do")
    public ServerResponse<Users> getUserData(HttpSession session){
        Users user = (Users) session.getAttribute("user");
        if (user==null){
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.FORCE_EXIT.getDesc()
            );
        }
        UsersVo users = ObjectUtils.Users(user);
        return ServerResponse.successRS(users);
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/tui.do")
    public ServerResponse<Users> tui(HttpSession session){
        Users user = (Users) session.getAttribute("user");
        if (user!=null){
            session.removeAttribute("user");
            return ServerResponse.successRS(
                    ConstCode.DEFAULT_SUCCRSS,
                    ConstCode.UserEnum.LOGOUT.getDesc());
        }
        return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.NO_LOGIN.getDesc());
    }


    /**
     * 获取用户详细信息
     * @param session
     * @return
     */
    @RequestMapping("/getalluserdata.do")
    public ServerResponse<Users> getAllUserData(HttpSession session){
        Users user = (Users) session.getAttribute("user");
        if (user==null){
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.FORCE_EXIT.getDesc()
            );
        }
        return ServerResponse.successRS(user);
    }


    /**
     * 更改邮箱手机号
     * @param email
     * @param uhone
     * @param img
     * @param session
     * @return
     */
    @RequestMapping("/updateuser.do")
    public ServerResponse<Users> updateUsers(String email,String uhone,String wenti,String daan,String img,HttpSession session){
        Users user = (Users) session.getAttribute("user");
        if (user==null){
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.FORCE_EXIT.getDesc()
            );
        }
        return userService.updateUsers(email,uhone,wenti,daan,img,user);
    }

    /**
     * 查看用户名或邮箱是否存在
     * @param str
     * @param type
     * @return
     */
    @RequestMapping("/jiaoyan.do")
    public ServerResponse<Users> selectByUsernameAndEmail(String str,String type){
        return userService.selectByUsernameAndEmail(str,type);
    }

    /**
     * 找回密码
     * @param username
     * @return
     */
    @RequestMapping("/hui.do")
    public ServerResponse<Users> selectWenTi(String username){
        return userService.selectWenTi(username);
    }

    /**
     * 提交问题答案
     * @param username
     * @param wenti
     * @param daan
     * @return
     */
    @RequestMapping("/daa.do")
    public ServerResponse<Users> selectByUsernameAndWenTiAndDaAn(String username,String wenti,String daan){
        return userService.selectByUsernameAndWenTiAndDaAn(username,wenti,daan);
    }



}
