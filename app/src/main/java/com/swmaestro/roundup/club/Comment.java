package com.swmaestro.roundup.club;

/**
 * Created by lk on 16. 6. 21..
 */
public class Comment {

    private String comment_title;
    private String email;
    private String comment_date;
    private String comment_content;

    public String getComment_title() {
        return comment_title;
    }

    public String getEmail() {
        return email;
    }

    public String getComment_date() {
        return comment_date;
    }

    public String getComment_content() {
        return comment_content;
    }

    public Comment(String email, String comment_date, String comment_content, String comment_title) {

        this.email = email;
        this.comment_date = comment_date;
        this.comment_content = comment_content;
        this.comment_title = comment_title;
    }
}
