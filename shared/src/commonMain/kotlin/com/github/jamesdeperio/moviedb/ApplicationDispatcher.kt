package com.github.jamesdeperio.moviedb

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
expect val ApplicationDispatcher: CoroutineDispatcher
