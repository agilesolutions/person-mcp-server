package com.agilesolutions.mcp.config;

import com.agilesolutions.mcp.tools.PersonTools;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class McpConfig {

    @Bean
    public ToolCallbackProvider tools(PersonTools personTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(personTools)
                .build();
    }

    @Bean
    public List<McpServerFeatures.SyncPromptRegistration> prompts() {
        var prompt = new McpSchema.Prompt("persons-by-nationality", "Get persons by nationality",
                List.of(new McpSchema.PromptArgument("nationality", "Person nationality", true)));

        var promptRegistration = new McpServerFeatures.SyncPromptRegistration(prompt, getPromptRequest -> {
            String nameArgument = (String) getPromptRequest.arguments().get("name");
            var userMessage = new McpSchema.PromptMessage(McpSchema.Role.USER,
                    new McpSchema.TextContent("How many persons are from " + nameArgument + " ?"));
            return new McpSchema.GetPromptResult("Count persons by nationality", List.of(userMessage));
        });

        return List.of(promptRegistration);
    }
}
