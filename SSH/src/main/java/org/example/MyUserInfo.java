package org.example;

import com.jcraft.jsch.*;
public class MyUserInfo implements UserInfo {
    @Override
    public String getPassphrase() {
        return Main.password ;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean promptPassword(String s) {
        return false;
    }

    @Override
    public boolean promptPassphrase(String s) {
        return false;
    }

    @Override
    public boolean promptYesNo(String s) {
        return true;
    }

    @Override
    public void showMessage(String s) {

    }
}
