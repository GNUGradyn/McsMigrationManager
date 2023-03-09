package com.gradyn.McsMigrationManager.Data.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.util.UUID;

@Table(name="usercache")
public class UserCache {
    @Column(name="uuid")
    private UUID UUID;

    @Column(name="username")
    private String Username;

    @Column(name="Transfered")
    private Boolean Transfered;

    public java.util.UUID getUUID() {
        return UUID;
    }

    public void setUUID(java.util.UUID UUID) {
        this.UUID = UUID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public bool getTransfered() {
        return Transfered;
    }

    public void setTransfered(bool transfered) {
        Transfered = transfered;
    }
}
