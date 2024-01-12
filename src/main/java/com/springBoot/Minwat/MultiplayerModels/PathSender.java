package com.springBoot.Minwat.MultiplayerModels;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PathSender {
	private String player1;
	private String player2;
	private List<Integer> path;
	private Integer pres;
}
