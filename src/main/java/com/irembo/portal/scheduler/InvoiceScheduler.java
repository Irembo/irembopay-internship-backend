package com.irembo.portal.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.irembo.portal.model.PaymentAccount;
import com.irembo.portal.model.PaymentInvoice;
import com.irembo.portal.model.SettlementTransaction;
import com.irembo.portal.repository.PaymentInvoiceRepository;
import com.irembo.portal.repository.SettlementTransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class InvoiceScheduler {

    @Autowired
    private PaymentInvoiceRepository paymentInvoiceRepository;

    @Autowired
    private SettlementTransactionRepository settlementTransactionRepository;

    // @Scheduled(fixedDelay = 3600000) // Runs every 1 hour
    @Scheduled(fixedDelay = 300000) // Runs every 1 hour
    @Transactional
    public void processPaidInvoices() {
        LocalDateTime lastNdays = LocalDateTime.now().minusDays(1); // Replace N with the desired number of days

        List<PaymentInvoice> paidInvoices = paymentInvoiceRepository.findPaidInvoicesWithinLastNDays(lastNdays);

        for (PaymentInvoice invoice : paidInvoices) {
            try {
                // Retrieve payment accounts for stakeholders
                PaymentAccount merchantAccount = invoice.getMerchantAccountId();
                PaymentAccount revenueAccount = invoice.getRevenueAccountId();
                PaymentAccount settlementAccount = invoice.getSettlementAccountId();

                // Check if any of the payment accounts are missing
                if (merchantAccount == null || revenueAccount == null || settlementAccount == null) {
                    // Log missing payment account and skip processing
                    System.out.println("Skipping invoice " + invoice.getInvoiceNumber() +
                            " due to missing payment account(s)");
                    continue;
                }

                // Create settlement transactions
                createSettlementTransaction(invoice, merchantAccount);
                createSettlementTransaction(invoice, revenueAccount);
                createSettlementTransaction(invoice, settlementAccount);

            } catch (Exception e) {
                // Handle any exceptions during processing
                System.out.println("Error processing invoice " + invoice.getInvoiceNumber() + ": " + e.getMessage());
            }
        }
    }

    private void createSettlementTransaction(PaymentInvoice invoice, PaymentAccount account) {
        // Create and populate settlement transaction object
        SettlementTransaction settlementTransaction = new SettlementTransaction();
        settlementTransaction.setId(UUID.randomUUID()); // Generate a unique ID
        settlementTransaction.setAmount(invoice.getAmount());
        settlementTransaction.setAppAccountId(invoice.getAppAccountId());
        // Set other fields as needed

        // Save the settlement transaction to the database
        settlementTransactionRepository.save(settlementTransaction);
        System.out.println("Created settlement transaction " + settlementTransaction.getId() +
                " for invoice " + invoice.getInvoiceNumber());
    }
}

