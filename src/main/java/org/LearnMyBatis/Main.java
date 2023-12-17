package org.LearnMyBatis;

import org.LearnMyBatis.mapper.UserMapper;
import org.LearnMyBatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        // get resource
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // ini SQLSessionFactory
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

//        try (SqlSession session = sqlSessionFactory.openSession()) {
//            List<User> userList = session.selectList(
//                    "org.mybatis.example.BlogMapper.selectBlog", 101);
//        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            /*
            Using an interface (e.g. BlogMapper.class) that properly describes the parameter and
            return value for a given statement,
            you can now execute cleaner and more type safe code,
            without error prone string literals and casting.
             */
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> userList = mapper.selectUser();

            for (User s:userList
                 ) {
                System.out.println(s.toString());
            }

            User user = mapper.selectuserID(1);

            System.out.println(user.toString());

            userList = mapper.selectByAgeUser(50);
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }

            System.out.println("now start test condition select");

            userList = mapper.selectByConditionsParam("a",30);
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }

            System.out.println("now start test condition select pojo");
            userList = mapper.selectByConditionsPOJO(new User(1,null,0,"sr"));
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }

            System.out.println("now start test condition select with dynamic sql");

            userList = mapper.selectByConditionsPOJOIf(new User(1,"a",30,null));
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }

            System.out.println("now start test choose w/o where");


//            select * from user         where                           and name = ? -- if age = - 1
            userList = mapper.selectSingleChoose(new User(1,"a",15,null));
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }

            System.out.println("now start test choose with where");

            userList = mapper.selectSingleChooseWWhere(new User(1,"a",15,null));
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }
        }

    }
}