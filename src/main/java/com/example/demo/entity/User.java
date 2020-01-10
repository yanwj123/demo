package com.example.demo.entity;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @JacksonXmlProperty(localName = "name" )
    private String name;

    @JacksonXmlProperty(localName = "age")
    private Integer age;

}
