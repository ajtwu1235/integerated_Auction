package skane.skaneshop.board.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class Item {
    private Long id;
    private String itemName;
    private int price;
    private String userName;
    private String tag;
    private String content;
    private List<String> category;//등록 지역
    private UploadFile attachFile;
    private boolean hotDeal;
    private List<UploadFile> imageFiles;
}
