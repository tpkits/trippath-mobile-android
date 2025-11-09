package com.sejun2.trippath.data.network.interceptor;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class HeaderInterceptor_Factory implements Factory<HeaderInterceptor> {
  private final Provider<Context> contextProvider;

  private HeaderInterceptor_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public HeaderInterceptor get() {
    return newInstance(contextProvider.get());
  }

  public static HeaderInterceptor_Factory create(Provider<Context> contextProvider) {
    return new HeaderInterceptor_Factory(contextProvider);
  }

  public static HeaderInterceptor newInstance(Context context) {
    return new HeaderInterceptor(context);
  }
}
