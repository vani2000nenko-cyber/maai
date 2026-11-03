package com.aiaggregator.app.data.provider

enum class ProviderType(val displayName: String, val defaultBaseUrl: String) {
    OPENCODE("OpenCode", "https://api.opencode.ai/v1"),
    OPENCODE_GO("OpenCode Go", "https://api.opencode.ai/go/v1"),
    OPENCODE_ZEN("OpenCode Zen", "https://api.opencode.ai/zen/v1")
}

data class ProviderConfig(
    val type: ProviderType,
    val baseUrl: String,
    val apiKey: String,
    val enabled: Boolean = true
)
