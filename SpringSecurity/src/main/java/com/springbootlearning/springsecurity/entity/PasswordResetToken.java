package com.springbootlearning.springsecurity.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {
    private static final int EXPIRATION_TIME = 10;
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "verificaiton_token_sequence"
    )
    private Long tokenId;
    private String token;
    private Date expirtationTime;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "userId"
    )
    private User user;

    public PasswordResetToken(User user, String token){
        super();
        this.token = token;
        this.user = user;
        this.expirtationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    public PasswordResetToken(String token){
        super();
        this.token = token;
        this.expirtationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    private Date calculateExpirationDate(int expirationTime){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expirationTime);
        return new Date(cal.getTime().getTime());

    }

}
