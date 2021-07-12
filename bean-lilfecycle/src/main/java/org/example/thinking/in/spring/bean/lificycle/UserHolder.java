package org.example.thinking.in.spring.bean.lificycle;

import org.example.thinking.in.spring.ioc.overview.domain.User;

/**
 * @Author Constant
 * @Date 2021/7/12 23:52
 * @Description TODO
 **/
public class UserHolder {
    private final User user;

    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
