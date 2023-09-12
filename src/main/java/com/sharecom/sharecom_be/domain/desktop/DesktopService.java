package com.sharecom.sharecom_be.domain.desktop;

import com.sharecom.sharecom_be.domain.parts.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class DesktopService {

    private final DesktopRepository desktopRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public List<GetDesktopDto> getDesktop(GetDesktopParam getDesktopParam) throws ParseException {

        List<GetDesktopDto> desktopList = desktopRepository.findAllBySerial(getDesktopParam.getSerial(), getDesktopParam.isUsedYn());

        return desktopList;
    }

    public String addParts(PostPartsReq postPartsReq) {


        return "";
    }
}
