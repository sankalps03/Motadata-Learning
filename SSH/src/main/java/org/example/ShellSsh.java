package org.example;

import com.jcraft.jsch.*;

import java.io.BufferedReader;

import java.util.Scanner;

public class ShellSsh {

    static String password;

    public static void main(String[] args) {
        Scanner scan = null;

        BufferedReader input = null;

        Session session = null;

        Channel channel = null;

        try {
            scan=new Scanner(System.in);

            String host ;

            System.out.println("Enter username@hostname");

            host = scan.nextLine();

            System.out.println();

            System.out.println("Enter password");

            password = scan.nextLine();

            String user = host.substring(0, host.indexOf('@'));

            host = host.substring(host.indexOf('@')+1);

            JSch jsch=new JSch();

            session = jsch.getSession(user,host,22);

            session.setPassword(password);

            UserInfo userinfo = new MyUserInfo();

            session.setUserInfo(userinfo);

            session.connect(3000000);

            channel = (ChannelShell) session.openChannel("shell");

            channel.setInputStream(System.in);

            channel.setOutputStream(System.out);

            channel.connect(30000);




        }catch (Exception e){

            e.printStackTrace();

        }finally {
            }if (scan != null){

                try {

                    scan.close();

                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        }
    }
