package com.example.converter;

import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.model.Architecture;
import com.example.po.ArchComponentConnectionPO;
import com.example.po.ArchComponentPO;
import com.example.po.ArchSystemPO;
import com.example.po.ArchitecturePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArchSystemConverter {
    ArchSystemConverter INSTANCE = Mappers.getMapper(ArchSystemConverter.class);

    ArchSystem from(ArchSystemPO archSystemPO);
    ArchSystemPO to(ArchSystem archSystem);

    Architecture from(ArchitecturePO architecturePO);
    ArchitecturePO to(Architecture architecture);

    ArchComponent from(ArchComponentPO archComponentPO);
    ArchComponentPO to(ArchComponent archComponent);

    ArchComponentConnection from(ArchComponentConnectionPO archComponentConnectionPO);
    ArchComponentConnectionPO to(ArchComponentConnection archComponentConnection);
}
