package com.example.WebSite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    @Lob
    @Column(columnDefinition = "text")
    public String text;
    @ManyToOne(fetch = FetchType.EAGER)
            @JoinColumn(name = "user_id",nullable = false)
            @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

}
