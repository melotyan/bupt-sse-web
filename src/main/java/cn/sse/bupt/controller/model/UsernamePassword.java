package cn.sse.bupt.controller.model;

import java.io.Serializable;

/**
 * Created by hao.yan on 2015/12/21.
 */
public class UsernamePassword implements Serializable {
    private static final long serialVersionUID = 4323582521548321358L;
    private String username;
    private String password;

    public UsernamePassword() {

    }

    public UsernamePassword(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UsernamePassword:{");
        sb.append("username:").append(username).append(";password:").append(password).append("}");
        return sb.toString();
    }
}
