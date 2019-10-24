package cn.edu.wzbc.blog.Mapper;

import cn.edu.wzbc.blog.Entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//映射
@Repository
public interface UserMapper {

    @Select(" select * from user WHERE username=#{username} and password=#{password} and enabled = 1")
    User CheckLogin(@Param("username") String username, @Param("password") String password);

    @Insert("insert into user (username,password,telephone,enabled,role) values (#{username},#{password},#{telephone},#{enabled},#{role}) ")
    int doRegister(User user);

    @Select(" select userid from user WHERE username=#{username}")
    User findIdByName(@Param("username") String username);

    @Select(" select * from user")
    List<User> getAllUser();

    @Select("select * from user where username!=#{username}")
    List<User> getOtherUser(@Param("username") String username);

    @Select("select * from user where role != 'admin'")
    List<User> getUserExceptAdmin();

    @Delete("delete from user where userid= #{userid}")
    int deleteUserById(long userid);

    @Update("update user set username=#{username},password=#{password},telephone=#{telephone},enabled=#{enabled},role = #{role} where userid=#{userid}")
    int UpdateUser(User user);


}

