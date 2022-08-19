package skane.skaneshop.Auction.infra;

import org.springframework.stereotype.Repository;
import skane.skaneshop.domain.Auction;
import skane.skaneshop.domain.BidInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BidInfoTestRepository {

    private final Map<Long, BidInfo> store = new HashMap<>();
    private long sequence =0L;

    public BidInfo save(BidInfo bidInfo){
        bidInfo.setBidNumber(++sequence);
        store.put(bidInfo.getBidNumber(),bidInfo);
        System.out.println("item = " + bidInfo);
        return bidInfo;
    }

    //삭제 기능
    public void remove(Long id){
        store.remove(id);
    }

    public List<BidInfo> findAll() {
        return new ArrayList<BidInfo>(store.values());
    }


    //수정 기능
    public void update(BidInfo bidInfo){
        BidInfo findBidInfo = store.get(bidInfo.getBid_Price());
        findBidInfo.setUserName(bidInfo.getUserName());
        findBidInfo.setBid_Price(bidInfo.getBid_Price());
        findBidInfo.setAuction(bidInfo.getAuction());
    }

    public BidInfo findById(Long id){
        return store.get(id);
    }
}