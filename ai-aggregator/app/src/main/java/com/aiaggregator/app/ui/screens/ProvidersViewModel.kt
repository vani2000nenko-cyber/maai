package com.aiaggregator.app.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiaggregator.app.data.provider.ProviderConfig
import com.aiaggregator.app.data.provider.ProviderRepository
import com.aiaggregator.app.data.provider.ProviderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProvidersUiState(
    val selectedType: ProviderType = ProviderType.OPENCODE,
    val baseUrl: String = ProviderType.OPENCODE.defaultBaseUrl,
    val apiKey: String = "",
    val saved: ProviderConfig? = null,
    val message: String? = null
)

@HiltViewModel
class ProvidersViewModel @Inject constructor(
    private val repo: ProviderRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProvidersUiState())
    val state: StateFlow<ProvidersUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repo.config.collect { cfg ->
                _state.value = _state.value.copy(saved = cfg)
            }
        }
    }

    fun selectType(type: ProviderType) {
        _state.value = _state.value.copy(
            selectedType = type,
            baseUrl = type.defaultBaseUrl
        )
    }

    fun setBaseUrl(v: String) { _state.value = _state.value.copy(baseUrl = v) }
    fun setApiKey(v: String) { _state.value = _state.value.copy(apiKey = v) }

    fun save() {
        val s = _state.value
        if (s.apiKey.isBlank()) {
            _state.value = s.copy(message = "Введите API ключ")
            return
        }
        viewModelScope.launch {
            repo.save(ProviderConfig(s.selectedType, s.baseUrl, s.apiKey))
            _state.value = _state.value.copy(message = "Сохранено")
        }
    }

    fun clear() {
        viewModelScope.launch {
            repo.clear()
            _state.value = _state.value.copy(saved = null, message = "Удалено")
        }
    }

    fun consumeMessage() { _state.value = _state.value.copy(message = null) }
}
