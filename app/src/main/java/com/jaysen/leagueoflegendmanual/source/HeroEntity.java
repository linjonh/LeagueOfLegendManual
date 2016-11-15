package com.jaysen.leagueoflegendmanual.source;

import java.io.Serializable;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/14.
 * hero entity
 */

public class HeroEntity implements Serializable {
    public String avatarUrl;
    public String legendName;
    public String legendTitle;
    public String description;
    public String tags;

    @Override
    public String toString() {
        return "Hero:[name: " + legendName
                + ",title: " + legendTitle
                + ",description: " + description
                + ",tags: " + tags
                + ",avatarUrl: " + avatarUrl + "]";
    }
}
