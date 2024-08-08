package com.javaclass.entities;
import jakarta.persistence.*;

@Entity

@Table(
        name = "user_table"
)

public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            sequenceName = "user_sequence",
            name = "user_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(

            name = "user_name"
    )
    public String name;

    @Column(
            name = "winCount"
    )
    private int winCount;

    @Column(
            name = "lossCount"
    )
    private int loseCount;

    @Column(
            name = "drawCount"
    )
    private int drawCount;

    public int getDrawCount() {return drawCount;}

    public void setDrawCount(int drawCount) {this.drawCount = drawCount;}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public void setLoseCount(int loseCount) {
        this.loseCount = loseCount;
    }
}
