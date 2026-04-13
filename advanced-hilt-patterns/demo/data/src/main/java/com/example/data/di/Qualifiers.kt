package com.example.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InternalApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PublicApi
