package de.hawlandshut.pluto22_ukw.test;

import java.util.ArrayList;
import java.util.List;

import de.hawlandshut.pluto22_ukw.model.Post;

public class PostTestData {
    public static List<Post> postTestList = new ArrayList<Post>();
    static String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ";

    public static void createTestData(){
        long time =  new java.util.Date().getTime();
    }
}