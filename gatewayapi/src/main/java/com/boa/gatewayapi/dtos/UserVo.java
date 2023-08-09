package com.boa.gatewayapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

	private String userName;
	private String userpwd;
	private Set<String> roles;
	

}
