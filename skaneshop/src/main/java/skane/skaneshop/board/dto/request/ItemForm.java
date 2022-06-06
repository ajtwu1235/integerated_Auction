package skane.skaneshop.board.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {
        private Long id;
        private String itemName;
        private int price;
        private Boolean hotDeal;// 판매 여부
        private List<String> category;//등록 지역
        private MultipartFile attachFile;
        private List<MultipartFile> imageFiles; //이미지

}
