package com.genesiscode.quotation.config;
import com.genesiscode.quotation.domain.*;
import com.genesiscode.quotation.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.util.*;
import lombok.*;

@AllArgsConstructor
@Configuration
public class DataLoader {

    private final PermissionRepository permissionRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Permission manageRole = new Permission(Functionality.MANAGE_ROLE);
            Permission manageUser = new Permission(Functionality.MANAGE_USER);
            Permission makeRequest = new Permission(Functionality.MAKE_REQUEST);
            Permission reviewRequest = new Permission(Functionality.REVIEW_REQUEST);
            Permission answerRequest = new Permission(Functionality.ANSWER_REQUEST);
            Permission makeQuote = new Permission(Functionality.MAKE_QUOTE);
            Permission makeQuotationReport = new Permission(Functionality.MAKE_QUOTATION_REPORT);
            Permission respondingQuotationReport = new Permission(Functionality.RESPONDING_QUOTATION_REPORT);

            permissionRepository.saveAll(List.of(manageRole, manageUser, makeRequest, reviewRequest,
                    answerRequest, makeQuote, makeQuotationReport, respondingQuotationReport));
        };
    }
}