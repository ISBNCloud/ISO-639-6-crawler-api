package com.isbncloud.iso639.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("alpha_object")
public class Alpha4 implements Serializable {

    @XStreamAlias("alpha4_id")
    private final String alpha4Id;
    @XStreamAlias("alpha4_parentid")
    private final String alpha4ParentId;
    @XStreamAlias("reference_name")
    private final String referenceName;

    public Alpha4(String alpha4Id, String alpha4ParentId, String referenceName) {
        this.alpha4Id = alpha4Id;
        this.alpha4ParentId = alpha4ParentId;
        this.referenceName = referenceName;
    }

    public String getAlpha4Id() {
        return alpha4Id;
    }

    public String getAlpha4ParentId() {
        return alpha4ParentId;
    }

    public String getReferenceName() {
        return referenceName;
    }
}
