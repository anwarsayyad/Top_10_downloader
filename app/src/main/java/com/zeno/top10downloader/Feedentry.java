package com.zeno.top10downloader;

public class Feedentry {
    private String name;

    private String realse_date;
    private String Author_and_singer;

    public String getImage_url() {
        return Image_url;
    }

    public void setImage_url(String image_url) {
        Image_url = image_url;
    }

    private String Image_url;

    public void setName(String name) {
        this.name = name;
    }



    public void setRealse_date(String realse_date) {
        this.realse_date = realse_date;
    }

    public void setAuthor_and_singer(String author_and_singer) {
        Author_and_singer = author_and_singer;
    }

    public String getName() {
        return name;
    }

    public String getRealse_date() {
        return realse_date;
    }

    public String getAuthor_and_singer() {
        return Author_and_singer;
    }



    @Override
    public String toString() {
        return "name=" + name + '\n' +
                "realse_date=" + realse_date + '\n' +
                "Author_and_singer=" + Author_and_singer + '\n' +
                "Image_url=" + Image_url + '\n' ;
    }
}
