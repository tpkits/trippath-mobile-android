package com.sejun2.trippath.core.di;

import com.sejun2.trippath.data.network.api.AuthApiService;
import com.sejun2.trippath.data.network.client.NetworkClient;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class NetworkModule_ProvideAuthApiServiceFactory implements Factory<AuthApiService> {
  private final Provider<NetworkClient> networkClientProvider;

  private NetworkModule_ProvideAuthApiServiceFactory(
      Provider<NetworkClient> networkClientProvider) {
    this.networkClientProvider = networkClientProvider;
  }

  @Override
  public AuthApiService get() {
    return provideAuthApiService(networkClientProvider.get());
  }

  public static NetworkModule_ProvideAuthApiServiceFactory create(
      Provider<NetworkClient> networkClientProvider) {
    return new NetworkModule_ProvideAuthApiServiceFactory(networkClientProvider);
  }

  public static AuthApiService provideAuthApiService(NetworkClient networkClient) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAuthApiService(networkClient));
  }
}
