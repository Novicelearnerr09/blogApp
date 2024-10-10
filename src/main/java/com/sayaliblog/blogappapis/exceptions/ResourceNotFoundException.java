package com.sayaliblog.blogappapis.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResourceNotFoundException extends  RuntimeException {

    String resourceName;
    String fieldname;
    Integer fieldValue;
    public ResourceNotFoundException( String resourceName, String fieldname, Integer fieldValue) {
        super(String.format("%s not found in %s:%s",resourceName,fieldname,fieldValue));
        this.resourceName = resourceName;
        this.fieldname = fieldname;
        this.fieldValue = fieldValue;
    }


}
