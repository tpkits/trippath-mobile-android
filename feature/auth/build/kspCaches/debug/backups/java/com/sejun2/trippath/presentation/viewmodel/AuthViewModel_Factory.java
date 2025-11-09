package com.sejun2.trippath.presentation.viewmodel;

import com.sejun2.trippath.data.auth.GoogleCredentialService;
import com.sejun2.trippath.data.auth.KakaoAuthService;
import com.sejun2.trippath.data.auth.SessionManager;
import com.sejun2.trippath.domain.repository.IAuthRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<IAuthRepository> authRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<GoogleCredentialService> googleCredentialServiceProvider;

  private final Provider<KakaoAuthService> kakaoAuthServiceProvider;

  private AuthViewModel_Factory(Provider<IAuthRepository> authRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider,
      Provider<GoogleCredentialService> googleCredentialServiceProvider,
      Provider<KakaoAuthService> kakaoAuthServiceProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
    this.googleCredentialServiceProvider = googleCredentialServiceProvider;
    this.kakaoAuthServiceProvider = kakaoAuthServiceProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(authRepositoryProvider.get(), sessionManagerProvider.get(), googleCredentialServiceProvider.get(), kakaoAuthServiceProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<IAuthRepository> authRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider,
      Provider<GoogleCredentialService> googleCredentialServiceProvider,
      Provider<KakaoAuthService> kakaoAuthServiceProvider) {
    return new AuthViewModel_Factory(authRepositoryProvider, sessionManagerProvider, googleCredentialServiceProvider, kakaoAuthServiceProvider);
  }

  public static AuthViewModel newInstance(IAuthRepository authRepository,
      SessionManager sessionManager, GoogleCredentialService googleCredentialService,
      KakaoAuthService kakaoAuthService) {
    return new AuthViewModel(authRepository, sessionManager, googleCredentialService, kakaoAuthService);
  }
}
