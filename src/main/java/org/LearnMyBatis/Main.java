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

            User user = mapper.selectuserID(20);

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

            System.out.println("now adding");

            mapper.addUser(new User("c",20,"sr"));

            //commit changes
            session.commit();

            userList = mapper.selectUser();
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }

            //return id added
            System.out.println("now adding and return newly added id");


            User addedUser = new User("d",20,"sr");
            int id = mapper.addUserReturnId(addedUser);
            int userId = addedUser.getId();

            //commit changes
            session.commit();

            userList = mapper.selectUser();
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }

            System.out.println(id);
            System.out.println(userId);

            //return id added

            System.out.println("now update User");


            addedUser.setName("e");
            addedUser.setAge(-1);
            addedUser.setCurPosition("sr");
            mapper.updateUser(addedUser);

            //commit changes
            session.commit();

            userList = mapper.selectUser();
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }


            //return id added

            //need provide all information of user
            //if not, will update to null ---> if test
            //deal with comma -- name, age, position --> update age ---> update set age = ?, where...

            System.out.println("now update User with set and if");


            addedUser.setName("f");
            addedUser.setAge(-3); //User{id=17, name='f', age=-1, curPosition='sr'}
            mapper.updateUserDynamic(addedUser);

            //commit changes
            session.commit();

            userList = mapper.selectUser();
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }

            System.out.println("now delete by id");



            mapper.delById(11);

            //commit changes
            session.commit();

            userList = mapper.selectUser();
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }

            System.out.println("now delete by id list");


            int[] ids = new int[]{1,2,3,4,5};
            mapper.delUsers(ids);

            //commit changes
            session.commit();

            userList = mapper.selectUser();
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }

            System.out.println("now delete by id list");


            int[] idss = new int[]{10,11,12,13};
            mapper.delUsersArray(idss);

            //commit changes
            session.commit();

            userList = mapper.selectUser();
            for (User s:userList
            ) {
                System.out.println(s.toString());
            }


        }

    }
}