package fr.univrouen.rss22.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    @Id
    private UUID guid;

    @XmlElement
    @Column
    private String title;

    @XmlElement
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;

    @XmlElements({
            @XmlElement(name = "published", type = Date.class),
            @XmlElement(name = "updated", type = Date.class)
    })

    @XmlElement(type = Image.class)
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @XmlElement(type = Content.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Content content;

    @XmlElements({
            @XmlElement(name = "author", type = Author.class),
            @XmlElement(name = "contributor", type = Author.class)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private Author author;

    @Column
    private Date date;

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

}
