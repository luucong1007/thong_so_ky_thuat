package com.mpec.thong_so_ky_thuat.controller;

import com.mpec.thong_so_ky_thuat.entities.ThuongHieu;
import com.mpec.thong_so_ky_thuat.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/thuong-hieu")
public class ThuongHieuController {
    @Autowired
    ThuongHieuService thuongHieuService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThuongHieu> thuongHieus = thuongHieuService.findAll(pageable);
        return ResponseEntity.ok(thuongHieus);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuService.findById(id);
        if (thuongHieuOptional.isPresent()) {
            return ResponseEntity.ok(thuongHieuOptional.get());
        } else {
            return ResponseEntity.ok("Thương hiệu không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text") String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThuongHieu> thuongHieus = thuongHieuService.search(text, pageable);
        return ResponseEntity.ok(thuongHieus);
    }

    @PostMapping("/save")
    public ThuongHieu save(@RequestBody ThuongHieu thuongHieu) {
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuService.save(thuongHieu);
        if (thuongHieuOptional.isPresent()) {
            return thuongHieuOptional.get();
        } else {
            return null;
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> updateTenThuongHieu(@RequestParam(name = "id") Integer id,
                                          @RequestParam(name = "tenThuongHieu") String tenThuongHieu) {
        return thuongHieuService.updateTenThuongHieu(id, tenThuongHieu)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return thuongHieuService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }
}
