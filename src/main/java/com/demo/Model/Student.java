package com.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	private int id;
	
	@NotBlank	
	private String name;
	@NotBlank
	@Size(min=4,message = "city character should be greater the 4")
	
	private String city;
}


