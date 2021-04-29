package com.example.demo.jpa.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sellers")
@Data
@NoArgsConstructor
public class SellerEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String accountId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "seller", orphanRemoval = true)
    private ProfileEntity profile;


    public SellerEntity(String accountId)
    {
        this.accountId = accountId;
    }
//
//    public long getId()
//    {
//        return id;
//    }
//
//    public void setId(long id)
//    {
//        this.id = id;
//    }
//
//    public String getAccountId()
//    {
//        return accountId;
//    }
//
//    public void setAccountId(String accountId)
//    {
//        this.accountId = accountId;
//    }
//
//    public ProfileEntity getProfile()
//    {
//        return profile;
//    }
//
//    public void setProfile(ProfileEntity profile)
//    {
//        this.profile = profile;
//    }
//
//    @Override
//    public String toString()
//    {
//        if (profile == null)
//        {
//            return super.toString();
//        }
//        else
//        {
//            return getProfile().getFirstName() + " " + getProfile().getLastName();
//        }
//    }
}

