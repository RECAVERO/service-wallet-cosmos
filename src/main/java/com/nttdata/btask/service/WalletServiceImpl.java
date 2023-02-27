package com.nttdata.btask.service;

import com.nttdata.btask.interfaces.WalletService;
import com.nttdata.domain.contract.WalletRepository;
import com.nttdata.domain.models.WalletDto;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class WalletServiceImpl implements WalletService {
  private final WalletRepository walletRepository;
  private final redisServiceImpl redisService;

  public WalletServiceImpl(WalletRepository walletRepository, redisServiceImpl redisService) {
    this.walletRepository = walletRepository;
    this.redisService = redisService;
  }

  @Override
  public Multi<WalletDto> list() {
    List<JsonObject> lista = new ArrayList<>();
    return walletRepository.list().map(c->{
      return c;
    }).call(walletDto -> {
      JsonObject jsonCustomer = new JsonObject()
          .put("name", walletDto.getName())
          .put("lastName", walletDto.getLastName())
          .put("nroDocument", walletDto.getNroDocument())
          .put("typeDocument", walletDto.getTypeDocument())
          .put("typeOperation", walletDto.getTypeOperation())
          .put("numberTelephone", walletDto.getNumberTelephone())
          .put("amount", walletDto.getAmount())
          .put("registrationDate", walletDto.getRegistrationDate())
          .put("created_datetime", walletDto.getCreated_datetime())
          .put("updated_datetime", walletDto.getUpdated_datetime())
          .put("active", walletDto.getActive());
      System.out.println(jsonCustomer);
      lista.add(jsonCustomer);
      return redisService.redisAdd("listWallet",lista.toString());
    });
  }

  @Override
  public Uni<WalletDto> findByNroWallet(WalletDto walletDto) {
    return walletRepository.findByNroWallet(walletDto).map(c->{
      return c;
    });
  }

  @Override
  public Uni<WalletDto> addWallet(WalletDto walletDto) {
    return walletRepository.addWallet(walletDto);
  }

  @Override
  public Uni<WalletDto> updateWallet(WalletDto walletDto) {
    return walletRepository.updateWallet(walletDto);
  }

  @Override
  public Uni<WalletDto> deleteWallet(WalletDto walletDto) {
    return walletRepository.deleteWallet(walletDto);
  }

  @Override
  public Uni<WalletDto> signInWallet(WalletDto walletDto) {
    return walletRepository.signInWallet(walletDto);
  }

  @Override
  public Uni<WalletDto> depositWallet(WalletDto walletDto) {
    return walletRepository.depositWallet(walletDto);
  }

  @Override
  public Uni<WalletDto> withdrawalWallet(WalletDto walletDto) {
    return walletRepository.withdrawalWallet(walletDto);
  }

  @Override
  public Uni<String> getRedis(String key) {
    return redisService.getRedis(key);
  }
}
