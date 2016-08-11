package com.charge.config.vo;

import com.charge.model.Favorite;
import com.charge.model.User;

import java.util.List;

/**
 * Created by liumw on 2016/8/11 0011.
 */
public class UserInfo {
    User user;
    List<Favorite> favoriteList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }
}
