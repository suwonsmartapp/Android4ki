package com.suwonsmartapp.android4ki.listview;

/**
 * Created by junsuk on 16. 5. 31..
 */
public class Contact {
    private String name;
    private String imageUri;

    public Contact(String imageUri, String name) {
        this.imageUri = imageUri;
        this.name = name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
