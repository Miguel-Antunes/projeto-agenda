package io.github.miguelantunes.agendaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.miguelantunes.agendaapi.model.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}
