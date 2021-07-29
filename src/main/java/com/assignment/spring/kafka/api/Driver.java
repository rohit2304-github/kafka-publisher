package com.assignment.spring.kafka.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Driver {
	private String driverID;
	private double longitude;
	private double latitude;

}
