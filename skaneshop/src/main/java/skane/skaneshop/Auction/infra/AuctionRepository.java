package skane.skaneshop.Auction.infra;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import skane.skaneshop.domain.Auction;

import java.util.List;

//이건 JPA -> h2 연결하고 나서 쓰자
public interface AuctionRepository extends JpaRepository<Auction,Long> {


}
