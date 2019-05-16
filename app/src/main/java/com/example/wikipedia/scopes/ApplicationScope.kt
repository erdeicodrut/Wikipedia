package com.example.wikipedia.scopes

import javax.inject.Scope

/** The Application Scope of the dependency tree. Used for dependencies at application level. */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope