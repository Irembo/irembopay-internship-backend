package com.irembo.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.irembo.portal.dto.BalanceProjection;
import com.irembo.portal.dto.PaymentInvoiceStatusExtraProjection;
import com.irembo.portal.dto.PaymentInvoiceStatusProjection;
import com.irembo.portal.model.PaymentInvoice;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentInvoiceRepository extends JpaRepository<PaymentInvoice, UUID> {
        // List<PaymentInvoiceProjection> findAllProjectedBy(UUID accountId);

        List<PaymentInvoice> findByAppAccountIdAndPaymentStatusAndPaymentMadeAtBefore(
                        UUID merchantAccountId, String paymentStatus, LocalDateTime paymentMadeAt);

        long countByAppAccountIdAndPaymentStatusAndPaymentMadeAtAfter(UUID accountId, String paid,
                        LocalDateTime sevenDaysAgo);

        @Query("SELECT i FROM PaymentInvoice i WHERE i.status = 'PAID' AND i.paymentMadeAt >= :lastNdays")
        List<PaymentInvoice> findPaidInvoicesWithinLastNDays(LocalDateTime lastNdays);

        @Query(value = "SELECT currency, SUM(amount) AS totalAmount\n" +
                        "FROM payment_invoice i\n" +
                        "WHERE i.app_account_id = ?1\n" +
                        "  AND i.payment_status = 'PAID'\n" +
                        "  AND i.payment_made_at >= ?2\n" +
                        "  AND i.payment_made_at <= ?3\n" +
                        "GROUP BY currency;", nativeQuery = true)
        List<BalanceProjection> sumInvoiceAmountByAppAccountIdAndPaymentStatusAndPaymentMadeAtAfter(UUID accountId,
                        LocalDateTime lastPayoutDone, LocalDateTime sevenDaysFromNow);

        @Query("SELECT " +
                        "pi.id AS id, " +
                        "pi.amount AS amount, " +
                        "pi.invoiceNumber AS invoiceNumber, " +
                        "pi.currency AS currency, " +
                        "pi.createdAt AS createdAt, " +
                        "pi.expiryAt AS expiryAt, " +
                        "pi.paymentStatus AS paymentStatus, " +
                        "st.settlementStatus AS status, " +
                        "pi.payoutAmount AS invoicePayout " +
                        "FROM PaymentInvoice pi " +
                        "LEFT JOIN pi.settlementTransactionId st " +
                        "WHERE pi.appAccountId = :accountNumber AND pi.paymentStatus != 'NEW'")
        Page<PaymentInvoiceStatusProjection> findByAppAccountId(UUID accountNumber, Pageable pageable);

        @Query("SELECT " +
                        "pi.id AS id, " +
                        "pi.amount AS amount, " +
                        "pi.invoiceNumber AS invoiceNumber, " +
                        "pi.currency AS currency, " +
                        "pi.createdAt AS createdAt, " +
                        "pi.expiryAt AS expiryAt, " +
                        "pi.paymentStatus AS paymentStatus, " +
                        "st.settlementStatus AS status, " +
                        "pi.payoutAmount AS invoicePayout " +
                        "FROM PaymentInvoice pi " +
                        "LEFT JOIN pi.settlementTransactionId st " +
                        "WHERE pi.appAccountId = :accountNumber AND pi.paymentStatus != 'NEW'")
        List<PaymentInvoiceStatusProjection> findByAppAccountIdAll(UUID accountNumber);

        @Query("SELECT " +
                        "pi.id AS id, " +
                        "pi.amount AS amount, " +
                        "pi.invoiceNumber AS invoiceNumber, " +
                        "pi.currency AS currency, " +
                        "pi.createdAt AS createdAt, " +
                        "pi.expiryAt AS expiryAt, " +
                        "pi.paymentMadeAt AS paymentMadeAt, " +
                        "pi.settledAt AS settledAt, " +
                        "pi.paymentStatus AS paymentStatus, " +
                        "st.settlementStatus AS status, " +
                        "pi.payoutAmount AS invoicePayout, " +
                        "ma.type AS accountType " +
                        "FROM PaymentInvoice pi " +
                        "LEFT JOIN pi.settlementTransactionId st " +
                        "RIGHT JOIN pi.merchantAccountId ma " +
                        "WHERE pi.id = :invoiceId AND pi.paymentStatus != 'NEW'")
        PaymentInvoiceStatusExtraProjection getPaymentInvoiceDetailsWithStatus(UUID invoiceId);

}
