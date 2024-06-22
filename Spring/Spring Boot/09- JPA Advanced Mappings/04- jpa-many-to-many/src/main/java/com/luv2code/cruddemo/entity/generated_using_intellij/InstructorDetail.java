package com.luv2code.cruddemo.entity.generated_using_intellij;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity
@Table(name = "instructor_detail", schema = "hb-05-many-to-many")
public class InstructorDetail {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "youtube_channel", length = 128)
    private String youtubeChannel;

    @Column(name = "hobby", length = 45)
    private String hobby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

}