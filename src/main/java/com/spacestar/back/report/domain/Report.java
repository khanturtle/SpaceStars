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
    private String fromMemberUuid;
    @Column(length = 40)
    private String toMemberUuid;
    @Column(length = 200)
    private String content;
    @Column(length = 200)
    private String picture;

    @Builder
    public Report(String fromMemberUuid, String toMemberUuid, String content, String picture) {
        this.fromMemberUuid = fromMemberUuid;
        this.toMemberUuid = toMemberUuid;
        this.content = content;
        this.picture = picture;
    }

}
