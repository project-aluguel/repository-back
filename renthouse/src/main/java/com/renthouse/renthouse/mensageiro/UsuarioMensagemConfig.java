package com.renthouse.renthouse.mensageiro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Binding;
import java.util.Queue;

@Configuration
public class UsuarioMensagemConfig {

    public static final String NOME_FILA = "FilaUsuario";
    public static final String NOME_EXCHANGE = "ExchangeUsuario";
    public static final String ROUTING_KEY = "criarUsuario";

//    @Bean
//    DirectExchange usuarioExchange() {
//        return new DirectExchange(NOME_EXCHANGE);
//    }
//
//    @Bean
//    Queue queue() {
//        return QueueBuilder.durable(NOME_FILA).build();
//    }
//
//    @Bean
//    Binding binding() {
//        return BindingBuilder.bind(queue()).to(usuarioExchange()).with(ROUTING_KEY);
//    }
}
