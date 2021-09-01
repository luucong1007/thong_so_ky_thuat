package com.mpec.thong_so_ky_thuat.serviceImpl;

import com.mpec.thong_so_ky_thuat.entities.HangHoa;
import com.mpec.thong_so_ky_thuat.entities.NhomHang;
import com.mpec.thong_so_ky_thuat.entities.NhomThongSo;
import com.mpec.thong_so_ky_thuat.entities.ThuongHieu;
import com.mpec.thong_so_ky_thuat.repository.HangHoaRepo;
import com.mpec.thong_so_ky_thuat.repository.NhomHangRepo;
import com.mpec.thong_so_ky_thuat.repository.NhomThongSoRepo;
import com.mpec.thong_so_ky_thuat.service.NhomHangService;
import com.mpec.thong_so_ky_thuat.service.NhomThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NhomThongSoServiceImpl implements NhomThongSoService {
    @Autowired
    NhomThongSoRepo nhomThongSoRepo;

    @Autowired
    private HangHoaRepo hangHoaRepo;


    @Override
    public Page<NhomThongSo> findAll(Pageable pageable) {
        try {
            return nhomThongSoRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomThongSo> findById(Integer id) {
        try {
            return nhomThongSoRepo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<NhomThongSo> search(String maNhomHang, String tenNhomHang, Pageable pageable) {
        try {
            return nhomThongSoRepo.search(maNhomHang, tenNhomHang, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomThongSo> update(NhomThongSo nhomThongSo, int hangHoaId) {
        try {
            Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(hangHoaId);
            if (!hangHoaOptional.isPresent()) {
                System.out.println("hangHoaID not found");
                return Optional.empty();
            }
            Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoRepo.findById(nhomThongSo.getId());
            if (!nhomThongSoOptional.isPresent()) {
                System.out.println("NHomHangID not found");
                return Optional.empty();
            }
            NhomThongSo nhomThongSo1 = nhomThongSoOptional.get();
            if (nhomThongSo.getMaNhomThongSo() != null) {
                nhomThongSo1.setMaNhomThongSo(nhomThongSo.getMaNhomThongSo());
            }
            if (nhomThongSo.getTenNhomThongSo() != null) {
                nhomThongSo1.setTenNhomThongSo(nhomThongSo.getTenNhomThongSo());
            }
            nhomThongSo1.setHangHoa(hangHoaOptional.get());
            nhomThongSo1.setXoa(false);
            return Optional.ofNullable(nhomThongSoRepo.save(nhomThongSo1));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            return nhomThongSoRepo.delete(id) >= 0;
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            return false;
        }
    }

    @Override
    public Optional<NhomThongSo> save(NhomThongSo nhomThongSo, int hangHoaId) {
        try {
            Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(hangHoaId);
            if (!hangHoaOptional.isPresent()) {
                return Optional.empty();
            }
            NhomThongSo nhomThongSo1 = new NhomThongSo();
            nhomThongSo1.setXoa(false);
            nhomThongSo1.setMaNhomThongSo(nhomThongSo.getMaNhomThongSo());
            nhomThongSo1.setTenNhomThongSo(nhomThongSo.getTenNhomThongSo());
            nhomThongSo1.setHangHoa(hangHoaOptional.get());
            return Optional.ofNullable(nhomThongSoRepo.save(nhomThongSo1));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
