package com.aluracursos.screenmatch.service;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import java.util.List;

public class ConsultaChatGPT {
    public static String obtenerTraduccion(String texto) {
        OpenAiService service = new OpenAiService("TU-APIKEY-OPENIA");

        List<ChatMessage> mensajes = List.of(
                new ChatMessage("system", "Eres un traductor profesional de inglés a español."),
                new ChatMessage("user", "Traduce exactamente este texto al español sin agregar nada más: " + texto)
        );

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-4o")
                .messages(mensajes)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        ChatCompletionResult respuesta = service.createChatCompletion(request);

        return respuesta.getChoices().get(0).getMessage().getContent().trim();
    }
}
