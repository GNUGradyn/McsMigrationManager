package com.gradyn.McsMigrationManager.Data.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="usercache")
public class UserCache {
    @Id
    @Column(name="uuid")
    private String RawUUID;

    @Column(name="username")
    private String Username;

    @Column(name="Transfered")
    private Boolean Transfered;

    public String getRawUUID() {
        return this.RawUUID;
    }

    public UUID getUUID() {
        return java.util.UUID.fromString(getRawUUID());
    }

    public void setRawUUID(String UUID) {
        this.RawUUID = UUID;
    }

    public void setUUID(UUID uuid) {
        setUsername(uuid.toString());
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Boolean getTransfered() {
        return Transfered;
    }

    public void setTransfered(Boolean transfered) {
        Transfered = transfered;
    }
}
