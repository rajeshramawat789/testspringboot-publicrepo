package com.test.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Entity
@Table(name = "user_accounts")
@Data
public class UserAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "phone", length = 12, nullable = false)
    private Long phone;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "country", nullable = false, length = 56)
    private String country;

    @Column(name = "department", nullable = false, length = 50)
    private String department;

}
