package com.microservices.intg.poc.messageconsumer.repository;


import com.microservices.intg.poc.messageconsumer.domain.FxRateDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FxRateDocumentRepository extends CrudRepository<FxRateDocument, String> {
}
