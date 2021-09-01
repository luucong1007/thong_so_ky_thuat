package com.mpec.thong_so_ky_thuat.controller;

import com.mpec.thong_so_ky_thuat.entities.NhomThongSo;
import com.mpec.thong_so_ky_thuat.service.NhomThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/nhom-thong-so")
public class NhomThongSoController {

    @Autowired
    NhomThongSoService nhomThongSoService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhomThongSo> hangHoas = nhomThongSoService.findAll(pageable);
        return ResponseEntity.ok(hangHoas);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        Optional<NhomThongSo> hangHoaOptional = nhomThongSoService.findById(id);
        if (hangHoaOptional.isPresent()) {
            return ResponseEntity.ok(hangHoaOptional.get());
        } else {
            return ResponseEntity.ok("Hàng hoá không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "ma", required = false) String ma,
                                    @RequestParam(name = "text", required = false) String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhomThongSo> hangHoas = nhomThongSoService.search(ma, text, pageable);
        return ResponseEntity.ok(hangHoas);
    }

    @PostMapping("/save")
    public NhomThongSo save(@RequestBody NhomThongSo nhomThongSo,
                            @RequestParam(name = "hangHoaId") int hangHoaId) {
        Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoService.save(nhomThongSo, hangHoaId);
        if (nhomThongSoOptional.isPresent()) {
            return nhomThongSoOptional.get();
        } else {
            return null;
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody NhomThongSo nhomThongSo,
                             @RequestParam(name = "hangHoaId") int hangHoaId){
        Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoService.update(nhomThongSo, hangHoaId);
        if (nhomThongSoOptional.isPresent()) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return nhomThongSoService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }

}
