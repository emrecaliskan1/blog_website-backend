package com.emre.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    private Long id;

    //bir sürü post'un bir kullanıcısı olabilir
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //user silindiğinde postları silinsin
    @JsonIgnore
    private User user;

    private String title;

    @Lob
    @Column(columnDefinition = "text")
    private String text;


}
