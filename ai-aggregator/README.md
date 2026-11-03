# Meldo AI Aggregator

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0-blue.svg)](https://kotlinlang.org/)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-purple.svg)](https://developer.android.com/jetpack/compose)

**Meldo AI Aggregator** — Android-приложение-агрегатор для работы с моделями искусственного интеллекта. Объединяет облачные провайдеры (OpenCode / OpenCode Go / OpenCode Zen) в одном интерфейсе в стиле **Material You**.

> ⚠️ **Внимание.** Ответы ИИ могут быть неточными — проверяйте важную информацию.

## ✨ Возможности

- 🎨 **Material You** — динамическая цветовая схема, адаптирующаяся под обои устройства.
- ☁️ **Облачные провайдеры** — подключение OpenCode, OpenCode Go, OpenCode Zen по API-ключу (хранится локально).
- 🛡️ **Онбординг-политика** — при первом запуске пользователь должен принять политику использования (ошибки ИИ, конфиденциальность, ответственность).
- 💬 **Чат** — единый интерфейс диалога.
- 🔐 **Безопасность** — API-ключи хранятся локально (DataStore).

## 🧱 Стек

| Компонент | Технология |
|---|---|
| Язык | Kotlin 2.0 |
| UI | Jetpack Compose, Material 3 (Material You) |
| DI | Hilt |
| Навигация | Navigation Compose |
| Хранение | DataStore Preferences, SharedPreferences |
| Сеть | Retrofit, OkHttp |
| Сборка | Gradle 8.9, AGP 8.5 |
| minSdk / targetSdk | 26 / 35 |

## 📋 Требования

- Android Studio Hedgehog (2023.1.1) или новее
- JDK 17
- Android SDK 35
- Устройство/эмулятор Android 8.0+ (API 26+)

## 🚀 Установка и запуск

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/<your-username>/ai-aggregator.git
   ```
2. Откройте папку `ai-aggregator` в Android Studio.
3. Дождитесь синхронизации Gradle.
4. Запустите на устройстве/эмуляторе: **Run → Run 'app'**.

### Сборка APK вручную
Меню **Build → Build Bundle(s) / APK(s) → Build APK(s)**.  
Готовый APK: `app/build/outputs/apk/debug/app-debug.apk`.

### Автоматическая сборка (GitHub Actions)
При каждом push в `main`/`master` workflow `.github/workflows/build.yml` собирает debug APK и публикует как артефакт. Скачать можно во вкладке **Actions**.

## 📁 Структура проекта

```
ai-aggregator/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/aiaggregator/app/
│   │   │   ├── AIAggregatorApp.kt          # Application (Hilt)
│   │   │   ├── MainActivity.kt             # Точка входа, навигация
│   │   │   ├── data/
│   │   │   │   └── provider/               # Провайдеры и репозиторий
│   │   │   └── ui/
│   │   │       ├── screens/                # Экраны (Policy, Home, Providers, Chat, Settings)
│   │   │       └── theme/                  # Material You тема
│   │   └── res/
│   └── build.gradle.kts
├── .github/workflows/build.yml
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── LICENSE
└── README.md
```

## 🔌 Провайдеры (OpenCode)

Пользователь сам подключает один из вариантов:
- **OpenCode** — `https://api.opencode.ai/v1`
- **OpenCode Go** — `https://api.opencode.ai/go/v1`
- **OpenCode Zen** — `https://api.opencode.ai/zen/v1`

Вводится Base URL и API-ключ. Ключ хранится локально в DataStore.

## ⚠️ Отказ от ответственности

- Модели ИИ могут ошибаться. Не полагайтесь на них в критически важных вопросах (медицина, право, финансы, безопасность).
- Приложение не несёт ответственности за ущерб данным или за контент, полученный от моделей.
- Пользователь несёт полную ответственность за соблюдение условий использования провайдеров и законодательства.

## 🗺️ Дорожная карта

- [ ] Интеграция llama.cpp для реального запуска локальных GGUF-моделей
- [ ] Шифрование API-ключей (EncryptedSharedPreferences)
- [ ] История диалогов и экспорт
- [ ] Поддержка дополнительных провайдеров
- [ ] Тёмная/светлая тема с ручным переключением

## 📄 Лицензия

Проект распространяется под лицензией [MIT](LICENSE).

## 🤝 Вклад

Pull requests приветствуются. Для крупных изменений сначала откройте issue для обсуждения.
