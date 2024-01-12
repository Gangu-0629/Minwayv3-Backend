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
public class AcceptanceModel {
		private String sender;
		private String receiver;
		private List<Integer> arr;
}
