package com.sejun2.trippath.data.repository;

import com.sejun2.trippath.data.auth.SessionManager;
import com.sejun2.trippath.data.local.TokenDataStore;
import com.sejun2.trippath.data.network.api.AuthApiService;
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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<AuthApiService> authApiServiceProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<TokenDataStore> tokenDataStoreProvider;

  private AuthRepositoryImpl_Factory(Provider<AuthApiService> authApiServiceProvider,
      Provider<SessionManager> sessionManagerProvider,
      Provider<TokenDataStore> tokenDataStoreProvider) {
    this.authApiServiceProvider = authApiServiceProvider;
    this.sessionManagerProvider = sessionManagerProvider;
    this.tokenDataStoreProvider = tokenDataStoreProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(authApiServiceProvider.get(), sessionManagerProvider.get(), tokenDataStoreProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(Provider<AuthApiService> authApiServiceProvider,
      Provider<SessionManager> sessionManagerProvider,
      Provider<TokenDataStore> tokenDataStoreProvider) {
    return new AuthRepositoryImpl_Factory(authApiServiceProvider, sessionManagerProvider, tokenDataStoreProvider);
  }

  public static AuthRepositoryImpl newInstance(AuthApiService authApiService,
      SessionManager sessionManager, TokenDataStore tokenDataStore) {
    return new AuthRepositoryImpl(authApiService, sessionManager, tokenDataStore);
  }
}
