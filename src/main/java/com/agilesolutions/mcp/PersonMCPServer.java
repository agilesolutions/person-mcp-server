package com.agilesolutions.mcp;

import com.agilesolutions.mcp.tools.PersonTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PersonMCPServer {

    public static void main(String[] args) {
        SpringApplication.run(PersonMCPServer.class, args);
    }

    @Bean
    public ToolCallbackProvider tools(PersonTools personTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(personTools)
                .build();
    }

}