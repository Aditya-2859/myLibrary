package com.library.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "allocatebook")
public class AllocateBook {

    @Id
    @Column(name = "bookid")
    private int bookid;

    @Column(name = "borrowerid")
    private int borrowerid;

    public int getBookid() {
        return bookid;
    }

    public int getBorrowerid() {
        return borrowerid;
    }
}
