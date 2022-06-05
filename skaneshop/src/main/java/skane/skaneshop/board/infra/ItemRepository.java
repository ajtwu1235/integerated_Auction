package skane.skaneshop.board.infra;

import org.springframework.stereotype.Repository;
import skane.skaneshop.board.dto.request.Item;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {

    private final Map<Long, Item> store = new HashMap<>();
    private long sequence =0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(),item);
        return item;
    }

    //삭제 기능
    public void remove(Long id){
        store.remove(id);
    }

    //수정 기능
    public void update(Item item){
        Item findItem = store.get(item.getId());
        findItem.setItemName(item.getItemName());
        findItem.setCategory(item.getCategory());
        findItem.setAttachFile(item.getAttachFile());
        findItem.setImageFiles(item.getImageFiles());
        findItem.setPrice(item.getPrice());
        findItem.setHotDeal(item.isHotDeal());
        findItem.setTag(item.getTag());
    }

    public Item findById(Long id){
        return store.get(id);
    }
}
