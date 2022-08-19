package skane.skaneshop.Auction.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import skane.skaneshop.domain.BidInfo;

public interface BidInfoRepository extends JpaRepository<BidInfo, Long> {
}
