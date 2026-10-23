package sg.edu.ntu.siewhoon.controller;

import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.boot.jackson.autoconfigure.JacksonProperties.Factory.Constraints.Write;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class AiController {
    private final ChatClient chatClient;

    public AiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    // mvn spring-boot:run
    // localhost:8080/chat?message=What is Java?


    // @GetMapping("/chat")
    // public String chat(@RequestParam String message) {
    //     return chatClient.prompt()
    //             .user(message)
    //             .call()
    //             .content();
    // }

    // localhost:8080/chat?message=How do I add a new customer?
    // localhost:8080/support?message=How do I add a new customer?
    // localhost:8080/support?message=What is the weather today?

    @GetMapping("/support")
    public String support(@RequestParam String message) {
        return chatClient.prompt()
                .system("You are a friendly and professional task support assistant for a Fashion company. " +
                        "You help users with questions on their various technical, administrative tasks " +
                        "Keep your answers concise and practical. " +
                        "Always provide step-by-step instructions when explaining tasks. " +
                        "If a question is not related to tasks, politely redirect the user.")
                .user(message)
                .call()
                .content();
    }

    // Create a new endpoint /summarise in AiController.java
    // Accept a text query parameter
    // Write a system prompt that instructs the AI to summarise the text in exactly
    // five bullet points, in plain language, with no introduction and no closing
    // remarks
    // Test it with the sample text below
    // Once the summary is correct, save it to a file called summary.csv

    // localhost:8080/summariser?text=The Q3 platform migration finished two weeks
    // behind schedule. The delay came mainly from the payments module, where an
    // undocumented dependency on the old session service was only discovered during
    // integration testing. The team resolved it by introducing a temporary adapter,
    // which is now scheduled for removal in Q1. Overall system latency improved by
    // around 18 percent after the migration, and error rates during peak hours
    // dropped noticeably. Three engineers were pulled from the reporting project to
    // help with the fix, which pushed the reporting dashboard work into the next
    // quarter. The team has recommended that future migrations include a dependency
    // audit before work begins, and that integration testing start earlier rather
    // than at the end.

    // @GetMapping("/summariser")
    // public String summariser(@RequestParam String text) {
    //     String summary = chatClient.prompt()
    //             .system("Summarise the text provided by the user exactly five bullet points, in plain language, with no introduction and no closing remarks.")
    //             .user(text)
    //             .call()
    //             .content();

    //     try {
    //         Files.writeString(Path.of("summary.csv"), summary);
    //     } catch (IOException e) {
    //         return "Could not save the file: " + e.getMessage();
    //     }
    //     return summary;
    // }

    // @GetMapping("/analyse-ticket")
    // public String analyseTicket(@RequestParam String ticket) {

    //     TicketAnalysis analysis = chatClient.prompt()
    //             .user(u -> u
    //                     .text("""
    //                             Analyse this customer support ticket: {ticket}

    //                             Category must be one of:
    //                             BILLING - payments, charges, invoices, subscriptions, refunds already processed
    //                             DELIVERY - shipping, tracking, late or missing orders, items that arrived damaged
    //                             TECHNICAL - the product or app not working, errors, crashes, login problems
    //                             OTHER - anything else, including general questions and feedback

    //                             Urgency must be one of:
    //                             HIGH - the customer cannot use the service at all, or it is a repeated failure, or they are threatening to leave
    //                             MEDIUM - the customer is blocked from one thing but can still work
    //                             LOW - a question, a request for information, or positive feedback

    //                             Set refundRequested to true ONLY if the customer explicitly asks for money back,
    //                             a refund, a reversal, or a cancellation with a refund. Do not infer it.
    //                             """)
    //                     .param("ticket", ticket))
    //             .call()
    //             .entity(TicketAnalysis.class);

    //     if (analysis.refundRequested()) {
    //         return "Routed to FINANCE team — " + analysis.summary();
    //     }

    //     if (analysis.urgency().equalsIgnoreCase("HIGH")) {
    //         return "Escalated to SENIOR SUPPORT — " + analysis.summary();
    //     }

    //     return "Added to standard " + analysis.category() + " queue — " + analysis.summary();
    // }

    // @GetMapping("/summariser")
    // public String summariser(@RequestParam String message) {
    // return chatClient.prompt()
    // .system("Summarise the text provided by the user exactly five bullet points,
    // in plain language, with no introduction and no closing remarks.")
    // .user(message)
    // .call()
    // .content();
    // }
}
