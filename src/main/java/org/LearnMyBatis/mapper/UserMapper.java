package org.LearnMyBatis.mapper;

import org.LearnMyBatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> selectUser();

    User selectuserID(int id);

    List<User> selectByAgeUser(int age);

    List<User> selectByConditionsParam(@Param("name")String name, @Param("age")int age);

    List<User> selectByConditionsPOJO(User user);

    List<User> selectByConditionsPOJOIf(User user);

    List<User> selectSingleChoose(User user);

    List<User> selectSingleChooseWWhere(User user);

}
