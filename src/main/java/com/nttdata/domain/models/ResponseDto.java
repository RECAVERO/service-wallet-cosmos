package com.nttdata.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
  private String status;
  private String msg;
  private CustomerDto customerDto;
  private WalletDto walletDto;
}
