package com.example.foody.feature_recipe.presentation.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController

/**
 * Navigates to the specified route while saving the current state.
 *
 * @param route The route to navigate to
 * @param restoreState Whether to restore the state when navigating back to this destination
 */
fun NavController.navigateWithSavingState(
    route: Any,
    restoreState: Boolean = true,
) {
    navigate(route) {
        launchSingleTop = true
        this.restoreState = restoreState
    }
}

/**
 * Sets a value in the saved state handle of the previous back stack entry.
 *
 * @param key The key to store the value under
 * @param value The value to store
 */
fun <T> NavController.setToPreviousBackStackEntry(
    key: String,
    value: T,
) {
    this.previousBackStackEntry?.savedStateHandle?.set(key, value)
}

/**
 * Sets a value in the saved state handle of a specific back stack entry identified by its route type.
 *
 * @param key The key to store the value under
 * @param value The value to store
 */
inline fun <reified Route : Any, VALUE> NavController.setToSpecificBackStackEntry(
    key: String,
    value: VALUE,
) {
    try {
        getBackStackEntry<Route>().savedStateHandle[key] = value
    } catch (e: Exception) {
        // do nothing
    }
}

/**
 * Gets a value from the saved state handle of this back stack entry.
 *
 * @param key The key of the value to retrieve
 * @return The value associated with the key, or null if not present
 */
fun <T> NavBackStackEntry.getValue(key: String): T? = this.savedStateHandle.get<T>(key)

/**
 * Removes a value from the saved state handle of this back stack entry.
 *
 * @param key The key of the value to remove
 * @return True if the value was successfully removed, false otherwise
 */
fun <T> NavBackStackEntry.removeValue(key: String): Boolean =
    this.savedStateHandle.remove<T>(key) != null

/**
 * Checks if the current back stack entry contains a value for the specified key.
 *
 * @param key The key to check for
 * @return True if the key exists in the current back stack entry's saved state handle
 */
fun NavController.currentBackStackEntryContains(key: String): Boolean =
    this.currentBackStackEntry?.savedStateHandle?.contains(key) == true

/**
 * Checks if a route of type T exists in the current back stack.
 *
 * @return True if a destination with the specified route type is in the back stack
 */
@SuppressLint("RestrictedApi")
inline fun <reified T : Any> NavHostController.hasRouteInBackStack(): Boolean =
    currentBackStack.value
        .map {
            it.destination
        }.any { it.hasRoute<T>() }

/**
 * Gets a value from the current back stack entry's saved state handle.
 *
 * @param key The key of the value to retrieve
 * @return The value associated with the key, or null if not present
 */
fun <T> NavController.getFromCurrentBackStackEntry(key: String): T? =
    this.currentBackStackEntry?.savedStateHandle?.get(key)

/**
 * Removes a value from the current back stack entry's saved state handle.
 *
 * @param key The key of the value to remove
 */
fun <T> NavController.removeFromCurrentBackStackEntry(key: String) {
    this.currentBackStackEntry?.savedStateHandle?.remove<T>(key)
}

/**
 * Removes a value from the previous back stack entry's saved state handle.
 *
 * @param key The key of the value to remove
 */
fun <T> NavController.removeFromPreviousBackStackEntry(key: String) {
    this.previousBackStackEntry?.savedStateHandle?.remove<T>(key)
}

/**
 * Checks if a destination with the specified route is in the back stack.
 *
 * @param route The route string to check for
 * @return True if a destination with the specified route is in the back stack
 */
@SuppressLint("RestrictedApi")
fun NavController.isDestinationOnBackStack(route: String): Boolean =
    currentBackStack.value.map { it.destination.route }.contains(route)
