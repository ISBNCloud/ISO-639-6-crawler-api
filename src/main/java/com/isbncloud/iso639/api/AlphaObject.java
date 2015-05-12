package com.isbncloud.iso639.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("alpha_object")
public class AlphaObject implements Serializable {

    @XStreamAlias("alpha4_id")
    final private String alpha4Id;
    @XStreamAlias("alpha4_parentid")
    final private String alpha4ParentId;
    @XStreamAlias("reference_name")
    final private String referenceName;

    public AlphaObject(String alpha4Id, String alpha4ParentId, String referenceName) {
        this.alpha4Id = alpha4Id;
        this.alpha4ParentId = alpha4ParentId;
        this.referenceName = referenceName;
    }
}
