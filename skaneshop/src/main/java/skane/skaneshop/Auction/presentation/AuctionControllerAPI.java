package skane.skaneshop.Auction.presentation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import skane.skaneshop.Auction.dto.AuctionDto;
import skane.skaneshop.Auction.dto.AuctionRequestDto;
import skane.skaneshop.Auction.infra.AuctionRepository;
import skane.skaneshop.Auction.infra.AuctionTestRepository;
import skane.skaneshop.Auction.infra.BidInfoRepository;
import skane.skaneshop.Auction.infra.BidInfoTestRepository;
import skane.skaneshop.domain.Auction;
import skane.skaneshop.domain.BidInfo;
import skane.skaneshop.domain.Category;
import skane.skaneshop.domain.Option;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * !!지금 현재 TestRepository 사용중임!!
 * JPARepository 나중에 바꿔주기
 */

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuctionControllerAPI {

    private final AuctionTestRepository auctionTestRepository;
    private final AuctionRepository auctionRepository;
    private final BidInfoTestRepository bidInfoTestRepository;
    private final BidInfoRepository bidInfoRepository;

    /**
     * ResponseDto 반환용
     * @return 현재있는 옥션들 ResponseDTO에 담아반환하는메소드
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction")
    public List<AuctionDto> getAuctions(){

       return auctionTestRepository.findAll().stream().
                map(o->new AuctionDto(o))
                .collect(Collectors.toList());

    }

    /**
     *
     *
     * @param aucReqDto
     * @return Parm으로 입찰정보를 받아와,  그 입찰정보를 저장하고 Return하는 메소드
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/auction/request")
    public List<AuctionRequestDto> reqAuctions(@RequestBody List<AuctionRequestDto> aucReqDto){

        // 여기에 입찰정보 저장로직
        for(AuctionRequestDto rq : aucReqDto){
            BidInfo bidInfo = new BidInfo();
            bidInfo.setAuction(auctionTestRepository.findById(rq.getAuctionId()));
            bidInfo.setUserName(rq.getUserName());
            bidInfo.setBid_Price(rq.getBid_price());

            bidInfoTestRepository.save(bidInfo);
        }

       return aucReqDto.stream().
                map(rq->new AuctionRequestDto(rq.getAuctionId(),rq.getUserName(), rq.getBid_price()))
                .collect(Collectors.toList());
    }
//==============================After Connected View Use Code====================================

    // 경매 데이터 저장
    /*@ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction/insert")
    public void saveAuction(@RequestBody AuctionDto auctionDto){
        Auction auction = new Auction();
        auction.setCategory(auctionDto.getCategory());
        auction.setOption(auctionDto.getOption());
        auction.setBidprice(auctionDto.getBidPrice());
        auctionRepository.save(auction);
    }*/

    // id로 검색
    /*@ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction/joinById")
    public Optional<Auction> joinById(@RequestBody Long auctionId){
        return auctionRepository.findById(auctionId);
    }*/

    // 경매 번호로 데이터 삭제
    /*@ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction/removeById")
    public void remove(@RequestBody Long auctionId){
        auctionRepository.deleteById(auctionId);
    }*/

//=============================Prototype Code====================================================

    // 경매 데이터 저장
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction/insert")
    public Auction saveAuction(){

        Auction auction = new Auction();
        auction.setCategory(Category.BOOKS);
        auction.setOption(Option.AUCTION);
//        auction.setBidprice(1000);

        return auctionRepository.save(auction);
    }

    // 정렬 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction/joinAuction")
    public List<Auction> joinAuction(){
        return auctionRepository.findAll(Sort.by(Sort.Direction.DESC,"auctionNumber"));
    }

    // id로 검색
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction/joinById")
    public Optional<Auction> joinById(Auction auction){
        return auctionRepository.findById(2L);
    }

    // 경매 번호로 데이터 삭제
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction/removeById")
    public void remove(Auction auction){
        auctionRepository.deleteById(2L);
    }

//==================================TEST CODE================================
    /*
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction")
    public List<AuctionDto> getAuctions(){
      return auctionTestRepository.findAll().stream().
                map(o->new AuctionDto(o))
                .collect(Collectors.toList());
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction/request")
    public List<AuctionRequestDto> reqAuctions(){
        return auctionTestRepository.findAll().stream().
                map(obj->new AuctionRequestDto(obj))
                .collect(Collectors.toList());
    }*/

    /*@GetMapping("/api/auction")
    public ResponseEntity<List<AuctionDto>> getAuctions(){
        List<AuctionDto> collect = auctionTestRepository.findAll().stream().
                map(o -> new AuctionDto(o))
                .collect(Collectors.toList());
        return new ResponseEntity(collect,HttpStatus.OK);
    }*/
}

