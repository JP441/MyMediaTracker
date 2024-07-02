package org.jp441.mymediatracker.covers;

import javafx.scene.image.Image;

public abstract class Cover {
    protected String id;
    protected String url;
    protected Image image;

    public Image getCover(){
        return image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
