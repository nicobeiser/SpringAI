package ttps.java.springaitest;

import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/deepseek")
@CrossOrigin("*")
public class DeepSeekAI {

    private DeepSeekChatModel chatModel;
    private ChatClient chatClient;

    public DeepSeekAI(DeepSeekChatModel chatModel) {
        this.chatModel = chatModel;
    }




        @GetMapping("/{message}")
        public ResponseEntity<String> getAnswer(@PathVariable String message) {
            try {
                String response = chatModel.call(message);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                // Esto te va a mostrar el error real en la consola
                e.printStackTrace();
                return ResponseEntity
                        .status(500)
                        .body("Error En el backend: " + e.getMessage());
            }
        }
}




