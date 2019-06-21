package com.atguigu.cache.bean;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department implements Serializable{

	private static final long serialVersionUID = 7815188007383457561L;
	private Integer id;
	private String departmentName;

}
