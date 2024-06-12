package com.spacestar.back.report.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40)
    private String fromMember;
    @Column(length = 40)
    private String toMember;
    @Column(length = 200)
    private String content;
    @Column(length = 200)
    private String picture;

    @Builder
    public Report(String fromMember, String toMember, String content,String picture) {
        this.fromMember = fromMember;
        this.toMember = toMember;
        this.content = content;
        this.picture = picture;
    }

}
