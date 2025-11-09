package com.sejun2.trippath.data.network.client;

import com.sejun2.trippath.data.network.interceptor.AuthInterceptor;
import com.sejun2.trippath.data.network.interceptor.HeaderInterceptor;
import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class NetworkClient_Factory implements Factory<NetworkClient> {
  private final Provider<HeaderInterceptor> headerInterceptorProvider;

  private final Provider<AuthInterceptor> authInterceptorProvider;

  private final Provider<Moshi> moshiProvider;

  private NetworkClient_Factory(Provider<HeaderInterceptor> headerInterceptorProvider,
      Provider<AuthInterceptor> authInterceptorProvider, Provider<Moshi> moshiProvider) {
    this.headerInterceptorProvider = headerInterceptorProvider;
    this.authInterceptorProvider = authInterceptorProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public NetworkClient get() {
    return newInstance(headerInterceptorProvider.get(), authInterceptorProvider.get(), moshiProvider.get());
  }

  public static NetworkClient_Factory create(Provider<HeaderInterceptor> headerInterceptorProvider,
      Provider<AuthInterceptor> authInterceptorProvider, Provider<Moshi> moshiProvider) {
    return new NetworkClient_Factory(headerInterceptorProvider, authInterceptorProvider, moshiProvider);
  }

  public static NetworkClient newInstance(HeaderInterceptor headerInterceptor,
      AuthInterceptor authInterceptor, Moshi moshi) {
    return new NetworkClient(headerInterceptor, authInterceptor, moshi);
  }
}
