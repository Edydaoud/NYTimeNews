package com.github.nytimesnewsapp.base.domain

import kotlinx.coroutines.*

abstract class BaseUseCase<ParamsType, ResultType> {

    private var parent: Job = SupervisorJob()
    private lateinit var scope: CoroutineScope

    abstract suspend fun run(params: ParamsType): ResultType

    operator fun invoke(
        scope: CoroutineScope,
        params: ParamsType,
        onFinished: suspend (ResultType) -> Unit = {}
    ) {
        this.scope = scope
        parent.cancel()
        parent = SupervisorJob()

        this.scope.launch(Dispatchers.IO + parent) {
            val result = run(params)
            withContext(Dispatchers.Main) { onFinished(result) }
        }
    }

    class None
}