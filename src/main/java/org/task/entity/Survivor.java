package org.task.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table
public class Survivor {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int survivorId;

    @Column
    private String name;

    @Column
    private String age;

    @Column
    private String gender;

    @JoinColumn
    @OneToOne
    private LastLocation lastLocation;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<Resource> resourceList;

    @Column
    private int infectionCount;

    public boolean isInfected() {
        if (this.infectionCount > 3) {
            return true;
        } else {
            return false;
        }
    }
}
