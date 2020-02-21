package com.itdr.mapper;

import com.itdr.pojo.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectByUsername(String username);

    Users selectByUsernameAndPassword(String username, String password);

    int updateUsers(Users users);

    int selectByUsernameAndEmail(@Param("str") String str,@Param("type") String type);

    Users selectByusernameAndWenTiAndDaAn(@Param("username") String username,
                                          @Param("wenti") String wenti,
                                          @Param("daan") String daan);

    int updateByPassword(@Param("username") String username,@Param("passwordNew") String password);

    int updatePasswordSetPasswordNew(@Param("username") String username,
                                     @Param("password") String password,
                                     @Param("passwordNew") String passwordNew);
}