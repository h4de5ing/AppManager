package com.code19.appmanager.sugar;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by gh0st on 2017/2/18.
 */

@Table(name = "book")
public class Book extends SugarRecord {
    private Long id;
    private String title;

    public Book() {
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String edition;


    public Book(String title, String edition) {
        this.title = title;
        this.edition = edition;
    }

    public Long getId() {
        return id;
    }
}
