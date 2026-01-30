package com.newzhxu.proxy;

public class UserImpl implements UserService {
    @Override
    public String getUser(int id) {
        return "User#" + id;
    }
}
