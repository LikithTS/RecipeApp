package com.numan.feature_recipe.domain.util

/**
 * Created a custom paginator class to handle pagination to our need which support Result class to handle success and failure.
 * Note: We can re-use this class in other project noted if you keep the Result class same as what we created in this project.
 */
class CustomPaginationLib<Key, Item, E : RootError>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> Result<Item, E>,
    private val getNextKey: suspend (currentKey: Key, result: Item) -> Key,
    private val onError: suspend (E) -> Unit,
    private val onSuccess: suspend (result: Item, newKey: Key) -> Unit,
    private val endReached: (currentKey: Key, result: Item) -> Boolean
) {

    private var currentKey = initialKey
    private var isMakingRequest = false
    private var isEndReached = false

    suspend fun loadNextItems() {
        if(isMakingRequest || isEndReached) {
            return
        }

        isMakingRequest = true
        onLoadUpdated(true)

        when (val result = onRequest(currentKey)) {
            is Result.Success -> {
                val item = result.data
                /**
                 * If you receive a null as response. Then no need to keep showing loading indicator.
                 */
                if (item == null) {
                    onLoadUpdated(false)
                    isMakingRequest = false
                    return
                }

                val newKey = getNextKey(currentKey, item)
                currentKey = newKey
                onSuccess(item, newKey)
                onLoadUpdated(false)
                isEndReached = endReached(currentKey, item)
                isMakingRequest = false
            }

            is Result.Error -> {
                onError(result.error)
                onLoadUpdated(false)
                isMakingRequest = false
            }
        }
    }

    fun reset() {
        currentKey = initialKey
        isEndReached = false
    }
}