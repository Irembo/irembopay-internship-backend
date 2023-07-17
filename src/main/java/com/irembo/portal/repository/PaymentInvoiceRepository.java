package com.irembo.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.irembo.portal.dto.BalanceProjection;
import com.irembo.portal.dto.PaymentInvoiceProjection;
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

        Page<PaymentInvoiceProjection> findByAppAccountId(UUID accountNumber, Pageable pageable);
}
