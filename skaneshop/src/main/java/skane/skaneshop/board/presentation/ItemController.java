package skane.skaneshop.board.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;
import skane.skaneshop.board.application.FileStore;
import skane.skaneshop.board.dto.request.ItemForm;
import skane.skaneshop.board.dto.request.UploadFile;
import skane.skaneshop.board.dto.request.Item;
import skane.skaneshop.board.infra.ItemRepository;
import skane.skaneshop.domain.Category;
import skane.skaneshop.login.application.ArgumentResolver.Login;
import skane.skaneshop.login.dto.request.Member;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    //카테고리 목록
    @ModelAttribute("category")
    public Category[] itemTypes(){
        Category[] values= Category.values();
        return values;
    }

    //새로운 UI 테스트용임
    @GetMapping("/test")
    public String testMap(@ModelAttribute ItemForm form) { return  "skone_upload";};
    //마찬가지로 새로운 UI 테스트용 잘대면 Mapping경로 바꿔 사용하기
    @PostMapping("/test")
    public String testPost(@ModelAttribute ItemForm form) throws IOException{

        List<UploadFile> storeImageFiles =fileStore.storeFiles(form.getImageFiles());

        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setHotDeal(form.getHotDeal());
        item.setImageFiles(storeImageFiles);
        item.setCategory(form.getCategory());
        itemRepository.save(item);

        System.out.println("itemRepository = " + itemRepository.findAll());

        log.info("테스트 성공");
        log.info(form.getItemName());
        System.out.println("form.getPrice() = " + form.getPrice());
        List<String> category = form.getCategory();
        for (String s : category) {
            System.out.println("s = " + s);
        }
        System.out.println("form.getPrice() = " + form.getPrice());
        System.out.println("form.getHotDeal() = " + form.getHotDeal());
        System.out.println("storeImageFiles = " + storeImageFiles);

        return "redirect:/";
    }

    //상품 수정 로직 추가함
    @GetMapping("/items/edit/{itemId}")
    public String editItemForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "skone_item_edit";}

    @PostMapping ("/items/edit/{itemId}")
    String editItem(@ModelAttribute ItemForm form,@PathVariable Long itemId) throws IOException{

        List<UploadFile> storeImageFiles =fileStore.storeFiles(form.getImageFiles());

        Item item = itemRepository.findById(itemId);
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setHotDeal(form.getHotDeal());
        item.setImageFiles(storeImageFiles);
        item.setCategory(form.getCategory());

        itemRepository.update(item);

        return "redirect:/";
    }

    //상품 삭제 기능
    @DeleteMapping("/items/delete/{itemId}")
    public String deleteItem(@PathVariable Long itemId){
        itemRepository.remove(itemId);
        return "redirect:/";
    }

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form){
        return "item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {

        //이미지 1개받으면 이거 사용
        UploadFile attachFile =fileStore.storeFile(form.getAttachFile());
        //이미지 여러개받으면 이거 사용
        List<UploadFile> storeImageFiles =fileStore.storeFiles(form.getImageFiles());

        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(storeImageFiles);
        itemRepository.save(item);

        redirectAttributes.addAttribute("itemId",item.getId());

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model){
        Item item = itemRepository.findById(id);
        model.addAttribute("item",item);
        return  "item-view";
    }

    //여러 이미지 파일 보이는 요청
    @ResponseBody
    @GetMapping(value = "/images/{filename}",produces ="image/png")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {

       return new UrlResource("file:"+fileStore.getFullPath(filename));
    }

    //파일 1개 다운로드
    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        String storeFileName = item.getAttachFile().getStoreFileName();
        String uploadFileName = item.getAttachFile().getUploadFileName();

        UrlResource urlResource = new UrlResource("file:"+fileStore.getFullPath(storeFileName));

        log.info("uploadFileName={}",uploadFileName);

        //한글깨짐 방지
        String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition ="attachment; filename=\"" + encodeUploadFileName +"\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).
                body(urlResource);

    }
}