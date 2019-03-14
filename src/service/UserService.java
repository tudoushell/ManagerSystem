package service;

import entity.User;

public interface UserService {

    /**
     * 修改用户密码
     * @param username
     * @param password
     * @return
     */
    boolean updateUser(String username,String password);
    /**
     * 判断用户密码是否正确
     * @param username
     * @param password
     * @return
     */
    User isUser(String username, String password);
}
