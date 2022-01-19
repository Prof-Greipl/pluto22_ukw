package de.hawlandshut.pluto22_ukw.model;

import com.google.firebase.database.DataSnapshot;

public class Post {

    public String uid;
    public String author;
    public String title;
    public String body;
    public long timestamp;
    public String firebaseKey;

    public Post() {
    }

    public Post(String uid,
                String author,
                String title,
                String body,
                long timestamp,
                String firebaseKey
    ) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
        this.firebaseKey = firebaseKey;
    }

    public static Post fromSnapshot(DataSnapshot snapshot){
        String uid = (String) snapshot.child("uid").getValue();
        String author = (String) snapshot.child("author").getValue();
        String title = (String) snapshot.child("title").getValue();
        String body = (String) snapshot.child("body").getValue();
        String firebaseKey = (String) snapshot.getKey();
        return new Post(uid, author, title, body, 0, firebaseKey);
    }
}