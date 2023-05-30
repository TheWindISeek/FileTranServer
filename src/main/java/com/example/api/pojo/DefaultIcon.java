package com.example.api.pojo;

import java.util.Arrays;

public class DefaultIcon {
    private Integer id;//图标的id

    private String name;//图标的名字

    private String filetypes;//？

    private byte[] icon;//图标具体的内容

    @Override
    public String toString() {
        return "DefaultIcon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filetypes='" + filetypes + '\'' +
                ", icon=" + Arrays.toString(icon) +
                '}';
    }

    public DefaultIcon(int id, String name, String  filetypes, byte[] icon) {
        this.id = id;
        this.name = name;
        this.filetypes = filetypes;
        this.icon = icon;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFiletypes() {
        return filetypes;
    }

    public void setFiletypes(String filetypes) {
        this.filetypes = filetypes == null ? null : filetypes.trim();
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }
}