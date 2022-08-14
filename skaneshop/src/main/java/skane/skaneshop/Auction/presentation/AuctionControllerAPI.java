package skane.skaneshop.Auction.presentation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import skane.skaneshop.Auction.dto.AuctionDto;
import skane.skaneshop.Auction.infra.AuctionTestRepository;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequiredArgsConstructor
public class AuctionControllerAPI {

    private final AuctionTestRepository auctionTestRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auction")
    public List<AuctionDto> getAuctions(){
       return auctionTestRepository.findAll().stream().
                map(o->new AuctionDto(o))
                .collect(Collectors.toList());
    }

//    @GetMapping("/api/auction")
//    public ResponseEntity<List<AuctionDto>> getAuctions(){
//        List<AuctionDto> collect = auctionTestRepository.findAll().stream().
//                map(o -> new AuctionDto(o))
//                .collect(Collectors.toList());
//        return new ResponseEntity(collect,HttpStatus.OK);
//    }

}

