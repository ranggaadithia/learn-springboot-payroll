package com.dimata.service.payroll.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public abstract class BaseRepository {

  protected final DSLContext jooq;

  protected UUID generatedId() { return UUID.randomUUID(); }
}
