package com.irembo.portal.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.irembo.portal.dto.BalanceProjection;
import com.irembo.portal.dto.CountProjection;
import com.irembo.portal.dto.PaymentInvoiceStatusExtraProjection;
import com.irembo.portal.dto.PaymentInvoiceStatusProjection;
import com.irembo.portal.dto.PaymentStatusProjection;
import com.irembo.portal.model.PaymentInvoice;

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
                        "st.id AS settlementTransactionId, " +
                        "pi.payoutAmount AS invoicePayout " +
                        "FROM PaymentInvoice pi " +
                        "LEFT JOIN pi.settlementTransactionId st " +
                        "WHERE pi.appAccountId = :accountNumber AND pi.paymentStatus != 'NEW' ORDER BY createdAt DESC")
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
                        "st.id AS settlementTransactionId, " +
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
                        "st.id AS settlementTransactionId, " +
                        "pi.payoutAmount AS invoicePayout, " +
                        "ma.identifier AS identifier, " +
                        "ma.type AS accountType " +
                        "FROM PaymentInvoice pi " +
                        "LEFT JOIN pi.settlementTransactionId st " +
                        "LEFT JOIN pi.merchantAccountId ma " +
                        "WHERE pi.id = :invoiceId AND pi.paymentStatus != 'NEW'")
        PaymentInvoiceStatusExtraProjection getPaymentInvoiceDetailsWithStatus(UUID invoiceId);

        @Query(value = "SELECT count(id) FROM payment_invoice where merchant_account_id = ?2 AND payment_made_at > ?1", nativeQuery = true)
        List<CountProjection> countByAppAccountIdAndPaymentStatusAndPaymentMadeAtAfterAndMerchantAccountId(
                        LocalDateTime sevenDaysAgo, UUID accountNumber);

        @Query("SELECT " +
                        "pi.id AS id, " +
                        "pi.amount AS amount, " +
                        "pi.invoiceNumber AS invoiceNumber, " +
                        "pi.currency AS currency, " +
                        "pi.createdAt AS createdAt, " +
                        "pi.expiryAt AS expiryAt, " +
                        "pi.paymentStatus AS paymentStatus, " +
                        "st.settlementStatus AS status, " +
                        "st.id AS settlementTransactionId, " +
                        "pi.payoutAmount AS invoicePayout " +
                        "FROM PaymentInvoice pi " +
                        "LEFT JOIN pi.settlementTransactionId st " +
                        "WHERE pi.appAccountId = :appAccountId AND pi.invoiceNumber = :invoiceNumber OR upper(pi.paymentStatus) LIKE upper(concat('%', :search, '%')) OR upper(st.settlementStatus) LIKE upper(concat('%', :search, '%'))")
        Page<PaymentInvoiceStatusProjection> searchForInvoice(
                        UUID appAccountId, String invoiceNumber, String search, Pageable pageable);

        @Query("SELECT p.paymentStatus as name, COUNT(p) as value FROM PaymentInvoice p where p.appAccountId = ?1 GROUP BY p.paymentStatus")
        List<PaymentStatusProjection> countByAppAccountIdAndPaymentStatus(UUID accountId);

}
