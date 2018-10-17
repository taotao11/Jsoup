package com.jsoup.demo.entity;
/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since 2018-08-29
 */
public class OtherJsoup {
    private String id;
    private String name;
    private String cssQuery;
    private String parentId;
    private String jID;


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public OtherJsoup() {
    }
    public String getjID() {
        return jID;
    }

    public void setjID(String jID) {
        this.jID = jID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
