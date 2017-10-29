package com.adminportal.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

public class BookDetail {
  
    private final Long id;
 
    private final String title;
 

    @JsonCreator 
    public BookDetail(@JsonProperty("id")Long id, 
                          @JsonProperty("title") String title) { 
        Assert.notNull(id, "id must not be null");

        Assert.notNull(title, "title must not be null"); 
        this.id = id;
        this.title = title; 
    }
 
    public Long getid() {
        return this.id; 
    }
 
    public String gettitle() { 
        return this.title; 
    }
 
    @Override 
    public int hashCode() { 
        return this.id.hashCode() * 31 + this.title.hashCode(); 
    }

    @Override 
    public boolean equals(Object obj) { 
        if (obj == this) { 
            return true; 
        } 
        if (obj == null || obj.getClass() != getClass()) { 
            return false; 
        } 
        BookDetail other = (BookDetail) obj; 
        return this.id.equals(other.id) && this.title.equals(other.title); 
    }
  
}