package com.example.Blog_App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value  =HttpStatus.NOT_FOUND)
public class ResourceNotFoundException  extends RuntimeException{
    private  String resourceName;
    private String fieldName;
   private String fieldValue;

   public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
       super(String.format("$ not found with %$ : '%S'", resourceName, fieldName, fieldValue));
       // Post not found with id : 1
       this.resourceName = resourceName;
       this.fieldName = fieldName;
       this.fieldValue = fieldValue;
   }
   public String getResourceName() {
       return resourceName;
   }

    public String getFieldName() {
        return fieldName;
    }
    public String getFieldValue() {
       return fieldValue;

    }
}

