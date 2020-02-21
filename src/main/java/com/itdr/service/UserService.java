package com.itdr.service;

import com.itdr.common.ServerResponse;
import com.itdr.pojo.Users;

public interface UserService {
    ServerResponse login(String username, String password);

    ServerResponse<Users> register(Users u);

    ServerResponse<Users> updateUsers(String email, String uhone,String wenti,String daan, String img, Users user);

    ServerResponse<Users> selectByUsernameAndEmail(String str, String type);

    ServerResponse<Users> selectWenTi(String username);

    ServerResponse<Users> selectByUsernameAndWenTiAndDaAn(String username, String wenti, String daan);

    ServerResponse<Users> updatePassword(String username, String wenti, String daan);

    ServerResponse<Users> updatePasswordSetPasswordNew(Users user, String password, String passwordNew);

}
