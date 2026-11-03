package com.aiaggregator.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PolicyScreen(onAccepted: () -> Unit) {
    var accepted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Политика использования и согласие",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = POLICY_TEXT,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = accepted, onCheckedChange = { accepted = it })
            Text(
                text = "Я прочитал(а) и принимаю условия политики",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = onAccepted,
            enabled = accepted,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Продолжить")
        }
    }
}

private const val POLICY_TEXT = """
Meldo AI Aggregator — это приложение-агрегатор для работы с моделями искусственного интеллекта. Перед началом использования внимательно ознакомьтесь с данной политикой.

1. Ошибки ИИ. Модели искусственного интеллекта могут ошибаться, выдавать неточную, неполную или вводящую в заблуждение информацию. Не полагайтесь на ответы ИИ в вопросах, требующих точности (медицина, право, финансы, безопасность). Проверяйте важную информацию из независимых источников.

2. Конфиденциальность. При использовании облачных провайдеров (OpenCode, OpenCode Go, OpenCode Zen и других) ваши запросы передаются на серверы провайдера. API-ключи хранятся в зашифрованном виде на устройстве.

3. Ответственность пользователя. Вы несёте полную ответственность за контент, который отправляете в модели и получаете от них, а также за соблюдение условий использования провайдеров и законодательства вашей страны.

4. Возрастные ограничения. Приложение предназначено для пользователей старше 13 лет (или возраста, установленного законом вашей страны).

5. Изменения политики. Политика может обновляться. Продолжая использовать приложение после обновления, вы принимаете новую версию.

Нажимая «Продолжить», вы подтверждаете, что прочитали, поняли и принимаете эту политику.
"""
