package skane.skaneshop.Auction.infra;

import org.springframework.stereotype.Repository;
import skane.skaneshop.board.dto.request.Item;
import skane.skaneshop.domain.Auction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuctionTestRepository {

    private final Map<Long, Auction> store = new HashMap<>();
    private long sequence =0L;

    public Auction save(Auction auction){
        auction.setAuctionNumber(++sequence);
        //Time 도 그냥 생성될때 만들어버리자
        auction.setLeft_time(LocalDateTime.now().plusDays(7L));
        store.put(auction.getAuctionNumber(),auction);
        System.out.println("item = " + auction);
        return auction;
    }

    //삭제 기능
    public void remove(Long id){
        store.remove(id);
    }

    public List<Auction> findAll() {
        return new ArrayList<Auction>(store.values());
    }


    //수정 기능
    public void update(Auction auction){
        Auction findAuction = store.get(auction.getAuctionNumber());
        findAuction.setCategory(auction.getCategory());
        findAuction.setOption(auction.getOption());
        findAuction.setPostBordNumber(auction.getPostBordNumber());
    }

    public Auction findById(Long id){
        return store.get(id);
    }
}
