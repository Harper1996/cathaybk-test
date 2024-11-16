package tw.idv.harper.cathaybktest.web.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.harper.cathaybktest.core.pojo.Core;
import tw.idv.harper.cathaybktest.web.product.dto.PriceDTO;
import tw.idv.harper.cathaybktest.web.product.dto.ProductDTO;
import tw.idv.harper.cathaybktest.web.product.service.PriceService;
import tw.idv.harper.cathaybktest.web.product.vo.Price;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    // 新增價格至DB
    @PostMapping()
    public ResponseEntity<Core> addPrices(@RequestBody PriceDTO priceDTO) {
        Core response = new Core();
        try {
            response = priceService.addPrices(priceDTO);
        } catch (Exception e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }


    // 查詢某日價格
    @GetMapping("/{productId}/{date}")
    public ResponseEntity<Core> searchPrice(@PathVariable String productId, @PathVariable Long date) {
        Core response = new Core();
        response.setSuccessful(false);

        try {
            response = priceService.searchPrice(productId, date);
        }catch (Exception e){
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }


    // 修改某日價格
    @PutMapping
    public ResponseEntity<Core> editPrice(@RequestBody ProductDTO productDTO) {
        Core response = new Core();
        response.setSuccessful(false);

        try {
            response = priceService.editPrice(productDTO.getPriceId(), productDTO.getPrice());
        } catch (Exception e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{priceId}")
    public ResponseEntity<Core> deletePrice(@PathVariable Long priceId) {
        Core response = new Core();
        response.setSuccessful(false);

        try {
            response = priceService.deletePrice(priceId);
        } catch (Exception e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

}
