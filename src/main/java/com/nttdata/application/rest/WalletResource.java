package com.nttdata.application.rest;

import com.nttdata.btask.interfaces.WalletService;
import com.nttdata.domain.models.WalletDto;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/wallets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletResource {
  private final WalletService walletService;

  public WalletResource(WalletService walletService) {
    this.walletService = walletService;
  }

  @GET
  @Timed(name = "wallet_list")
  @Counted(name = "count_list")
  public Multi<WalletDto> findAll() {
    return walletService.list();
  }

  @POST
  @Timed(name = "wallet_add")
  @Counted(name = "count_add")
  public Uni<WalletDto> add(WalletDto walletDto) {
    return walletService.addWallet(walletDto);
  }

  @POST
  @Path("/signIn")
  public Uni<WalletDto> signInWallet(WalletDto walletDto) {
    return walletService.signInWallet(walletDto);
  }


  @PUT
  public Uni<WalletDto> updateCustomer(WalletDto walletDto) {
    return walletService.updateWallet(walletDto);
  }

  @POST
  @Path("/search")
  public Uni<WalletDto> findByNroAccount(WalletDto walletDto) {
    return walletService.findByNroWallet(walletDto);
  }

  @DELETE
  public Uni<WalletDto> deleteCustomer(WalletDto walletDto) {
    return walletService.deleteWallet(walletDto);
  }

  @POST
  @Path("/deposit")
  public Uni<WalletDto> depositWallet(WalletDto walletDto) {
    return walletService.depositWallet(walletDto);
  }

  @POST
  @Path("/withdrawal")
  public Uni<WalletDto> withdrawalWallet(WalletDto walletDto) {
    return walletService.withdrawalWallet(walletDto);
  }

  @POST
  @Path("/prueba/{key}")
  public Uni<String> getAll(@PathParam("key") String key) {
    return walletService.getRedis(key);
  }
}
