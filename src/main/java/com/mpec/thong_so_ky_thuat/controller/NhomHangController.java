package com.mpec.thong_so_ky_thuat.controller;

import com.mpec.thong_so_ky_thuat.entities.NhomHang;
import com.mpec.thong_so_ky_thuat.entities.ThuongHieu;
import com.mpec.thong_so_ky_thuat.service.NhomHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/nhom-hang")
public class NhomHangController {
    @Autowired
    NhomHangService nhomHangService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhomHang> nhomHangs = nhomHangService.findAll(pageable);
        return ResponseEntity.ok(nhomHangs);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        Optional<NhomHang> nhomHangOptional = nhomHangService.findById(id);
        if (nhomHangOptional.isPresent()) {
            return ResponseEntity.ok(nhomHangOptional.get());
        } else {
            return ResponseEntity.ok("Nhóm hàng không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "maNhomHang", required = false) String maNhomHang,
                                    @RequestParam(name = "tenNhomHang", required = false) String tenNhomHang,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhomHang> nhomHangs = nhomHangService.search(maNhomHang, tenNhomHang, pageable);
        return ResponseEntity.ok(nhomHangs);
    }

    @PostMapping("/save")
    public NhomHang save(@RequestBody NhomHang  nhomHang) {
        Optional<NhomHang> nhomHangOptional = nhomHangService.save(nhomHang);
        if (nhomHangOptional.isPresent()) {
            return nhomHangOptional.get();
        } else {
            return null;
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody NhomHang nhomHang) {
        Optional<NhomHang> nhomHangOptional = nhomHangService.update(nhomHang);
        if(nhomHangOptional.isPresent()){
            return ResponseEntity.ok("success");
        }else {
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return nhomHangService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }
}
