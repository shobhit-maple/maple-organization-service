package com.maple.organization.service;

import com.alviss.commons.exception.NotFoundException;
import com.maple.organization.dao.OrganizationDao;
import com.maple.organization.service.model.OrganizationEntity;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao dao;

    public OrganizationEntity get(String id) {
        val entity = dao.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Requested organization is not found");
        }
        return entity.get();
    }

    public Page<OrganizationEntity> getAll(final Pageable pageable) {
        return dao.findAll(pageable);
    }

    public OrganizationEntity save(OrganizationEntity entity) {
        return dao.save(entity);
    }

    public void delete(final OrganizationEntity entity) {
        dao.delete(entity);
    }
}
