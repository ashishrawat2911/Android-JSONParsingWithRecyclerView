package com.example.admin.jsonparsingwithrecyclerview;

/**
 * Created by Ashish Rawat on 2/13/2018.
 */

public class DevelopersList {
    private String login;
    private String avtar_url;
    private String html_url;
    public String getLogin() {
        return login;
    }

    public String getAvtar_url() {
        return avtar_url;
    }

    public String getHtml_url() {
        return html_url;
    }


    public DevelopersList(String login, String html_url, String avtar_url) {
        this.login = login;
        this.avtar_url = avtar_url;
        this.html_url = html_url;
    }
}
