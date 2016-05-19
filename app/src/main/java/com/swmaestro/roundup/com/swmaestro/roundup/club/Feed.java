
package com.swmaestro.roundup.com.swmaestro.roundup.club;

public class Feed {

    private String feedTitle;
    private int feedThumbnail;
    private String feedContent;
    private String feedAuthor;

    public Feed(String feedTitle, String feedAuthor, int feedThumbnail, String feedContent) {
        this.feedTitle = feedTitle;
        this.feedThumbnail = feedThumbnail;
        this.feedContent = feedContent;
        this.feedAuthor = feedAuthor;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public String getFeedAuthor(){ return feedAuthor; }

    public int getFeedFileThumbnail() {
        return feedThumbnail;
    }

    public String getFeedContent() {
        return feedContent;
    }


}