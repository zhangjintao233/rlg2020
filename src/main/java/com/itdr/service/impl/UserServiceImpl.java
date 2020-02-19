package com.itdr.service.impl;

import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.config.TokenCache;
import com.itdr.mapper.UsersMapper;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public ServerResponse login(String username, String password) {
        if (StringUtils.isEmpty(username)) return ServerResponse.defeatedRS(
                ConstCode.UserEnum.EMPTY_USERNAME.getCode(),
                ConstCode.UserEnum.EMPTY_USERNAME.getDesc()
        );
        if (StringUtils.isEmpty(password)) return ServerResponse.defeatedRS(
                ConstCode.UserEnum.EMPTY_PASSWORD.getCode(),
                ConstCode.UserEnum.EMPTY_PASSWORD.getDesc()
        );
        Users users = usersMapper.selectByUsernameAndPassword(username, password);
        if (users == null) return ServerResponse.defeatedRS(
                ConstCode.UserEnum.FAIL_LOGIN.getCode(),
                ConstCode.UserEnum.FAIL_LOGIN.getDesc()
        );
        return ServerResponse.successRS(users);
    }

    @Override
    public ServerResponse<Users> register(Users u) {
        if (StringUtils.isEmpty(u.getUsername())) return ServerResponse.defeatedRS(
                ConstCode.UserEnum.EMPTY_USERNAME.getCode(),
                ConstCode.UserEnum.EMPTY_USERNAME.getDesc()
        );
        if (StringUtils.isEmpty(u.getPassword())) return ServerResponse.defeatedRS(
                ConstCode.UserEnum.EMPTY_PASSWORD.getCode(),
                ConstCode.UserEnum.EMPTY_PASSWORD.getDesc()
        );
        if (StringUtils.isEmpty(u.getEmail())) return ServerResponse.defeatedRS(
                ConstCode.UserEnum.EMPTY_EMAIL.getCode(),
                ConstCode.UserEnum.EMPTY_EMAIL.getDesc()
        );

        Users i = usersMapper.selectByUsername(u.getUsername());
        if (i != null) return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.EXIST_USER.getDesc()
        );
        int insert = usersMapper.insert(u);
        if (insert <= 0) return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.FAIL_USER.getDesc()
        );

        return ServerResponse.successRS(
                ConstCode.DEFAULT_SUCCRSS,
                ConstCode.UserEnum.SUCCESS_USER.getDesc()
        );
    }

    @Override
    public ServerResponse<Users> updateUsers(String email, String uhone, String wenti, String daan, String img, Users users) {
        Users u = new Users();
        u.setId(users.getId());
        u.setEmail(email);
        u.setUhone(uhone);
        u.setWenti(wenti);
        u.setDaan(daan);
        u.setImg(img);
        int i = usersMapper.updateUsers(u);
        if (i <= 0) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.FAIL_UPDATEUSER.getDesc()
            );
        }
        return ServerResponse.successRS(
                ConstCode.DEFAULT_SUCCRSS,
                ConstCode.UserEnum.SUCCESS_USERMSG.getDesc()
        );
    }

    @Override
    public ServerResponse<Users> selectByUsernameAndEmail(String str, String type) {
        if (StringUtils.isEmpty(str)) return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.EMPTY_EMAILORNAME.getDesc()
        );
        if (StringUtils.isEmpty(type)) return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.EMPTY_EMAILORNAME.getDesc()
        );

        int u = usersMapper.selectByUsernameAndEmail(str, type);
        if (u > 0) return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.FAIL_EMAILORNAME.getDesc()
        );

        return ServerResponse.successRS(
                ConstCode.DEFAULT_SUCCRSS,
                ConstCode.UserEnum.SUCCESS_MSG.getDesc()
        );
    }

    @Override
    public ServerResponse<Users> selectWenTi(String username) {
        if (StringUtils.isEmpty(username))return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.EMPTY_USERNAME.getDesc()
        );

        Users u = usersMapper.selectByUsername(username);
        if (u == null){
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.INEXISTENCE_USER.getDesc()
            );
        }
        String wenti = u.getWenti();
        if (StringUtils.isEmpty(wenti))return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.EMPTY_QUESTION.getDesc()
        );
        return ServerResponse.successRs(ConstCode.DEFAULT_SUCCRSS,wenti);
    }

    @Override
    public ServerResponse<Users> selectByUsernameAndWenTiAndDaAn(String username, String wenti, String daan) {
        if (StringUtils.isEmpty(username))return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.EMPTY_USERNAME.getDesc()
        );
        if (StringUtils.isEmpty(wenti))return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.EMPTY_QUESTION.getDesc()
        );
        if (StringUtils.isEmpty(daan))return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.EMPTY_ANSWER.getDesc()
        );
        Users u = usersMapper.selectByusernameAndWenTiAndDaAn(username,wenti,daan);
        if (u == null)return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.UserEnum.ERROR_ANSWER.getDesc()
        );
        String s = UUID.randomUUID().toString();
        TokenCache.set("token_"+username,s);
        return ServerResponse.successRs(ConstCode.DEFAULT_SUCCRSS,s);
    }
}
