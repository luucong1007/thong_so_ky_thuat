package com.mpec.thong_so_ky_thuat.serviceImpl;

import com.mpec.thong_so_ky_thuat.entities.HangHoa;
import com.mpec.thong_so_ky_thuat.entities.NhomHang;
import com.mpec.thong_so_ky_thuat.entities.ThuongHieu;
import com.mpec.thong_so_ky_thuat.entities.dto.HangHoaDTO;
import com.mpec.thong_so_ky_thuat.repository.HangHoaRepo;
import com.mpec.thong_so_ky_thuat.repository.NhomHangRepo;
import com.mpec.thong_so_ky_thuat.repository.ThuongHieuRepo;
import com.mpec.thong_so_ky_thuat.service.HangHoaService;
import com.mpec.thong_so_ky_thuat.service.NhomHangService;
import com.mpec.thong_so_ky_thuat.service.ThuongHieuService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HangHoaServiceImpl implements HangHoaService {
    @Autowired
    private HangHoaRepo hangHoaRepo;

    @Autowired
    private ThuongHieuRepo thuongHieuRepo;

    @Autowired
    private NhomHangRepo nhomHangRepo;


    @Override
    public Page<HangHoa> findAll(Pageable pageable) {
        try {
            return hangHoaRepo.findAll(pageable);
        } catch (Exception ex) {
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoa> findById(Integer id) {
        try {
            return hangHoaRepo.findById(id);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Page<HangHoa> search(String ma, String tenHangHoa, Pageable pageable) {
        try {
            return hangHoaRepo.search(ma, tenHangHoa, pageable);
        } catch (Exception ex) {
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoa> update(HangHoaDTO hangHoaDTO) {
        try {
            Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(hangHoaDTO.getId());
            if (!nhomHangRepo.findById(hangHoaDTO.getId()).isPresent()) {
                System.out.println("hangHoaID not found");
                return Optional.empty();
            }
            Optional<NhomHang> nhomHangOptional = nhomHangRepo.findById(hangHoaDTO.getNhomHangId());
            if (!nhomHangOptional.isPresent()) {
                System.out.println("NHomHangID not found");
                return Optional.empty();
            }
            Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepo.findById(hangHoaDTO.getThuongHieuId());
            if (!thuongHieuOptional.isPresent()) {
                System.out.println("ThuongHieuId not found");
                return Optional.empty();
            }
            HangHoa hangHoa = hangHoaOptional.get();
            if (hangHoaDTO.getMa() != null) {
                hangHoa.setMa(hangHoaDTO.getMa());
            }
            if (hangHoaDTO.getMaGiamGia() != null) {
                hangHoa.setMaGiamGia(hangHoaDTO.getMaGiamGia());
            }
            if (hangHoaDTO.getMoTa() != null) {
                hangHoa.setMoTa(hangHoaDTO.getMoTa());
            }
            if (hangHoaDTO.getPhanTramgiamGia() != null) {
                hangHoa.setPhanTramgiamGia(hangHoaDTO.getPhanTramgiamGia());
            }
            if (hangHoaDTO.getTenHangHoa() != null) {
                hangHoa.setTenHangHoa(hangHoaDTO.getTenHangHoa());
            }
            if (hangHoaDTO.getTichDiem() != null) {
                hangHoa.setTichDiem(hangHoaDTO.getTichDiem());
            }
            if (hangHoaDTO.getUrl1() != null) {
                hangHoa.setUrl1(hangHoaDTO.getUrl1());
            }
            if (hangHoaDTO.getUrl2() != null) {
                hangHoa.setUrl2(hangHoaDTO.getUrl2());
            }
            if (hangHoaDTO.getUrl3() != null) {
                hangHoa.setUrl3(hangHoaDTO.getUrl3());
            }
            hangHoa.setNhomHang(nhomHangOptional.get());
            hangHoa.setThuongHieu(thuongHieuOptional.get());
            hangHoa.setXoa(false);
            return Optional.ofNullable(hangHoaRepo.save(hangHoa));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            return hangHoaRepo.delete(id) >= 0;
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            return false;
        }
    }

    @Override
    public Optional<HangHoa> save(HangHoaDTO hangHoaDTO) {
        try {
            Optional<NhomHang> nhomHangOptional = nhomHangRepo.findById(hangHoaDTO.getNhomHangId());
            if (!nhomHangOptional.isPresent()) {
                return Optional.empty();
            }
            Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepo.findById(hangHoaDTO.getThuongHieuId());
            if (!thuongHieuOptional.isPresent()) {
                return Optional.empty();
            }
            HangHoa hangHoa = new HangHoa();
            hangHoa.setMa(hangHoaDTO.getMa());
            hangHoa.setMaGiamGia(hangHoaDTO.getMaGiamGia());
            hangHoa.setMoTa(hangHoaDTO.getMoTa());
            hangHoa.setPhanTramgiamGia(hangHoaDTO.getPhanTramgiamGia());
            hangHoa.setTenHangHoa(hangHoaDTO.getTenHangHoa());
            hangHoa.setTichDiem(hangHoaDTO.getTichDiem());
            hangHoa.setUrl1(hangHoaDTO.getUrl1());
            hangHoa.setUrl2(hangHoaDTO.getUrl2());
            hangHoa.setUrl3(hangHoaDTO.getUrl3());
            hangHoa.setNhomHang(nhomHangOptional.get());
            hangHoa.setThuongHieu(thuongHieuOptional.get());
            hangHoa.setXoa(false);
            return Optional.ofNullable(hangHoaRepo.save(hangHoa));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
