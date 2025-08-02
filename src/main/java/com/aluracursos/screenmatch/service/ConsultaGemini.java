package com.aluracursos.screenmatch.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class ConsultaGemini {
    public static String obtenerTraduccion(String texto) {
        String modelo = "gemini-2.0-flash-lite";
        String prompt = "Traduce exactamente el siguiente texto al español, sin agregar ningún comentario, explicación o encabezado. \n" +
                "Devuelve únicamente la traducción, sin frases como \"Aquí tienes la traducción\" ni ningún otro texto adicional.\n" +
                "\n" +
                "Texto: " + texto;

        Client cliente = new Client.Builder().apiKey("TU-APIKEY-GEMINI").build();

        try {
            GenerateContentResponse respuesta = cliente.models.generateContent(
                    modelo,
                    prompt,
                    null
            );

            if (!respuesta.text().isEmpty()) {
                return respuesta.text();
            }
        } catch (Exception e) {
            System.err.println("Error al llamar a la API de Gemini para traducción: " + e.getMessage());
        }

        return null;
    }
}
