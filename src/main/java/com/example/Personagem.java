package com.example;

import java.util.List;

public class Personagem {
    private String name;
    private String img;
    private List<String> actor;
    private List<Episode> episodes;
    private List<String> occupation;
    private String id;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getActor() {
        return actor;
    }

    public void setActor(List<String> actor) {
        this.actor = actor;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<String> getOccupation() {
        return occupation;
    }

    public void setOccupation(List<String> occupation) {
        this.occupation = occupation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Personagem() {
    }

    @Override
    public String toString() {
        return "Personagem{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", actor=" + actor +
                ", episodes=" + episodes +
                ", occupation=" + occupation +
                ", id='" + id + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
