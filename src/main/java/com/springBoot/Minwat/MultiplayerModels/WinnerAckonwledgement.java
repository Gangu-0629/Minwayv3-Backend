package com.springBoot.Minwat.MultiplayerModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class WinnerAckonwledgement 
{
	private String player1;
	private String player2;
	private String winner;
}
