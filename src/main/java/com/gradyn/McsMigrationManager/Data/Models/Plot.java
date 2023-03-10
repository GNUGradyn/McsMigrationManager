package com.gradyn.McsMigrationManager.Data.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="plot")
public class Plot {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="plot_id_x")
    private int PlotIdX;
    @Column(name="plot_id_z")
    private int PlotIdZ;
    @Column(name="owner")
    private String Owner;
    @Column(name="world")
    private String world;
    @Column(name="timestamp")
    private Timestamp timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlotIdX() {
        return PlotIdX;
    }

    public void setPlotIdX(int plotIdX) {
        PlotIdX = plotIdX;
    }

    public int getPlotIdZ() {
        return PlotIdZ;
    }

    public void setPlotIdZ(int plotIdZ) {
        PlotIdZ = plotIdZ;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
