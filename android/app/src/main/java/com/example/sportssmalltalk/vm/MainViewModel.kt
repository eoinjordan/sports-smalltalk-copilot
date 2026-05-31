package com.example.sportssmalltalk.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportssmalltalk.data.ConversationStarter
import com.example.sportssmalltalk.data.SportsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MainUiState(
    val sport: String = "GAA",
    val setting: String = "Pub",
    val tone: String = "Safe",
    val loading: Boolean = false,
    val error: String? = null,
    val starter: ConversationStarter? = null
)

class MainViewModel(
    private val repository: SportsRepository = SportsRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun setSport(value: String) {
        _uiState.value = _uiState.value.copy(sport = value)
    }

    fun setSetting(value: String) {
        _uiState.value = _uiState.value.copy(setting = value)
    }

    fun setTone(value: String) {
        _uiState.value = _uiState.value.copy(tone = value)
    }

    fun generate() {
        val current = _uiState.value
        viewModelScope.launch {
            _uiState.value = current.copy(loading = true, error = null)
            try {
                val starter = repository.generate(current.sport, current.setting, current.tone)
                _uiState.value = _uiState.value.copy(loading = false, starter = starter)
            } catch (error: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = error.message ?: "Unable to generate starter"
                )
            }
        }
    }
}
